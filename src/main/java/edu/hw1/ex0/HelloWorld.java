package edu.hw1.ex0;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class HelloWorld {

    private final static Logger LOGGER = LogManager.getLogger();

    private HelloWorld() {}

    public static void logInfoHelloWorld() {
        LOGGER.info("Hello and welcome!");
    }
}
