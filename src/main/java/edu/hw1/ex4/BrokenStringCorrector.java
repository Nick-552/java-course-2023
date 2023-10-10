package edu.hw1.ex4;

public final class BrokenStringCorrector {
    private BrokenStringCorrector() {}

    public static String fixString(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i = 1; i < stringBuilder.length(); i += 2) {
            char tmp = stringBuilder.charAt(i - 1);
            stringBuilder.setCharAt(i - 1, stringBuilder.charAt(i));
            stringBuilder.setCharAt(i, tmp);
        }
        return stringBuilder.toString();
    }
}
