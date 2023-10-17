package edu.Project1;

import edu.Project1.State.GameState;
import edu.Project1.State.GameStatus;
import edu.Project1.dictionary.DefaultDictionary;
import edu.Project1.dictionary.Dictionary;
import edu.Project1.guess.GuessingSession;
import edu.Project1.guess.GuessingWord;
import edu.Project1.input.CliReader;
import edu.Project1.input.Reader;
import edu.Project1.output.StateCliPrinter;
import edu.Project1.output.StatePrinter;

public class HangmanGame {
    private final GuessingWord guessingWord;
    private final GameState gameState;
    private final Reader reader;
    private final StatePrinter statePrinter;

    HangmanGame() {
        Dictionary dictionary = new DefaultDictionary();
        reader = new CliReader();
        statePrinter = new StateCliPrinter();
        gameState = new GameState();
        guessingWord = new GuessingWord(dictionary.getRandomWord(), dictionary.getAlphabet());
    }

    HangmanGame(String[] bankOgWords) {
        Dictionary dictionary = new DefaultDictionary(bankOgWords);
        reader = new CliReader();
        statePrinter = new StateCliPrinter();
        gameState = new GameState();
        guessingWord = new GuessingWord(dictionary.getRandomWord(), dictionary.getAlphabet());
    }

    public void run() {
        gameState.setGameStatus(GameStatus.RUNNING);
        statePrinter.printStartMessage(gameState, guessingWord);
        while (gameState.getGameStatus() != GameStatus.STOPPED) {
            new GuessingSession(guessingWord, gameState, reader, statePrinter).run();
        }
    }
}
