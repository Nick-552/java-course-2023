package edu.Project1;

import edu.Project1.dictionary.DefaultDictionary;
import edu.Project1.input.Reader;
import edu.Project1.output.StateCliPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Queue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HangmanGameTest {

    private HangmanGame hangmanGame;

    private final Queue<String> input = new ArrayDeque<>();
    private final Queue<String> output = new ArrayDeque<>();


    @BeforeEach
    void prepareForTest() {
        input.clear();
        output.clear();
        hangmanGame = new HangmanGame(
            new Reader() {
                @Override
                public String getLetter() {
                    output.offer("Guess a letter:");
                    return input.poll();
                }
            },
            new StateCliPrinter() {
                @Override
                public void print(String out) {
                    output.add(out);
                }
            },
            new DefaultDictionary(new String[]{"word"})
        );
    }


    @Test
    @DisplayName("Give up case")
    void testGiveUp(){
        input.offer("giveUp");
        hangmanGame.run();
        assertThat(output.toArray()).isEqualTo(new String[]{
            "Hangman Game",
            "",
            "Rules:",
            "In the beginning of the game, a random word is generated",
            "The goal of the game is to guess this word by guessing letters it consists of",
            "On each turn you should guess one letter",
            "If the letter guessed correctly, it will be opened in the word and you will NOT lose an attempt",
            "If guessing letter is not in the word, you lose one attempt",
            "You have 7 attempts",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "You lost",
            "Word: ****",
            "Remaining attempts: 7",
            ""
        });
    }

    @Test
    @DisplayName("Win case (also checking hits)")
    void testWinAndHits(){
        input.offer("w");
        input.offer("o");
        input.offer("r");
        input.offer("d");
        hangmanGame.run();
        assertThat(output.toArray()).isEqualTo(new String[]{
            "Hangman Game",
            "",
            "Rules:",
            "In the beginning of the game, a random word is generated",
            "The goal of the game is to guess this word by guessing letters it consists of",
            "On each turn you should guess one letter",
            "If the letter guessed correctly, it will be opened in the word and you will NOT lose an attempt",
            "If guessing letter is not in the word, you lose one attempt",
            "You have 7 attempts",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "Hit",
            "Word: w***",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w̶, x, y, z]",
            "Guess a letter:",
            "Hit",
            "Word: wo**",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o̶, p, q, r, s, t, u, v, w̶, x, y, z]",
            "Guess a letter:",
            "Hit",
            "Word: wor*",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o̶, p, q, r̶, s, t, u, v, w̶, x, y, z]",
            "Guess a letter:",
            "You won",
            "Word: word",
            "Remaining attempts: 7",
            ""
        });
    }

    @Test
    @DisplayName("Lose case (also checking misses)")
    void testLoseAndMisses(){
        input.offer("q");
        input.offer("e");
        input.offer("t");
        input.offer("y");
        input.offer("u");
        input.offer("i");
        input.offer("p");
        hangmanGame.run();
        assertThat(output.toArray()).isEqualTo(new String[]{
            "Hangman Game",
            "",
            "Rules:",
            "In the beginning of the game, a random word is generated",
            "The goal of the game is to guess this word by guessing letters it consists of",
            "On each turn you should guess one letter",
            "If the letter guessed correctly, it will be opened in the word and you will NOT lose an attempt",
            "If guessing letter is not in the word, you lose one attempt",
            "You have 7 attempts",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "Missed",
            "Word: ****",
            "Remaining attempts: 6",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q̶, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "Missed",
            "Word: ****",
            "Remaining attempts: 5",
            "",
            "Alphabet: [a, b, c, d, e̶, f, g, h, i, j, k, l, m, n, o, p, q̶, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "Missed",
            "Word: ****",
            "Remaining attempts: 4",
            "",
            "Alphabet: [a, b, c, d, e̶, f, g, h, i, j, k, l, m, n, o, p, q̶, r, s, t̶, u, v, w, x, y, z]",
            "Guess a letter:",
            "Missed",
            "Word: ****",
            "Remaining attempts: 3",
            "",
            "Alphabet: [a, b, c, d, e̶, f, g, h, i, j, k, l, m, n, o, p, q̶, r, s, t̶, u, v, w, x, y̶, z]",
            "Guess a letter:",
            "Missed",
            "Word: ****",
            "Remaining attempts: 2",
            "",
            "Alphabet: [a, b, c, d, e̶, f, g, h, i, j, k, l, m, n, o, p, q̶, r, s, t̶, u̶, v, w, x, y̶, z]",
            "Guess a letter:",
            "Missed",
            "Word: ****",
            "Remaining attempts: 1",
            "",
            "Alphabet: [a, b, c, d, e̶, f, g, h, i̶, j, k, l, m, n, o, p, q̶, r, s, t̶, u̶, v, w, x, y̶, z]",
            "Guess a letter:",
            "You lost",
            "Word: ****",
            "Remaining attempts: 0",
            "",
        });
    }

    @Test
    @DisplayName("Wrong input")
    void testWrongInput(){
        input.offer("fa");
        input.offer("");
        input.offer("5");
        input.offer("щ");
        input.offer("R");
        hangmanGame.run();
        assertThat(output.toArray()).isEqualTo(new String[]{
            "Hangman Game",
            "",
            "Rules:",
            "In the beginning of the game, a random word is generated",
            "The goal of the game is to guess this word by guessing letters it consists of",
            "On each turn you should guess one letter",
            "If the letter guessed correctly, it will be opened in the word and you will NOT lose an attempt",
            "If guessing letter is not in the word, you lose one attempt",
            "You have 7 attempts",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "You entered the wrong character(s)",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "You entered the wrong character(s)",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "You entered the wrong character(s)",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "You entered the wrong character(s)",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "You entered the wrong character(s)",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "You lost",
            "Word: ****",
            "Remaining attempts: 7",
            ""
        });
    }

    @Test
    @DisplayName("Already tried and already guessed")
    void testAlreadyWas(){
        input.offer("a");
        input.offer("w");
        input.offer("a");
        input.offer("w");
        input.offer("w");
        hangmanGame.run();
        assertThat(output.toArray()).isEqualTo(new String[]{
            "Hangman Game",
            "",
            "Rules:",
            "In the beginning of the game, a random word is generated",
            "The goal of the game is to guess this word by guessing letters it consists of",
            "On each turn you should guess one letter",
            "If the letter guessed correctly, it will be opened in the word and you will NOT lose an attempt",
            "If guessing letter is not in the word, you lose one attempt",
            "You have 7 attempts",
            "Word: ****",
            "Remaining attempts: 7",
            "",
            "Alphabet: [a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "Missed",
            "Word: ****",
            "Remaining attempts: 6",
            "",
            "Alphabet: [a̶, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z]",
            "Guess a letter:",
            "Hit",
            "Word: w***",
            "Remaining attempts: 6",
            "",
            "Alphabet: [a̶, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w̶, x, y, z]",
            "Guess a letter:",
            "You tried this letter already",
            "Word: w***",
            "Remaining attempts: 6",
            "",
            "Alphabet: [a̶, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w̶, x, y, z]",
            "Guess a letter:",
            "This letter is already guessed",
            "Word: w***",
            "Remaining attempts: 6",
            "",
            "Alphabet: [a̶, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w̶, x, y, z]",
            "Guess a letter:",
            "This letter is already guessed",
            "Word: w***",
            "Remaining attempts: 6",
            "",
            "Alphabet: [a̶, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w̶, x, y, z]",
            "Guess a letter:",
            "You lost",
            "Word: w***",
            "Remaining attempts: 6",
            "",
        });
    }
}
