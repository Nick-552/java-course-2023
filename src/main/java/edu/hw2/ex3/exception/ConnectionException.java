package edu.hw2.ex3.exception;

public class ConnectionException  extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
