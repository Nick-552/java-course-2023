package edu.hw5.ex8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class RegexExtraBinaryUtilsTest {

    public static Stream<Arguments> notBinarySource() {
        return Stream.of(
            Arguments.of("sdfgsf"),
            Arguments.of("110024"),
            Arguments.of("10 10"),
            Arguments.of(" 10101")
        );
    }

    private static final Map<String, Boolean> mapStringExpected = new HashMap<>();

    @BeforeEach
    void clear() {
        mapStringExpected.clear();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null test")
    void allMethod_shouldThrowIllegalArgumentException_whenNullProvided(String nullString) {
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.hasOddLength(nullString));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.startsWith0AndOddLengthOrStartsWith1AndEvenLength(nullString));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.hasNumberOf0sThatIsMultipleOfThree(nullString));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.isAnyStringExcept11And111(nullString));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.isEachOddChar1(nullString));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.containsAtLeastTwo0sAndNoMoreThanOne1(nullString));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.noConsecutive1s(nullString));
    }

    @ParameterizedTest
    @MethodSource("notBinarySource")
    @DisplayName("Not binary test")
    void allMethod_shouldThrowIllegalArgumentException_whenNotBinaryProvided(String notBinary) {
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.hasOddLength(notBinary));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.startsWith0AndOddLengthOrStartsWith1AndEvenLength(notBinary));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.hasNumberOf0sThatIsMultipleOfThree(notBinary));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.isAnyStringExcept11And111(notBinary));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.isEachOddChar1(notBinary));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.containsAtLeastTwo0sAndNoMoreThanOne1(notBinary));
        assertThatIllegalArgumentException().isThrownBy(() -> RegexExtraBinaryUtils.noConsecutive1s(notBinary));
    }

    @Test
    void hasOddLength() {
        mapStringExpected.putAll(Map.of(
            "011", true,
            "1", true,
            "10", false,
            "", false,
            "1001", false
        ));
        assertThat(
            mapStringExpected
                .keySet().stream()
                .collect(Collectors.toMap(el -> el, RegexExtraBinaryUtils::hasOddLength)))
            .containsExactlyInAnyOrderEntriesOf(mapStringExpected);
    }

    @Test
    void startsWith0AndOddLengthOrStartsWith1AndEvenLength() {
        mapStringExpected.putAll(Map.of(
            "011", true,
            "10", true,
            "110", false,
            "", false,
            "1001", true,
            "0110", false
        ));
        assertThat(
            mapStringExpected
                .keySet().stream()
                .collect(Collectors.toMap(el -> el, RegexExtraBinaryUtils::startsWith0AndOddLengthOrStartsWith1AndEvenLength)))
            .containsExactlyInAnyOrderEntriesOf(mapStringExpected);
    }

    @Test
    void hasNumberOf0sThatIsMultipleOfThree() {
        mapStringExpected.putAll(Map.of(
            "01100", true,
            "", true,
            "10", false,
            "0110", false,
            "101010101001", true
        ));
        assertThat(
            mapStringExpected
                .keySet().stream()
                .collect(Collectors.toMap(el -> el, RegexExtraBinaryUtils::hasNumberOf0sThatIsMultipleOfThree)))
            .containsExactlyInAnyOrderEntriesOf(mapStringExpected);
    }

    @Test
    void isAnyStringExcept11And111() {
        mapStringExpected.putAll(Map.of(
            "111", false,
            "1", true,
            "11", false,
            "", true,
            "1111", true,
            "11100",true,
            "011", true
        ));
        assertThat(
            mapStringExpected
                .keySet().stream()
                .collect(Collectors.toMap(el -> el, RegexExtraBinaryUtils::isAnyStringExcept11And111)))
            .containsExactlyInAnyOrderEntriesOf(mapStringExpected);
    }

    @Test
    void isEachOddChar1() {
        mapStringExpected.putAll(Map.of(
            "101", true,
            "1", true,
            "0", false,
            "01", false,
            "110111", false
        ));
        assertThat(
            mapStringExpected
                .keySet().stream()
                .collect(Collectors.toMap(el -> el, RegexExtraBinaryUtils::isEachOddChar1)))
            .containsExactlyInAnyOrderEntriesOf(mapStringExpected);
    }

    @Test
    void containsAtLeastTwo0sAndNoMoreThanOne1() {
        mapStringExpected.putAll(Map.of(
            "010", true,
            "1", false,
            "1001", false,
            "00", true,
            "", false
        ));
        assertThat(
            mapStringExpected
                .keySet().stream()
                .collect(Collectors.toMap(el -> el, RegexExtraBinaryUtils::containsAtLeastTwo0sAndNoMoreThanOne1)))
            .containsExactlyInAnyOrderEntriesOf(mapStringExpected);
    }

    @Test
    void noConsecutive1s() {
        mapStringExpected.putAll(Map.of(
            "011", false,
            "1", true,
            "1010101010101", true,
            "", true,
            "0000110000", false
        ));
        assertThat(
            mapStringExpected
                .keySet().stream()
                .collect(Collectors.toMap(el -> el, RegexExtraBinaryUtils::noConsecutive1s)))
            .containsExactlyInAnyOrderEntriesOf(mapStringExpected);
    }
}
