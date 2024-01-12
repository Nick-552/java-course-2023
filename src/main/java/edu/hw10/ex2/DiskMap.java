package edu.hw10.ex2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public class DiskMap<T> implements Map<String, T> {

    private final Path path;

    public DiskMap(Path path) {
        this.path = path;
        File directory = path.toFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("cannot create dir: %s".formatted(path));
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(path.toFile().list()).length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        checkKey(key);
        File file = path.resolve((String) key).toFile();
        return file.exists();
    }

    @Override
    public boolean containsValue(Object value) {
        checkValue(value);
        return values().contains((T) value);
    }

    @SneakyThrows
    @Override
    public T get(Object key) {
        checkKey(key);
        Path keyPath = path.resolve((String) key);
        if (Files.exists(keyPath)) {
            try (
                FileInputStream fileIn = new FileInputStream(keyPath.toFile());
                ObjectInputStream in = new ObjectInputStream(fileIn)
            ) {
                return (T) in.readObject();
            }
        } else {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public T put(String key, T value) {
        checkNotNull(key);
        checkNotNull(value);
        Path keyPath = path.resolve(key);
        T oldValue = get(key);
        try (
            FileOutputStream fileOut = new FileOutputStream(keyPath.toFile());
            ObjectOutputStream out = new ObjectOutputStream(fileOut)
        ) {
            out.writeObject(value);
        }
        return oldValue;
    }

    @Override
    public T remove(Object key) {
        checkKey(key);
        File file = path.resolve((String) key).toFile();
        if (file.exists()) {
            T value = get(key);
            file.delete();
            return value;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        try (Stream<Path> paths = Files.list(path)) {
            paths.forEach(p -> p.toFile().delete());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Set<String> keySet() {
        try (Stream<Path> paths = Files.list(path)) {
            return paths
                .filter(path1 -> path1.toFile().isFile())
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Set<T> values() {
        try (Stream<Path> paths = Files.list(path)) {
            return paths
                .filter(path1 -> path1.toFile().isFile())
                .map(p -> {
                    try (
                        FileInputStream fileIn = new FileInputStream(p.toFile());
                        ObjectInputStream in = new ObjectInputStream(fileIn)
                    ) {
                        return (T) in.readObject();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Set<Entry<String, T>> entrySet() {
        try (Stream<Path> paths = Files.list(path)) {
            return paths.map(p -> {
                String key = p.getFileName().toString();
                T value = get(key);
                return new DiskMapEntry<>(key, value);
            }).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkNotNull(Object keyOrValue) {
        if (keyOrValue == null) {
            throw new IllegalArgumentException("keyOrValue must not be null");
        }
    }

    private void checkKey(Object key) {
        checkNotNull(key);
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("Key must be String");
        }
    }

    private void checkValue(Object value) {
        checkNotNull(value);
        try {
            var tValue = (T) value;
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal type of value");
        }
    }

    @AllArgsConstructor
    @Getter
    static final class DiskMapEntry<T> implements Entry<String, T> {

        private final String key;

        private T value;

        @Override
        public T setValue(T value) {
            T oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            return o instanceof Map.Entry<?, ?> entry
                && Objects.equals(key, entry.getKey())
                && Objects.equals(value, entry.getValue());
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 29 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }
}
