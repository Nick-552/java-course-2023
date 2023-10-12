package edu.hw2.ex3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        // do smth
    }

    @Override
    public void close() {
        // closing connection
        LOGGER.info("Connection closed");
    }
}
