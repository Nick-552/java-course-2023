package edu.project3.model;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Builder;

@Builder
public record NginxLog(
    String ipAddress, String remoteUser, OffsetDateTime timeStamp,
    Request request,
    Integer status,
    Integer bodyBytesSent,
    String referer,
    String httpUserAgent,
    String file) {

    @SuppressWarnings("checkstyle:MagicNumber")
    public static NginxLog parseStringToLog(String logString) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        final Pattern logPattern =
            Pattern.compile("^(.*) - (.*) \\[(.*)] \"(.*) (.*) (.*)\" (\\d+) (\\d+) \"(.*)\" \"(.*)\" \"(.*)\"$");
        Matcher matcher = logPattern.matcher(logString);
        if (matcher.matches()) {
            return NginxLog.builder()
                .ipAddress(matcher.group(1))
                .remoteUser(matcher.group(2))
                .timeStamp(OffsetDateTime.parse(matcher.group(3), formatter))
                .request(Request.builder()
                    .type(matcher.group(4))
                    .resource(matcher.group(5))
                    .protocol(matcher.group(6))
                    .build())
                .status(Integer.parseInt(matcher.group(7)))
                .bodyBytesSent(Integer.parseInt(matcher.group(8)))
                .referer(matcher.group(9))
                .httpUserAgent(matcher.group(10))
                .file(matcher.group(11))
                .build();
        } else {
            return null;
        }
    }
}
