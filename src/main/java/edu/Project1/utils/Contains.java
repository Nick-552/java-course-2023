package edu.Project1.utils;

public final class Contains {
    private Contains() {}

    public static <E> boolean contains(E element, E[] array) {
        for (E candidate: array) {
            if (element.equals(candidate)) {
                return true;
            }
        }
        return false;
    }
}
