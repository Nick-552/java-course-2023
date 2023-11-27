package edu.hw6.ex4;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;

class FileOutputStreamChainTest {

    @Test
    void writeToFile() {
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
        FileOutputStreamChain.writeToFile(file, "Programming is learned by writing programs. ― Brian Kernighan");
        assertThat(file).hasContent("Programming is learned by writing programs. ― Brian Kernighan");
    }
}
