package edu.hw8.ex2;

public interface ThreadPool extends AutoCloseable {

    void start();

    void shutdown();

    void execute(Runnable runnable);
}
