package edu.hw2.ex3.connection_manager;

import edu.hw2.ex3.connection.Connection;
import edu.hw2.ex3.connection.FaultyConnection;
import edu.hw2.ex3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_BOUND = 10;

    private static Random random = new Random();

    @Override
    public Connection getConnection() {
        if (random.nextInt(0, RANDOM_BOUND) == 0) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
