package edu.hw9.ex2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileSearchTask extends RecursiveTask<List<File>> {

    private final File current;

    private final Predicate<File> predicate;

    @Override
    protected List<File> compute() {
        List<File> found = new ArrayList<>();
        List<FileSearchTask> tasks = new ArrayList<>();
        if (predicate.test(current)) {
            found.add(current);
        }
        if (!current.isDirectory()) {
            return found;
        }
        for (File file : Objects.requireNonNull(current.listFiles())) {
            FileSearchTask task = new FileSearchTask(file, predicate);
            task.fork();
            tasks.add(task);
        }
        for (var task : tasks) {
            found.addAll(task.join());
        }
        return found;
    }
}
