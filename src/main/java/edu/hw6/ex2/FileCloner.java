package edu.hw6.ex2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileCloner {

    public static Path clone(Path path) {
        Path directory = path.getParent();
        String file = path.getFileName().toString();
        int indexOfDot = file.lastIndexOf('.');
        if (indexOfDot == -1) {
            indexOfDot = file.length();
        }
        String fileName = file.substring(0, indexOfDot);
        String fileExtension = file.substring(indexOfDot);

        Path clonePath = directory.resolve(fileName + " — копия" + fileExtension);

        int copyIdx = 2;
        synchronized (FileCloner.class) {
            while (clonePath.toFile().exists()) {
                clonePath = directory.resolve(fileName + " — копия (%s)".formatted(copyIdx) + fileExtension);
                copyIdx++;
            }
            try {
                Files.copy(path, clonePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return clonePath;
    }

}
