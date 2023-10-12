package edu.hw2.ex3;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
