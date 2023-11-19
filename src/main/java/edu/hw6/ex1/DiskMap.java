package edu.hw6.ex1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public record DiskMap(Path path) implements Map<String, String> {

    private static final String NULL_MSG = "%s must not be null";

    private static final String NOT_STRING_MSG = "%s must be a String";

    private static final String KEY = "key";

    private static final String VALUE = "value";

    public DiskMap(Path path) {
        this.path = path;
        File directory = path.toFile();
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("cannot create dir: %s".formatted(path));
            }
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
        check(key, KEY);
        File file = path.resolve((String) key).toFile();
        return file.exists();
    }

    @Override
    public boolean containsValue(Object value) {
        check(value, VALUE);
        return values().contains((String) value);
    }

    @Override
    public String get(Object key) {
        check(key, KEY);
        Path keyPath = path.resolve((String) key);
        if (Files.exists(keyPath)) {
            try {
                return Files.readString(keyPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String put(String key, String value) {
        check(key, KEY);
        check(value, VALUE);
        Path keyPath = path.resolve(key);
        try {
            String oldValue = get(key);
            Files.write(keyPath, value.getBytes());
            return oldValue;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String remove(Object key) {
        check(key, KEY);
        File file = path.resolve((String) key).toFile();
        if (file.exists()) {
            String value = get(key);
            file.delete();
            return value;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
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
    public @NotNull Set<String> values() {
        try (Stream<Path> paths = Files.list(path)) {
            return paths
                .filter(path1 -> path1.toFile().isFile())
                .map(p -> {
                    try {
                        return new String(Files.readAllBytes(p));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Set<Entry<String, String>> entrySet() {
        try (Stream<Path> paths = Files.list(path)) {
            return paths.map(p -> {
                String key = p.getFileName().toString();
                String value = get(key);
                return new DiskMapEntry(key, value);
            }).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void check(Object keyOrValue, String type) {
        if (keyOrValue == null) {
            throw new IllegalArgumentException(String.format(NULL_MSG, type));
        }
        if (!(keyOrValue instanceof String)) {
            throw new IllegalArgumentException(String.format(NOT_STRING_MSG, type));
        }
    }

    @AllArgsConstructor
    @Getter
    static final class DiskMapEntry implements Entry<String, String> {


        private final String key;

        private String value;

        @Override
        public String setValue(String value) {
            String oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            return o instanceof Map.Entry<?, ?> e
                && Objects.equals(key, e.getKey())
                && Objects.equals(value, e.getValue());
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 29 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }
}
