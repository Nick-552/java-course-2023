package edu.project4.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ListUtils {

    public static <T> T getRandomElement(List<T> list) {
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }
}
