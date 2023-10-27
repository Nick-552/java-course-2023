package edu.Project1.dictionary;

import edu.Project1.utils.Contains;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class DefaultDictionary implements Dictionary {
    private static final int MIN_WORD_LENGTH = 4;
    private static final int MAX_WORD_LENGTH = 15;
    static final String[] DEFAULT_WORDS = {
        "bank", "java", "programming",
        "checkstyle", "word", "weather",
        "hangman", "project", "denissin"
    };
    static final String[] ALPHABET = IntStream.rangeClosed('a', 'z')
        .mapToObj(value -> String.valueOf((char) value)).toArray(String[]::new);
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
        for (char c : randomWord.toCharArray()) {
            if (!Contains.contains(String.valueOf(c), ALPHABET)) {
                LogManager.getLogger().warn("Word doesnt match alphabet, word was selected from default set of words");
                randomWord = DEFAULT_WORDS[new Random().nextInt(DEFAULT_WORDS.length)];
                break;
            }
        }
        return randomWord;
    }

    @Override
    public String[] getAlphabet() {
        return Arrays.copyOf(ALPHABET, ALPHABET.length);
    }
}
