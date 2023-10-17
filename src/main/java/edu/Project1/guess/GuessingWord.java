package edu.Project1.guess;

import java.util.ArrayList;
import java.util.Arrays;
import static edu.Project1.utils.Contains.contains;

public class GuessingWord {
    private static final char HIDDEN_LETTER = '*';
    private final String actualWord;
    private final char[] userWord;

    private final Character[] alphabet;

    private final ArrayList<Character> alreadyTried;

    public GuessingWord(String actualWord, Character[] alphabet) {
        this.actualWord = actualWord;
        this.userWord = new char[actualWord.length()];
        this.alphabet = alphabet;
        this.alreadyTried = new ArrayList<>(alphabet.length);
        Arrays.fill(userWord, HIDDEN_LETTER);

    }

    public char[] getUserWord() {
        return userWord;
    }

    public Character[] getAlphabet() {
        return alphabet;
    }

    public boolean alphabetContains(char c) {
        return contains(c, alphabet);
    }

    public boolean isAlreadyTried(char c) {
        return contains(c, alreadyTried.toArray());
    }

    public boolean isGuessed() {
        return !userWordContains(HIDDEN_LETTER);
    }

    public boolean actualWordContains(Character guessingChar) {
        Character[] charArray = new Character[actualWord.length()];
        for (int i = 0; i < actualWord.length(); i++) {
            charArray[i] = actualWord.charAt(i);
        }
        return contains(guessingChar, charArray);
    }

    public boolean userWordContains(Character guessingChar) {
        Character[] charArray = new Character[userWord.length];
        for (int i = 0; i < userWord.length; i++) {
            charArray[i] = userWord[i];
        }
        return contains(guessingChar, charArray);
    }

    public void open(Character guessingChar) {
        for (int i = 0; i < userWord.length; i++) {
            if (actualWord.charAt(i) == guessingChar) {
                userWord[i] = guessingChar;
            }
        }
    }

    public void tryChar(Character c) {
        alreadyTried.add(c);
    }
}
