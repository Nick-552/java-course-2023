package edu.hw2.ex3.connection;

import edu.hw2.ex3.exception.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int RANDOM_BOUND = 10;

    private final static int LOW_SPEED_BOUND = 4;

    private static Random random = new Random();

    @Override
    public void execute(String command) {
        int randomInt = random.nextInt(0, RANDOM_BOUND);
        if (randomInt == 0) {
            throw new ConnectionException("Failed to connect (no connection)");
        } else if (randomInt < LOW_SPEED_BOUND) {
            throw new ConnectionException("Failed to connect (low internet speed)");
        }
        // do something
        LOGGER.info("Executed successfully");
    }

    @Override
    public void close() {
        // closing connection
        LOGGER.info("Connection closed");
    }
}
