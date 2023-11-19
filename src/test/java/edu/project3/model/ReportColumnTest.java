package edu.project3.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class ReportColumnTest {

    public static Stream<Arguments> reportColumnSource() {
        return Stream.of(
            Arguments.of(
                new ReportColumn("head", List.of("value", "longvalue", "val")),
                new ReportColumn("head", List.of("value", "longvalue", "val"), 9)
            ),
            Arguments.of(
                new ReportColumn("long head of column", List.of("value", "longval", "val")),
                new ReportColumn("long head of column", List.of("value", "longval", "val"), 19)
            )

        );
    }

    @ParameterizedTest
    @MethodSource("reportColumnSource")
    @DisplayName("initWithoutMaxLength")
    void initWithoutMaxLength(ReportColumn reportColumn1, ReportColumn reportColumn2) {
        assertThat(reportColumn1).isEqualTo(reportColumn2);
    }

}
