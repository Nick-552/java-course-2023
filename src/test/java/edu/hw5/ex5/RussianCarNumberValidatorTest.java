package edu.hw5.ex5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RussianCarNumberValidatorTest {

    public static Stream<Arguments> russianCarNumberSource() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)

        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null test")
    void isValid_shouldThrowIllegalArgumentException_whenNullProvided(String russianCarNumber) {
        assertThatIllegalArgumentException().isThrownBy(() ->RussianCarNumberValidator.isValid(russianCarNumber));
    }

    @ParameterizedTest
    @MethodSource("russianCarNumberSource")
    @DisplayName("isValid test")
    void isValid_shouldReturnIfValidRussianCarNumber_whenNotNullProvided(String russianCarNumber, boolean expected) {
        assertThat(RussianCarNumberValidator.isValid(russianCarNumber)).isEqualTo(expected);
    }
}
