package edu.hw3.ex4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DecimalToRomanConverterTest {

    @ParameterizedTest
    @CsvSource(
        value = {
            "2, II",
            "12, XII",
            "16, XVI",
            "14, XIV",
            "3999, MMMCMXCIX"
        }
    )
    @DisplayName("Basic test")
    void convert_shouldReturnCorrectRomanNumber_whenValidInput(int decimalNumber, String expectedRomanNumber) {
        assertThat(DecimalToRomanConverter.convert(decimalNumber)).isEqualTo(expectedRomanNumber);
    }

    @Test
    @DisplayName("Invalid argument")
    void convert_shouldThrowIllegalArgumentException_whenInvalidInput() {
        assertThatIllegalArgumentException().isThrownBy(() -> DecimalToRomanConverter.convert(-5));
    }
}
