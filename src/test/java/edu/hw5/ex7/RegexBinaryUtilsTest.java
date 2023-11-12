package edu.hw5.ex7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RegexBinaryUtilsTest {

    public static Stream<Arguments> notBinaryStringSource() {
        return Stream.of(
            Arguments.of("1sfa1"),
            Arguments.of("110sd10"),
            Arguments.of("01a")
        );
    }

    public static Stream<Arguments> hasAtLeastThreeCharsAnd3rdIs0Source() {
        return Stream.of(
            Arguments.of("110", true),
            Arguments.of("00", false),
            Arguments.of("1110", false),
            Arguments.of("1101", true),
            Arguments.of("", false)
        );
    }

    public static Stream<Arguments> hasSameStartAndEndCharsSource() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("011010010", true),
            Arguments.of("1101010101101", true),
            Arguments.of("01010101011", false),
            Arguments.of("11010101010", false),
            Arguments.of("1", true)
        );
    }

    public static Stream<Arguments> hasLengthInRangeOneToThreeSource() {
        return Stream.of(
            Arguments.of("0", true),
            Arguments.of("", false),
            Arguments.of("10", true),
            Arguments.of("101", true),
            Arguments.of("1010101", false)
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("All method null test")
    void allMethods_shouldThrowIllegalArgumentExceptionWhenNullProvided(String s) {
        assertThatIllegalArgumentException().isThrownBy(() -> RegexBinaryUtils.hasAtLeastThreeCharsAnd3rdIs0(s));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexBinaryUtils.hasSameStartAndEndChars(s));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexBinaryUtils.hasLengthInRangeOneToThree(s));
    }

    @ParameterizedTest
    @MethodSource("notBinaryStringSource")
    @DisplayName("Not binary")
    void allMethods_shouldThrowIllegalArgumentExceptionWhenNotBinaryProvided(String s) {
        assertThatIllegalArgumentException().isThrownBy(() -> RegexBinaryUtils.hasAtLeastThreeCharsAnd3rdIs0(s));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexBinaryUtils.hasSameStartAndEndChars(s));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexBinaryUtils.hasLengthInRangeOneToThree(s));
    }


    @ParameterizedTest
    @MethodSource("hasAtLeastThreeCharsAnd3rdIs0Source")
    @DisplayName("hasAtLeastThreeCharsAnd3rdIs0")
    void hasAtLeastThreeCharsAnd3rdIs0(String binaryString, boolean expected) {
        assertThat(RegexBinaryUtils.hasAtLeastThreeCharsAnd3rdIs0(binaryString)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("hasSameStartAndEndCharsSource")
    @DisplayName("hasSameStartAndEndChars")
    void hasSameStartAndEndChars(String binaryString, boolean expected) {
        assertThat(RegexBinaryUtils.hasSameStartAndEndChars(binaryString)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("hasLengthInRangeOneToThreeSource")
    @DisplayName("hasLengthInRangeOneToThree")
    void hasLengthInRangeOneToThree(String binaryString, boolean expected) {
        assertThat(RegexBinaryUtils.hasLengthInRangeOneToThree(binaryString)).isEqualTo(expected);
    }
}
