package edu.project3.receiver;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Receivers {

    public static LogsReceiver getLogReceiver(String path) {
        if (path.startsWith("http")) {
            return new UrlLogReceiver(path);
        } else {
            return new LocalLogsReceiver(path);
        }
    }
}
