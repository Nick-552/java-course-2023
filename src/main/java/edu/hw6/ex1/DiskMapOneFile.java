package edu.hw6.ex1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

public record DiskMapOneFile(Path path) implements Map<String, String> {

    private static final String KEY = "key";

    private static final String VALUE = "value";

    private static final String KEY_PATTERN = "^%s:(.*)$";

    private static final String VALUE_PATTERN = "^.*:%s$";

    @SneakyThrows
    public DiskMapOneFile(Path path) {
        this.path = path;
        File directory = path.getParent().toFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("cannot create dir: %s".formatted(directory.getName()));
        }
        File mapFile = path.toFile();
        if (!mapFile.exists() && !mapFile.createNewFile()) {
            throw new RuntimeException("cannot create file: %s".formatted(path));
        }
        clear();
    }

    @Override
    public int size() {
        try (var stream = Files.lines(path)) {
            return (int) stream.count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        check(key, KEY);
        try (var stream = Files.lines(path)) {
            return stream.anyMatch(
                string -> string
                    .matches(String.format(KEY_PATTERN, key))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        check(value, VALUE);
        try (var stream = Files.lines(path)) {
            return stream.anyMatch(
                string -> string
                    .matches(String.format(VALUE_PATTERN, value))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(Object key) {
        check(key, KEY);
        try (var stream = Files.lines(path)) {
            return stream
                .filter(
                    string -> string
                        .matches(String.format(KEY_PATTERN, key))
                ).findFirst()
                .map(string -> string.split(":")[1])
                .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String put(String key, String value) {
        check(key, KEY);
        check(value, VALUE);
        String oldValue = remove(key);
        try (FileWriter fileWriter = new FileWriter(path.toFile(), true)) {
            fileWriter.write(key + ":" + value + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        check(key, KEY);
        var entrySet = entrySet();
        var currentEntry = entrySet.stream().filter(e -> e.getKey().equals(key)).findFirst().orElse(null);
        if (currentEntry != null) {
            try (FileWriter fileWriter = new FileWriter(path.toFile())) {
                StringBuilder stringBuilder = new StringBuilder();
                for (var e : entrySet) {
                    if (!e.getKey().equals(key)) {
                        stringBuilder.append(e.getKey()).append(":").append(e.getValue()).append("\n");
                    }
                }
                fileWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return currentEntry.getValue();
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        var map = entrySet().stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        map.putAll(m);
        try {
            Files.write(
                path,
                map.entrySet().stream().map(entry -> entry.getKey() + ":" + entry.getValue()).toList(),
                //
                StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            fileWriter.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull Set<String> keySet() {
        return entrySet().stream()
            .map(Entry::getKey)
            .collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<String> values() {
        return entrySet().stream()
            .map(Entry::getValue)
            .collect(Collectors.toSet());
    }

    @Override
    public @NotNull Set<Entry<String, String>> entrySet() {
        try (Stream<String> stream = Files.lines(path)) {
            return stream
                .filter(string -> !string.isEmpty())
                .map(line -> {
                    String[] mapEntry = line.split(":");
                    return new DiskMapOneFileEntry(mapEntry[0], mapEntry[1]);
                }).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void check(Object keyOrValue, String type) {
        if (keyOrValue == null) {
            throw new IllegalArgumentException(String.format("%s must not be null", type));
        }
        if (!(keyOrValue instanceof String)) {
            throw new IllegalArgumentException(String.format("%s must be a String", type));
        }
        if (((String) keyOrValue).contains(":")) {
            throw new IllegalArgumentException(String.format("%s must not contain ':' character", type));
        }
    }

    @AllArgsConstructor
    @Getter
    static final class DiskMapOneFileEntry implements Entry<String, String> {

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
