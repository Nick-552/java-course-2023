package edu.hw6.ex2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class FileClonerTest {

    @Test
    void testClone() {
        Path folder;
        try {
            folder = Files.createTempDirectory("folder");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path file;
        try {
            file = Files.createFile(folder.resolve("Tinkoff top secrets.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path copyPath1Expected = Path.of(folder.toString(), "Tinkoff top secrets — копия.txt");
        Path copyPath1Actual = FileCloner.clone(file);
        assertThat(copyPath1Actual).isEqualTo(copyPath1Expected);
        assertThat(copyPath1Actual.toFile()).exists();

        Path copyPath2Expected = Path.of(folder.toString(), "Tinkoff top secrets — копия (2).txt");
        Path copyPath2Actual = FileCloner.clone(file);
        assertThat(copyPath2Actual).isEqualTo(copyPath2Expected);
        assertThat(copyPath2Actual.toFile()).exists();
    }
}
