package edu.Project1.guess;

import edu.Project1.dictionary.Dictionary;
import java.util.Arrays;
import static edu.Project1.utils.Contains.contains;

public class GuessingWord {
    private static final char HIDDEN_LETTER = '*';
    private static final String STRIKED = "\u0336";
    private final String actualWord;
    private final Character[] userWord;

    private final String[] alphabet;

    public GuessingWord(Dictionary dictionary) {
        this.actualWord = dictionary.getRandomWord();
        this.userWord = new Character[actualWord.length()];
        this.alphabet = dictionary.getAlphabet();
        Arrays.fill(userWord, HIDDEN_LETTER);
    }

    public String getUserWord() {
        return String.valueOf(
            Arrays.stream(userWord)
            .collect(
                StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append
            )
        );
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public boolean alphabetContains(String s) {
        return contains(s, alphabet) || contains(s.concat(STRIKED), alphabet);
    }

    public boolean isAlreadyTried(char c) {
        return contains(String.valueOf(c).concat(STRIKED), alphabet);
    }

    public boolean isGuessed() {
        return !userWordContains(HIDDEN_LETTER);
    }

    public boolean actualWordContains(Character guessingChar) {
        Character[] charArray = actualWord
            .chars()
            .mapToObj(c -> (Character) (char) c)
            .toArray(Character[]::new);
        return contains(guessingChar, charArray);
    }

    public boolean userWordContains(Character guessingChar) {
        return contains(guessingChar, userWord);
    }

    public void open(Character guessingChar) {
        for (int i = 0; i < userWord.length; i++) {
            if (actualWord.charAt(i) == guessingChar) {
                userWord[i] = guessingChar;
            }
        }
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i].equals(String.valueOf(guessingChar))) {
                alphabet[i] = alphabet[i].concat(STRIKED);
            }
        }
    }

    public void tryChar(Character c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i].equals(String.valueOf(c))) {
                alphabet[i] = alphabet[i].concat(STRIKED);
            }
        }
    }
}
