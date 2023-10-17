package edu.Project1.State;

public class GameState {
    private GameStatus gameStatus;
    private static final int DEFAULT_AMOUNT_OF_ATTEMPTS = 7;
    private final int maxAttempts;
    private int attempts;

    public GameState(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    public GameState() {
        this.maxAttempts = DEFAULT_AMOUNT_OF_ATTEMPTS;
        this.attempts = 0;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void stop() {
        setGameStatus(GameStatus.STOPPED);
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public void doAttempt() {
        if (++attempts >= maxAttempts) {
            this.stop();
        }
    }
}
