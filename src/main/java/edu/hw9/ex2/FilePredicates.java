package edu.hw9.ex2;

import java.io.File;
import java.util.function.Predicate;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;

@UtilityClass
public class FilePredicates {

    public static Predicate<File> isDirectoryAndHasAtLeast(int nFiles) {
        return file -> {
            if (!file.isDirectory()) {
                return false;
            }
            var files = file.listFiles();
            return files != null && files.length >= nFiles;
        };
    }

    public static Predicate<File> isExtension(String... extension) {
        return file -> file.isFile() && FilenameUtils.isExtension(file.getName(), extension);
    }

    public static Predicate<File> hasSizeBetween(int from, int to) {
        return file -> file.isFile() && file.length() >= from && file.length() < to;
    }
}
