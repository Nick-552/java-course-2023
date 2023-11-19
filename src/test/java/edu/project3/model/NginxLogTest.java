package edu.project3.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class NginxLogTest {

    public static Stream<Arguments> logSource() {
        return Stream.of(
            Arguments.of(
                "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\" \"logs\"",
                new NginxLog(
                    "93.180.71.3", "-",
                    OffsetDateTime.of(2015, 5, 17, 8, 5, 32, 0, ZoneOffset.UTC),
                    new Request("GET", "/downloads/product_1", "HTTP/1.1"),
                    304,
                    0,
                    "-",
                    "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)",
                    "logs"
                    )
            ),
            Arguments.of("wrong log", null)
        );
    }

    @ParameterizedTest
    @MethodSource("logSource")
    @DisplayName("parseStringToLog")
    void parseStringToLog_shouldReturnLogIfLogStringIsCorrectElseNull(String logString, NginxLog expected) {
        assertThat(NginxLog.parseStringToLog(logString)).isEqualTo(expected);
    }
}
