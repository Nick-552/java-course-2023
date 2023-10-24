package edu.hw3.ex2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BracketsClusteringTest {

    private static Stream<Arguments> bracketGoodSource() {
        return Stream.of(
            Arguments.of("()()()", new String[] {"()", "()", "()"}),
            Arguments.of("((()))", new String[] {"((()))"}),
            Arguments.of("((()))(())()()(()())", new String[] {"((()))", "(())", "()", "()", "(()())"}),
            Arguments.of("((())())(()(()()))", new String[] {"((())())", "(()(()()))"}),
            Arguments.of("", new String[]{})
        );
    }

    private static Stream<Arguments> bracketBadSourceWrongSymbols() {
        return Stream.of(
            Arguments.of("(a)()()"),
            Arguments.of("[]((({})))(())()()(()())")
        );
    }

    private static Stream<Arguments> bracketBadSourceNotBalanced() {
        return Stream.of(
            Arguments.of("(()()"),
            Arguments.of(")("),
            Arguments.of("()("),
            Arguments.of("(()"),
            Arguments.of("())")
        );
    }

    @ParameterizedTest
    @MethodSource("bracketGoodSource")
    @DisplayName("Basic test")
    void clusterBrackets_shouldReturnClusters_whenValidInput(String expr, String[] expectedClusters) {
        assertThat(BracketsClustering.clusterBrackets(expr)).isEqualTo(expectedClusters);
    }
    @ParameterizedTest
    @MethodSource("bracketBadSourceNotBalanced")
    @DisplayName("Not balanced")
    void clusterBrackets_ThrowIllegalArgumentException_whenNotBalanced(String expr) {
        assertThatIllegalArgumentException().isThrownBy(() -> BracketsClustering.clusterBrackets(expr));
    }

    @ParameterizedTest
    @MethodSource("bracketBadSourceWrongSymbols")
    @DisplayName("Wrong symbols")
    void clusterBrackets_shouldThrowIllegalArgumentException_whenWrongSymbols(String expr) {
        assertThatIllegalArgumentException().isThrownBy(() -> BracketsClustering.clusterBrackets(expr));
    }

    @Test
    @DisplayName("null test")
    void clusterBrackets_shouldThrowIllegalArgumentException_whenNull() {
        assertThatIllegalArgumentException().isThrownBy(() -> BracketsClustering.clusterBrackets(null));
    }
}
