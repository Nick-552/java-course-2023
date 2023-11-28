package edu.project3.filter;

import edu.project3.model.NginxLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class LogsTimeFilterTest {

    public static Stream<Arguments> sourceForPassFiltration() {
        return Stream.of(
            Arguments.of(
                OffsetDateTime.of(2023, 11, 18, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2023, 11, 17, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2023, 11, 19, 0, 0, 0, 0, ZoneOffset.UTC),
                true
            ),
            Arguments.of(
                OffsetDateTime.of(2023, 11, 20, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2023, 11, 17, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2023, 11, 19, 0, 0, 0, 0, ZoneOffset.UTC),
                false
            ),
            Arguments.of(
                OffsetDateTime.of(2023, 11, 18, 0, 0, 0, 0, ZoneOffset.UTC),
                null,
                OffsetDateTime.of(2023, 11, 19, 0, 0, 0, 0, ZoneOffset.UTC),
                true
            ),
            Arguments.of(
                OffsetDateTime.of(2023, 11, 18, 0, 0, 0, 0, ZoneOffset.UTC),
                OffsetDateTime.of(2023, 11, 19, 0, 0, 0, 0, ZoneOffset.UTC),
                null,
                false
            ),
            Arguments.of(
                OffsetDateTime.of(2023, 11, 18, 0, 0, 0, 0, ZoneOffset.UTC),
                null,
                null,
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("sourceForPassFiltration")
    @DisplayName("basicTest")
    void passFiltration_shouldWorkCorrectly_whenVariousCases(
        OffsetDateTime timeStamp,
        OffsetDateTime from,
        OffsetDateTime to,
        boolean expected
    ) {
        var filter = new LogsTimeFilter(from, to);
        var nginxLog = NginxLog.builder().timeStamp(timeStamp).build();
        assertThat(filter.passFiltration(nginxLog)).isEqualTo(expected);
    }
}
