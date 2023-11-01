package edu.Project1.dictionary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static edu.Project1.utils.Contains.contains;

class DefaultDictionaryTest {

    @Test
    @DisplayName("Incorrect word length")
    void getRandomWord_shouldThrowRuntimeException_whenIncorrectWordLength() {
        Dictionary dictionary = new DefaultDictionary(new String[]{"", "s", "a"});
        assertThrows(RuntimeException.class, dictionary::getRandomWord);
    }

    @Test
    @DisplayName("Word doesn't fit alphabet")
    void getRandomWord_shouldReturnWordFromDefaultDictionary_whenWordDoesntFitAlphabetOfDictionary() {
        Dictionary dictionary = new DefaultDictionary(new String[]{"немного", "русских", "слов"});
        assertThat(contains(dictionary.getRandomWord(), DefaultDictionary.DEFAULT_WORDS)).isTrue();
    }

    @Test
    @DisplayName("Basic getRandomWord() test")
    void getRandomWord_shouldReturnWordFromWords_whenAll() {
        String[] words = new String[]{"sunglasses", "rainbow", "risk"};
        Dictionary dictionary = new DefaultDictionary(words);
        assertThat(contains(dictionary.getRandomWord(), words)).isTrue();
        assertThat(contains(new DefaultDictionary().getRandomWord(), DefaultDictionary.DEFAULT_WORDS)).isTrue();
    }
}
