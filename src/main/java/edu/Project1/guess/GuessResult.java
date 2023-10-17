package edu.Project1.guess;

import edu.Project1.State.GameState;
import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {
    GuessingWord guessingWord();

    GameState gameState();

    @NotNull String message();

    record Defeat(GuessingWord guessingWord, GameState gameState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You lost";
        }
    }

    record Win(GuessingWord guessingWord, GameState gameState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You won";
        }
    }

    record SuccessfulGuess(GuessingWord guessingWord, GameState gameState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Hit";
        }
    }

    record FailedGuess(GuessingWord guessingWord, GameState gameState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Missed";
        }
    }

    record AlreadyGuessed(GuessingWord guessingWord, GameState gameState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "This letter is already guessed";
        }
    }

    record InvalidGuess(GuessingWord guessingWord, GameState gameState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You entered the wrong character(s)";
        }
    }

    record AlreadyTried(GuessingWord guessingWord, GameState gameState) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You tried this letter already";
        }
    }
}
