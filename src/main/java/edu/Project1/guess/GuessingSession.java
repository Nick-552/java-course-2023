package edu.Project1.guess;

import edu.Project1.State.GameState;
import edu.Project1.State.GameStatus;
import edu.Project1.input.Reader;
import edu.Project1.output.StatePrinter;
import org.jetbrains.annotations.NotNull;

public class GuessingSession {
    private final GuessingWord guessingWord;
    private final GameState gameState;

    private final Reader reader;
    private final StatePrinter statePrinter;

    public GuessingSession(GuessingWord guessingWord, GameState gameState, Reader reader, StatePrinter statePrinter) {
        this.guessingWord = guessingWord;
        this.gameState = gameState;
        this.reader = reader;
        this.statePrinter = statePrinter;
    }

    public void run() {
        statePrinter.printAlphabet(guessingWord);
        String guessingString = reader.getLetter();
        GuessResult guessResult = guessResult(guessingString);
        statePrinter.printGuessingResult(guessResult);
    }

    @NotNull GuessResult guessResult(String guessingString) {
        if (guessingString.equals("giveUp")) {
            gameState.stop();
            return new GuessResult.Defeat(guessingWord, gameState);
        } else if (guessingString.length() != 1 || !guessingWord.alphabetContains(guessingString)) {
            return new GuessResult.InvalidGuess(guessingWord, gameState);
        } else {
            return guessChar(guessingString.charAt(0));
        }
    }

    @NotNull GuessResult guessChar(char guess) {
        GuessResult guessResult;
        if (guessingWord.userWordContains(guess)) {                                     // already guessed
            guessResult = new GuessResult.AlreadyGuessed(guessingWord, gameState);
        } else if (guessingWord.isAlreadyTried(guess)) {                                // already tried
            guessResult = new GuessResult.AlreadyTried(guessingWord, gameState);
        } else if (!guessingWord.actualWordContains(guess)) {                           // failed guess
            guessingWord.tryChar(guess);
            gameState.doAttempt();
            if (gameState.getGameStatus() == GameStatus.STOPPED) {
                guessResult = new  GuessResult.Defeat(guessingWord, gameState);
            } else {
                guessResult = new GuessResult.FailedGuess(guessingWord, gameState);
            }
        } else {                                                                        // correct guess
            guessingWord.open(guess);
            if (guessingWord.isGuessed()) {
                gameState.stop();
                guessResult = new GuessResult.Win(guessingWord, gameState);
            } else {
                guessResult = new GuessResult.SuccessfulGuess(guessingWord, gameState);
            }
        }
        return guessResult;
    }
}
