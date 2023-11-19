package edu.hw5.ex4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PasswordStrengthCheckerTest {

    public static Stream<Arguments> passwordSource() {
        return Stream.of(
            Arguments.of("~ ! @ # $ % ^ & * |", true),
            Arguments.of("", false),
            Arguments.of("fa kdaj2/A1532/SF", false),
            Arguments.of("12345678*", true)
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null test")
    void isStrong_shouldThrowIllegalArgumentException_whenNullProvided(String password) {
        assertThatIllegalArgumentException().isThrownBy(() -> PasswordStrengthChecker.isStrong(password));
    }

    @ParameterizedTest
    @MethodSource("passwordSource")
    @DisplayName("Passwords test")
    void isStrong_shouldReturnIfPasswordIsStrong_whenNotNull(String password, boolean isStrongExpected) {
        assertThat(PasswordStrengthChecker.isStrong(password)).isEqualTo(isStrongExpected);
    }

}
