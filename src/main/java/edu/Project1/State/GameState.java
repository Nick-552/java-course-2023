package edu.Project1.State;

public class GameState {
    private static final int DEFAULT_AMOUNT_OF_ATTEMPTS = 7;
    private final int maxAttempts;
    private GameStatus gameStatus;
    private int attempts;

    public GameState(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    public GameState() {
        this.maxAttempts = DEFAULT_AMOUNT_OF_ATTEMPTS;
        this.attempts = 0;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getAttempts() {
        return attempts;
    }

    public void stop() {
        setGameStatus(GameStatus.STOPPED);
    }

    public void doAttempt() {
        if (++attempts >= maxAttempts) {
            this.stop();
        }
    }
}
