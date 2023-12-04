package edu.hw8.ex1;

import edu.hw8.ex1.client.Client;
import edu.hw8.ex1.server.Server;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;

public class QuotesTest {

    @SneakyThrows
    @Test
    @DisplayName("Тестирование Server и Client")
    public void serverAndClient_shouldWorkCorrectly() {
        Server server = new Server();
        try (
            ExecutorService serverService = Executors.newSingleThreadExecutor();
            ExecutorService clients = Executors.newFixedThreadPool(3)
        ) {
            serverService.execute(server::start);
            var responses = new ConcurrentLinkedQueue<String>();
            Thread.sleep(1000);

            clients.execute(() -> {
                Client client = new Client("localhost", 8080);
                client.start();
                responses.add(client.requestQuote("личности"));
                client.close();
            });
            clients.execute(() -> {
                Client client = new Client("localhost", 8080);
                client.start();
                responses.add(client.requestQuote("sgsdfgdfs"));
                client.close();
            });
            clients.execute(() -> {
                Client client = new Client("localhost", 8080);
                client.start();
                responses.add(client.requestQuote("интеллект"));
                client.close();
            });
            clients.awaitTermination(1, TimeUnit.SECONDS);
            assertThat(responses).containsExactlyInAnyOrder(
                "Не переходи на личности там, где их нет",
                "No quote for this keyword",
                "Чем ниже интеллект, тем громче оскорбления"
            );
            server.shutdownNow();
        }
    }
}
