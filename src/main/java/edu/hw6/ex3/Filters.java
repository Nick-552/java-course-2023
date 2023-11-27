package edu.hw6.ex3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.PathMatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Filters {

    public static AbstractFilter largerThen(Long size) {
        return entry -> entry.toFile().length() > size;
    }

    public static AbstractFilter writeable() {
        return entry -> entry.toFile().canWrite();
    }

    public static AbstractFilter hasOneOfExtensions(String... extensionsAllowed) {
        return entry -> {
            String fileName = entry.getFileName().toString();
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
            for (String extension : extensionsAllowed) {
                if (extension.equals(fileExtension)) {
                    return true;
                }
            }
            return false;
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return entry -> {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(entry.getFileName().toString());
            return matcher.find();
        };
    }

    public static AbstractFilter globMatcher(String glob) {
        return entry -> {
            PathMatcher pathMatcher = entry.getParent().getFileSystem().getPathMatcher("glob:" + glob);
            return pathMatcher.matches(entry.getFileName());
        };
    }

    public static AbstractFilter magicNumber(byte... magicNumbers) {
        return entry -> {
            File file = entry.toFile();
            try (InputStream inputStream = new FileInputStream(file)) {
                for (byte magicNumber : magicNumbers) {
                    if (inputStream.read() != magicNumber) {
                        return false;
                    }
                }
            } catch (IOException e) {
                return false;
            }
            return true;
        };
    }
}
