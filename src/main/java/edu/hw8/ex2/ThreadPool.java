package edu.hw8.ex2;

public interface ThreadPool extends AutoCloseable {

    void start();

    void execute(Runnable runnable);
}
