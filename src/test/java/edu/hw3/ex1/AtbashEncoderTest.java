package edu.hw3.ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AtbashEncoderTest {

    @ParameterizedTest
    @DisplayName("Basic test")
    @CsvSource(value = {
        "Hello world!, Svool dliow!",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
        "Русские слова, Русские слова",
    })
    void encode_shouldReturnEncodedString_whenNotNull(String message, String expected) {
        assertThat(AtbashEncoder.encode(message)).isEqualTo(expected);
    }

    @Test
    @DisplayName("null and empty test")
    void encode_shouldEmptyString_whenNullOrEmptyMessage() {
        assertThat(AtbashEncoder.encode("")).isEqualTo("");
        assertThat(AtbashEncoder.encode(null)).isEqualTo("");
    }
}
