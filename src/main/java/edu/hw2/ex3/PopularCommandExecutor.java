package edu.hw2.ex3;

import edu.hw2.ex3.connection.Connection;
import edu.hw2.ex3.connection_manager.ConnectionManager;
import edu.hw2.ex3.exception.ConnectionException;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private final ConnectionManager connectionManager;

    private final static Logger LOGGER = LogManager.getLogger();

    private final int maxAttempts;

    public PopularCommandExecutor(int maxAttempts, ConnectionManager connectionManager) {
        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("maxAttempts should be greater than 0");
        }
        this.maxAttempts = maxAttempts;
        this.connectionManager = connectionManager;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        Connection connection = Objects.requireNonNull(connectionManager.getConnection());
        ConnectionException connectionExceptionLast = null;
        int failedAttempts = 0;
        while (failedAttempts < maxAttempts) {
            try {
                connection.execute(command);
                break;
            } catch (Exception e) {
                failedAttempts++;
                if (e instanceof ConnectionException) {
                    connectionExceptionLast = (ConnectionException) e;
                } else {
                    connectionExceptionLast = new ConnectionException(
                        "Unexpected exception has occurred while executing command.", e
                    );
                }
            }
        }
        try {
            connection.close();
        } catch (Exception e) {
            LOGGER.info("Failed to close connection");
            throw new ConnectionException(e);
        } finally {
            if (failedAttempts == maxAttempts) {
                LOGGER.info("Execution failed");
                throw Objects.requireNonNull(connectionExceptionLast);
            }
        }

    }
}
