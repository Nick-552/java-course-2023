package edu.project3.receiver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class ReceiversTest {

    public static Stream<Arguments> pathSource() {
        return Stream.of(
            Arguments.of("http", UrlLogReceiver.class),
            Arguments.of("https://asegasdgafdgaf", UrlLogReceiver.class),
            Arguments.of("logs/**/*.log", LocalLogsReceiver.class),
            Arguments.of("sfdsfasdfasdfa", LocalLogsReceiver.class)
        );
    }

    @ParameterizedTest
    @MethodSource("pathSource")
    @DisplayName("getLogReceiver")
    void getLogReceiver_shouldReturnUrlReceiverIfStartsWithHttpElseLocalReceiver(
        String path, Class<LogsReceiver> expected) {
        assertThat(Receivers.getLogReceiver(path)).isInstanceOf(expected);
    }
}
