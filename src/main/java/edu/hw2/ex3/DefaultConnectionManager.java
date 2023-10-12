package edu.hw2.ex3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_BOUND = 10;
    private final Random connectionRandom;
    private final Random executeRandom;

    public DefaultConnectionManager(Random connectionRandom, Random executeRandom) {
        this.connectionRandom = connectionRandom;
        this.executeRandom = executeRandom;
    }

    @Override
    public Connection getConnection() {
        int i = connectionRandom.nextInt(0, RANDOM_BOUND);
        if (i == 0) {
            return new FaultyConnection(executeRandom);
        } else {
            return new StableConnection();
        }
    }
}
