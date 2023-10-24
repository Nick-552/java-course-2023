package edu.hw3.ex1;

import org.jetbrains.annotations.NotNull;

public final class AtbashEncoder {

    private AtbashEncoder() {}

    public static @NotNull String encode(String message) {
        if (message == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(message);
        for (int i = 0; i < stringBuilder.length(); i++) {
            char letter = stringBuilder.charAt(i);
            if (letter >= 'A' && letter <= 'Z') {
                stringBuilder.setCharAt(i, (char) ('Z' - letter + 'A'));
            } else if (letter >= 'a' && letter <= 'z') {
                stringBuilder.setCharAt(i, (char) ('z' - letter + 'a'));
            }
        }
        return stringBuilder.toString();
    }
}
