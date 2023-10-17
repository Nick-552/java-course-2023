package edu.Project1.output;

import edu.Project1.State.GameState;
import edu.Project1.guess.GuessResult;
import edu.Project1.guess.GuessingWord;

public interface StatePrinter {
    void printGameState(GameState gameState, GuessingWord guessingWord);

    void printStartMessage(GameState gameState, GuessingWord guessingWord);

    void printGuessingResult(GuessResult guessResult);
}
