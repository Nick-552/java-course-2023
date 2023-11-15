package edu.project3.receiver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalLogsReceiver implements LogsReceiver {

    @Override
    public List<String> receiveLogs(String glob) {
        List<String> logs = new ArrayList<>();
        try {
            Files.newDirectoryStream(Paths.get(""), glob).forEach(p -> {
                if (!Files.isRegularFile(p)) {
                    return;
                }
                try {
                    logs.addAll(Files.readAllLines(p));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return logs;
    }
}
