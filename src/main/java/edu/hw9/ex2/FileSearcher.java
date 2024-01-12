package edu.hw9.ex2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileSearcher {

    private static final ForkJoinPool FORK_JOIN_POOL = ForkJoinPool.commonPool();

    public static List<File> searchFiles(File startDirectory, Predicate<File> predicate) {
        return FORK_JOIN_POOL.invoke(new FileSearchTask(startDirectory, predicate));
    }
}
