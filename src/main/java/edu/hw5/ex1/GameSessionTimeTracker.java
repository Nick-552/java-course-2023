package edu.hw5.ex1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class GameSessionTimeTracker {

    private static final Pattern SESSION_PATTERN = Pattern.compile(
        "^(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})$"
    );

    private static final DateTimeFormatter SESSION_DATE_TIME_FORMATTER = DateTimeFormatter
        .ofPattern("yyyy-MM-dd, HH:mm");

    private GameSessionTimeTracker() {}

    public static Duration getAverageGameSessionDuration(@NotNull List<String> sessions) {
        if (sessions.isEmpty()) {
            throw new IllegalArgumentException("You should provide at least 1 session");
        }
        Duration durationSum = Duration.ZERO;
        for (String session : sessions) {
            durationSum = durationSum.plus(getGameSessionDuration(session));
        }
        return durationSum.dividedBy(sessions.size()).truncatedTo(ChronoUnit.MINUTES);
    }

    private static Duration getGameSessionDuration(String session) {
        Matcher sessionMatcher = SESSION_PATTERN.matcher(session);
        if (!sessionMatcher.matches()) {
            throw new IllegalArgumentException(
                "Wrong format of session, should be \"0000-00-00, 00:00 - 0000-00-00, 00:00\""
            );
        }
        LocalDateTime start = LocalDateTime.parse(sessionMatcher.group(1), SESSION_DATE_TIME_FORMATTER);
        LocalDateTime end = LocalDateTime.parse(sessionMatcher.group(2), SESSION_DATE_TIME_FORMATTER);
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("Start of session later than end");
        }
        return Duration.between(start, end);
    }
}
