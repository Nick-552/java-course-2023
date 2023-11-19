package edu.project3.receiver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UrlLogReceiver implements LogsReceiver {

    private final String url;

    @Override
    public List<String> receiveLogs() {
        try {
            InputStream inputStream = new URI(url).toURL().openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.lines().map(line -> line + " \"" + url.substring(url.lastIndexOf('/')) + "\"").toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
