package edu.hw2.ex3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager connectionManager;

    private final static Logger LOGGER = LogManager.getLogger();

    private final int maxAttempts;

    public PopularCommandExecutor(int maxAttempts, long getConnectionRandomSeed, long executionRandomSeed) {
        this.maxAttempts = maxAttempts;
        Random getConnectionRandom = new Random(getConnectionRandomSeed);
        Random executeRandom = new Random(executionRandomSeed);
        this.connectionManager = new DefaultConnectionManager(getConnectionRandom, executeRandom);
    }

    public PopularCommandExecutor(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        Random getConnectionRandom = new Random();
        Random executeRandom = new Random();
        this.connectionManager = new DefaultConnectionManager(getConnectionRandom, executeRandom);
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        Connection connection = connectionManager.getConnection();
        ConnectionException connectionExceptionLast = null;
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                connection.execute(command);
                break;
            } catch (Exception connectionException) {
                attempts++;
                connectionExceptionLast = (ConnectionException) connectionException;
            }
        }
        try {
            connection.close();
        } catch (Exception e) {
            throw new ConnectionException(e);
        }
        if (attempts == maxAttempts) {
            LOGGER.info("Execution failed");
            throw connectionExceptionLast;
        } else {
            LOGGER.info("Executed succesfully");
        }
    }
}
