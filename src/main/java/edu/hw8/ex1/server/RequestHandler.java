package edu.hw8.ex1.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw8.ex1.server.QuotesStorage.DEFAULT_MAP;

@RequiredArgsConstructor
public class RequestHandler implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger();

    private static final int BUFFER_SIZE = 1024;

    private static final QuotesStorage QUOTES_STORAGE = QuotesStorage.getConcurrent().filledWithEntriesOf(DEFAULT_MAP);

    private final SocketChannel socketChannel;

    @Override
    public void run() {
        LOGGER.info("started");
        String request = readFromClient();
        String response = processRequest(request);
        LOGGER.info("processed request. response: %s".formatted(response));
        sendResponseToClient(response);
        try {
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("socketChannel closed");
    }

    @SneakyThrows
    public String readFromClient() {
        StringBuilder requestString = new StringBuilder();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        if (socketChannel.read(byteBuffer) > 0) {
            requestString.append(new String(byteBuffer.array(), StandardCharsets.UTF_8));
        }
        return requestString.toString().trim();
    }

    public String processRequest(String request) {
        return QUOTES_STORAGE.getQuote(request);
    }

    @SneakyThrows
    public void sendResponseToClient(String responseMessage) {
        ByteBuffer responseBuffer = ByteBuffer.wrap((responseMessage).getBytes(StandardCharsets.UTF_8));
        while (responseBuffer.hasRemaining()) {
            socketChannel.write(responseBuffer);
        }
    }
}
