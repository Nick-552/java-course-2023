package edu.hw3.ex1;

import org.jetbrains.annotations.NotNull;

public final class AtbashEncoder {
    private static final char UPPER_CASE_START = 'A';
    private static final char UPPER_CASE_END = 'Z';
    private static final char LOWER_CASE_START = 'a';
    private static final char LOWER_CASE_END = 'z';

    private AtbashEncoder() {}

    public static @NotNull String encode(String message) {
        if (message == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(message);
        for (int i = 0; i < stringBuilder.length(); i++) {
            char letter = stringBuilder.charAt(i);
            if (letter >= UPPER_CASE_START && letter <= UPPER_CASE_END) {
                stringBuilder.setCharAt(i, (char) (UPPER_CASE_END - letter + UPPER_CASE_START));
            } else if (letter >= LOWER_CASE_START && letter <= LOWER_CASE_END) {
                stringBuilder.setCharAt(i, (char) (LOWER_CASE_END - letter + LOWER_CASE_START));
            }
        }
        return stringBuilder.toString();
    }
}
