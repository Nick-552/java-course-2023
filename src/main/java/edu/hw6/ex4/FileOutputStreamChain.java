package edu.hw6.ex4;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileOutputStreamChain {

    public static void writeToFile(Path path, String message) {
        try (
            OutputStream outputStream = Files.newOutputStream(path);
            OutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                bufferedOutputStream,
                StandardCharsets.UTF_8
            );
            PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {
            printWriter.print(message);
            printWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
