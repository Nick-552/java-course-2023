package edu.Project1.output;

import edu.Project1.State.GameState;
import edu.Project1.guess.GuessResult;
import edu.Project1.guess.GuessingWord;
import java.util.Arrays;

public class StateCliPrinter implements StatePrinter {
    @Override
    public void printGuessingResult(GuessResult guessResult) {
        print(guessResult.message());
        printGameState(guessResult.gameState(), guessResult.guessingWord());
    }

    @Override
    public void printStartMessage(GameState gameState, GuessingWord guessingWord) {
        print("Hangman Game");
        print("");
        print("Rules:");
        print("In the beginning of the game, a random word is generated");
        print("The goal of the game is to guess this word by guessing letters it consists of");
        print("On each turn you should guess one letter");
        print(
            "If the letter guessed correctly,"
            + " it will be opened in the word and you will NOT lose an attempt"
        );
        print("If guessing letter is not in the word, you lose one attempt");
        print("You have " + gameState.getMaxAttempts() + " attempts");
        printGameState(gameState, guessingWord);
    }

    @Override
    public void printGameState(GameState gameState, GuessingWord guessingWord) {
        print("Word: " + guessingWord.getUserWord());
        print("Remaining attempts: " + (gameState.getMaxAttempts() - gameState.getAttempts()));
        print("");
    }

    @Override
    public void printAlphabet(GuessingWord guessingWord) {
        print("Alphabet: " + (Arrays.toString(guessingWord.getAlphabet())));
    }


    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void print(String output) {
        System.out.println(output);
    }
}
