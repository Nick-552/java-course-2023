package edu.hw2.ex3.connection;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
