package edu.hw3.ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AtbashEncoderTest {

    @ParameterizedTest
    @DisplayName("Basic test")
    @CsvSource(value = {
        "Hello world!, Svool dliow!",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    })
    void encode_shouldReturnStringWithLatinLetters_whenNotNull(String message, String expected) {
        assertThat(AtbashEncoder.encode(message)).isEqualTo(expected);
    }

    @Test
    @DisplayName("null test")
    void encode_shouldThrowNullPointerException_whenNull() {
        assertThrows(IllegalArgumentException.class, () -> AtbashEncoder.encode(null));
    }
}
