package edu.hw3.ex3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordFrequency {
    private WordFrequency() {}

    public static <T> Map<T, Integer> count(List<T> words) {
        Map<T, Integer> ret = new HashMap<>();
        if (words == null) {
            throw new IllegalArgumentException("words must not be null");
        }
        for (T word : words) {
            ret.putIfAbsent(word, 0);
            ret.put(word, ret.get(word) + 1);
        }
        return ret;
    }
}
