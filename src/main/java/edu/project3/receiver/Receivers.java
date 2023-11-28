package edu.project3.receiver;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Receivers {

    public static LogsReceiver getLogReceiver(String path) {
        if (path.startsWith("http")) {
            return new UrlLogReceiver(path);
        } else {
            return new LocalLogsReceiver(path);
        }
    }
}
