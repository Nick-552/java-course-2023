package edu.hw6.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static edu.hw6.ex3.Filters.writeable;
import static org.assertj.core.api.Assertions.assertThat;

class AbstractFilterTest {
    public static final List<Path> testFiles;

    static {
        try {
            testFiles = prepareFiles(Files.createTempDirectory("testdir"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Path> prepareFiles(Path directory) throws IOException {
        List<Path> files = List.of(
            Files.createTempFile(directory, "tinkoff", ".txt"),
            Files.createTempFile(directory, "offset", ".txt"),
            Files.createTempFile(directory, "intellij", ".txt"),
            Files.createTempFile(directory, "theBestOfTheBestAre", ".txt"),
            Files.createTempFile(directory, "votavHyegreSniSsined", ".png"),
            Files.createTempFile(directory, "turn off", ".png")
        );
        files.forEach(path -> {
            path.toFile().deleteOnExit();
            if (path.getFileName().toString().endsWith(".png")) {
                try {
                    Files.write(path, new byte[] {(byte) 0x89, 'P', 'N', 'G'});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Files.write(files.get(1), new byte[] {0x25});
        Files.write(files.get(4), new byte[] {0x25});
        files.get(3).toFile().setWritable(false);

        Files.writeString(files.get(0), "Take me on an internship at Tinkoff");
        return files;
    }

    @Test
    @DisplayName("readable, extensions and and")
    void readableWriteableAnd() {
        AbstractFilter filter = Filters.hasOneOfExtensions("txt").and(writeable());
        assertThat(testFiles.stream().filter(entry -> {
            try {
                return filter.accept(entry);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).containsExactlyInAnyOrder(testFiles.get(0), testFiles.get(1), testFiles.get(2));
    }

    @Test
    @DisplayName("magicNumbers")
    void magicNumbers() {
        AbstractFilter filter = Filters.magicNumber((byte) 0x25);
        assertThat(testFiles.stream().filter(entry -> {
            try {
                return filter.accept(entry);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).containsExactlyInAnyOrder(testFiles.get(1), testFiles.get(4));
    }

    @Test
    @DisplayName("largerThen")
    void largerThen() {
        AbstractFilter filter = Filters.largerThen(10L);
        assertThat(testFiles.stream().filter(entry -> {
            try {
                return filter.accept(entry);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).containsExactlyInAnyOrder(testFiles.get(0));
    }

    @Test
    @DisplayName("regexContain")
    void regexContains() {
        AbstractFilter filter = Filters.regexContains("off.*txt");
        assertThat(testFiles.stream().filter(entry -> {
            try {
                return filter.accept(entry);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).containsExactlyInAnyOrder(testFiles.get(0), testFiles.get(1));
    }

    @Test
    @DisplayName("globMatcher")
    void globMatcher() {
        AbstractFilter filter = Filters.globMatcher("*.png");
        assertThat(testFiles.stream().filter(entry -> {
            try {
                return filter.accept(entry);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).containsExactlyInAnyOrder(testFiles.get(4), testFiles.get(5));
    }

}
