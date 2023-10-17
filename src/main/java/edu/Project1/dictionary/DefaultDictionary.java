package edu.Project1.dictionary;

import edu.Project1.utils.Contains;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class DefaultDictionary implements Dictionary {
    private static final int MIN_WORD_LENGTH = 4;
    private static final int MAX_WORD_LENGTH = 15;
    public static final String[] DEFAULT_WORDS = {
        "bank", "java", "programming",
        "checkstyle", "word", "weather",
        "hangman", "project", "denissin"
    };
    public static final Character[] ALPHABET = {
        'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
        'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
        'z', 'x', 'c', 'v', 'b', 'n', 'm',
    };
    private final String[] words;

    public DefaultDictionary(String[] words) {
        this.words = words;
    }

    public DefaultDictionary() {
        this.words = DEFAULT_WORDS;
    }

    @Override
    public @NotNull String getRandomWord() {
        String randomWord = words[new Random().nextInt(words.length)];
        if (randomWord.length() < MIN_WORD_LENGTH || randomWord.length() > MAX_WORD_LENGTH) {
            throw new RuntimeException("Incorrect word length");
        }
        for (char c: randomWord.toCharArray()) {
            if (!Contains.contains(c, ALPHABET)) {
                LogManager.getLogger().warn("Word doesnt match alphabet, word was selected from default set of words");
                randomWord = DEFAULT_WORDS[new Random().nextInt(DEFAULT_WORDS.length)];
                break;
            }
        }
        return randomWord;
    }

    @Override
    public Character[] getAlphabet() {
        return ALPHABET;
    }
}
