package edu.project3.receiver;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class UrlLogReceiverTest {

    private static final String URL =
        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

    @Test
    void receiveLogs() {
        assertThat(new UrlLogReceiver(URL).receiveLogs()).isNotNull().isNotEqualTo(List.of());
    }
}
