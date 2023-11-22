package edu.hw7.ex1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger counter = new AtomicInteger(0);

    int getValue() {
        return counter.get();
    }

    void increment() {
        counter.incrementAndGet();
    }
}
