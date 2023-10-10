package edu.hw1.ex1;

public final class TimeToSecondsConverter {
    private static final int MAX_SECONDS = 60;

    private TimeToSecondsConverter() {}

    public static int convert(String time) {
        if (!time.matches("^\\d+:\\d+$")) { //checks if format is wrong
            return -1;
        }
        String[] minutesAndSeconds = time.split(":");
        int minutes = Integer.parseInt(minutesAndSeconds[0]);
        int seconds = Integer.parseInt(minutesAndSeconds[1]);
        if (seconds >= MAX_SECONDS) {
            return -1;
        } else {
            return minutes * MAX_SECONDS + seconds;
        }
    }
}
