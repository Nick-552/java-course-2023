package edu.hw5.ex6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SubstringUtilsTest {

    public static Stream<Arguments> substringSource() {
        return Stream.of(
            Arguments.of("", "", true),
            Arguments.of("abc", "achfdbaabgabcaabg.", true),
            Arguments.of("abc", "ab5c", false),
            Arguments.of("abc", "abc", true),
            Arguments.of("fsdfgs", "gsdfgsdf", false),
            Arguments.of("abcd", "abc", false)
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null test")
    void isSubstringOf_shouldThrowIllegalArgumentException_whenNull(String nullString) {
        assertThatIllegalArgumentException().isThrownBy(() -> SubstringUtils.isSubstringOf(nullString, nullString));
    }

    @ParameterizedTest
    @MethodSource("substringSource")
    @DisplayName("Null test")
    void isSubstringOf_shouldReturnIfTargetIsSubstringOfSource_whenNotNull(
        String target, String source, boolean expected) {
        assertThat(SubstringUtils.isSubstringOf(target, source)).isEqualTo(expected);
    }
}
