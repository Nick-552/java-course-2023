package edu.hw3.ex3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WordFrequency {
    private WordFrequency() {}

    public static <T> Map<T, Integer> count(List<T> words) {
        Map<T, Integer> ret = new HashMap<>();
        if (words == null) {
            return ret;
        }
        for (T word : words) {
            if (!ret.containsKey(word)) {
                ret.put(word, 1);
            } else {
                ret.put(word, ret.get(word) + 1);
            }
        }
        return ret;
    }
}
