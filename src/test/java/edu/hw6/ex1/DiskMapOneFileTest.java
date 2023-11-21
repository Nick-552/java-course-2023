package edu.hw6.ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DiskMapOneFileTest {

    private static DiskMapOneFile diskMap = createDiskMapOneFile();

    private static DiskMapOneFile createDiskMapOneFile() {
        DiskMapOneFile diskMap;
        try {
            diskMap = new DiskMapOneFile(Files.createTempFile("map", "txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        diskMap.path().toFile().deleteOnExit();
        return diskMap;
    }

    @BeforeEach
    void clear() {
        diskMap = createDiskMapOneFile();
    }

    @Test
    @DisplayName("size and isEmpty test")
    void sizeAndIsEmpty() {
        assertThat(diskMap.size()).isEqualTo(0);
        assertThat(diskMap.isEmpty()).isTrue();
        diskMap.put("key1", "value1");
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertThat(diskMap.size()).isEqualTo(2);
        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("put, get and contains")
    void putGetContains() {
        assertThat(diskMap.get("key1")).isNull();
        assertThat(diskMap.containsKey("key1")).isFalse();
        assertThat(diskMap.containsValue("value1")).isFalse();
        diskMap.put("key1", "value1");
        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.containsKey("key1")).isTrue();
        assertThat(diskMap.containsValue("value1")).isTrue();
    }

    @Test
    @DisplayName("remove")
    void remove() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertThat(diskMap.containsKey("key1")).isTrue();
        assertThat(diskMap.remove("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key1")).isNull();
        assertThat(diskMap.containsKey("key1")).isFalse();
    }

    @Test
    @DisplayName("keySet and values")
    void keySetAndValues() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        assertThat(diskMap.values()).containsExactlyInAnyOrder("value2", "value1");
        assertThat(diskMap.keySet()).containsExactlyInAnyOrder("key2", "key1");
    }

    @Test
    @DisplayName("wrong arguments")
    void putNullOrNotString_shouldThrowIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> diskMap.put("key1", null));
        assertThatIllegalArgumentException().isThrownBy(() -> diskMap.put(null, "key1"));
        assertThatIllegalArgumentException().isThrownBy(() -> diskMap.get(5));
        assertThatIllegalArgumentException().isThrownBy(() -> diskMap.containsValue(16));
    }

    @Test
    @DisplayName("clear")
    void clearTest() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        diskMap.clear();
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("putAll and entrySet")
    void putAllAndEntrySet() {
        var map = Map.of("key1", "value1", "key2", "value2");
        diskMap.putAll(map);
        assertThat(diskMap.entrySet()).containsExactlyInAnyOrderElementsOf(map.entrySet());
    }

    @Test
    @DisplayName("diskMapEntry")
    void diskMapEntry() {
        var diskMapEntry = new DiskMapOneFile.DiskMapOneFileEntry("key1", "value1");
        assertThat(diskMapEntry.getKey()).isEqualTo("key1");
        assertThat(diskMapEntry.getValue()).isEqualTo("value1");
        diskMapEntry.setValue("value2");
        assertThat(diskMapEntry.getValue()).isEqualTo("value2");
    }
}
