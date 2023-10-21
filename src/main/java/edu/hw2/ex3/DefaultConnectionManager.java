package edu.hw2.ex3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_BOUND = 10;

    private final Random connectionRandom;

    private final Random executionRandom;

    public DefaultConnectionManager(Random connectionRandom, Random executeRandom) {
        this.connectionRandom = connectionRandom;
        this.executionRandom = executeRandom;
    }

    @Override
    public Connection getConnection() {
        if (connectionRandom.nextInt(0, RANDOM_BOUND) == 0) {
            return new FaultyConnection(executionRandom);
        } else {
            return new StableConnection();
        }
    }
}
