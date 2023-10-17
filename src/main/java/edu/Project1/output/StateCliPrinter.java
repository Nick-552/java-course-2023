package edu.Project1.output;

import edu.Project1.State.GameState;
import edu.Project1.guess.GuessResult;
import edu.Project1.guess.GuessingWord;
import java.util.Arrays;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class StateCliPrinter implements StatePrinter {
    @Override
    public void printGuessingResult(GuessResult guessResult) {
        System.out.println(guessResult.message());
        printGameState(guessResult.gameState(), guessResult.guessingWord());
    }

    @Override
    public void printStartMessage(GameState gameState, GuessingWord guessingWord) {
        System.out.println("Hangman Game");
        System.out.println();
        System.out.println("Rules:");
        System.out.println("In the beginning of the game, a random word is generated");
        System.out.println("The goal of the game is to guess this word by guessing letters it consists of");
        System.out.println("On each turn you should guess one letter");
        System.out.println(
            "If the letter guessed correctly,"
            + " it will be opened in the word and you will NOT lose an attempt"
        );
        System.out.println("If guessing letter is not in the word, you lose one attempt");
        System.out.println("You have " + gameState.getMaxAttempts() + " attempts");
        printGameState(gameState, guessingWord);
    }

    @Override
    public void printGameState(GameState gameState, GuessingWord guessingWord) {
        System.out.println("Word: " + (Arrays.toString(guessingWord.getUserWord())));
        System.out.println("Alphabet: " + (Arrays.toString(guessingWord.getAlphabet())));
        System.out.println("Remaining attempts: " + (gameState.getMaxAttempts() - gameState.getAttempts()));
        System.out.println();
    }
}
