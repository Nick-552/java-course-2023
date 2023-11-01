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
    private final Dictionary dictionary;

    HangmanGame() {
        reader = new CliReader();
        statePrinter = new StateCliPrinter();
        dictionary = new DefaultDictionary();
        gameState = new GameState();
        guessingWord = new GuessingWord(dictionary);
    }

    HangmanGame(Reader reader, StatePrinter statePrinter, Dictionary dictionary) {
        this.reader = reader;
        this.statePrinter = statePrinter;
        this.dictionary = dictionary;
        gameState = new GameState();
        guessingWord = new GuessingWord(dictionary);
    }

    public void run() {
        gameState.setGameStatus(GameStatus.RUNNING);
        statePrinter.printStartMessage(gameState, guessingWord);
        while (gameState.getGameStatus() != GameStatus.STOPPED) {
            new GuessingSession(guessingWord, gameState, reader, statePrinter).run();
        }
    }
}
