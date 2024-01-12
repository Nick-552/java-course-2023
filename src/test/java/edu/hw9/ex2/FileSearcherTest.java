package edu.hw9.ex2;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

class FileSearcherTest {

    private static final int SIZE_FROM = 100;

    private static final int MIN_FILES_IN_LARGE_DIR = 10;

    private static final Random RANDOM = new Random();

    @TempDir
    private static Path testDir;

    private static final List<File> tinkoffExtension = new ArrayList<>();

    private static final List<File> largeDirs = new ArrayList<>();

    private static final List<File> filesWithSizeFrom = new ArrayList<>();

    @SneakyThrows
    @BeforeAll
    public static void fillDirectory() {
        Path dir1 = createDirectoryInside(testDir);
        Path dir2 = createDirectoryInside(dir1);
        Path dir3 = createDirectoryInside(testDir);
        tinkoffExtension.addAll(fillDirWithFiles(dir3, ".tinkoff", RANDOM.nextInt(MIN_FILES_IN_LARGE_DIR)));
        tinkoffExtension.addAll(fillDirWithFiles(dir2, ".tinkoff", RANDOM.nextInt(MIN_FILES_IN_LARGE_DIR)));
        largeDirs.add(dir1.toFile());
        largeDirs.add(testDir.toFile());
        fillDirWithFiles(dir1, ".java", RANDOM.nextInt(MIN_FILES_IN_LARGE_DIR, 1000));
        fillDirWithFiles(testDir, ".kt", RANDOM.nextInt(MIN_FILES_IN_LARGE_DIR, 1000));
        File largeFile1 = Files.createFile(dir1.resolve("largeFile1.txt")).toFile();
        File largeFile2 = Files.createFile(testDir.resolve("largeFile2.txt")).toFile();
        try (
            FileWriter fileWriter1 = new FileWriter(largeFile1);
            FileWriter fileWriter2 = new FileWriter(largeFile2)
        ) {
            while (largeFile1.length() < SIZE_FROM) {
                fileWriter1.write("some data");
                fileWriter1.flush();
            }
            while (largeFile2.length() < SIZE_FROM) {
                fileWriter2.write("some data");
                fileWriter2.flush();
            }
            filesWithSizeFrom.add(largeFile1);
            filesWithSizeFrom.add(largeFile2);
        }
    }

    @SneakyThrows
    public static List<File> fillDirWithFiles(Path path, String extension, int nFiles) {
        var filePaths = new ArrayList<File>();
        for (int i = 0; i < nFiles; i++) {
            Path newFile = path.resolve("file" + UUID.randomUUID() + extension);
            filePaths.add(Files.createFile(newFile).toFile());
        }
        return filePaths;
    }

    @SneakyThrows
    public static Path createDirectoryInside(Path path) {
        Path directory = path.resolve("directory" + UUID.randomUUID());
        return Files.createDirectory(directory);
    }

    @Test
    void searchLargeDirectories() {
        assertThat(
            FileSearcher.searchFiles(testDir.toFile(), FilePredicates.isDirectoryAndHasAtLeast(MIN_FILES_IN_LARGE_DIR))
        ).containsExactlyInAnyOrderElementsOf(
            largeDirs
        );
    }

    @Test
    void searchFilesWithSizeBetween() {
        assertThat(
            FileSearcher.searchFiles(testDir.toFile(), FilePredicates.hasSizeBetween(SIZE_FROM, Integer.MAX_VALUE))
        ).containsExactlyInAnyOrderElementsOf(
            filesWithSizeFrom
        );
    }

    @Test
    void searchFilesWithExtension() {
        assertThat(
            FileSearcher.searchFiles(testDir.toFile(), FilePredicates.isExtension("tinkoff"))
        ).containsExactlyInAnyOrderElementsOf(
            tinkoffExtension
        );
    }
}
