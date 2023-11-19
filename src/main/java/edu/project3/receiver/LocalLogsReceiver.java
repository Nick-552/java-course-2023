package edu.project3.receiver;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocalLogsReceiver implements LogsReceiver {

    private List<Path> paths;

    public LocalLogsReceiver(String path) {
        paths = getAllPaths(path);
    }

    @Override
    public List<String> receiveLogs() {
        List<String> logs = new ArrayList<>();
        paths.forEach(p -> logs.addAll(readLogsFromFile(p)));
        return logs;
    }

    private static List<String> readLogsFromFile(Path p) {
        if (!Files.isRegularFile(p)) {
            return List.of();
        }
        try {
            return Files.readAllLines(p)
                .stream()
                .map(string -> string + " \"" + p.toString().substring(p.toString().lastIndexOf('\\')) + "\"")
                .toList();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from file", e);
        }
    }

    private static List<Path> getAllPaths(String path) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + path);
        try (Stream<Path> pathStream = Files.find(
            Path.of(""),
            Integer.MAX_VALUE,
            (currentPath, fileAttributes) -> !fileAttributes.isDirectory() && pathMatcher.matches(currentPath)
        )) {
            return pathStream.toList();
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot obtain files");
        }
    }
}
