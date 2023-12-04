package edu.hw8.ex1.server;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class RequestHandler implements Runnable {

    private static final int BUFFER_SIZE = 1024;

    private static final QuotesStorage QUOTES_STORAGE = QuotesStorage.getConcurrent().filledWithDefaultValues();

    private final SocketChannel socketChannel;

    @SneakyThrows
    @Override
    public void run() {
        log.info("started");
        String request = readFromClient();
        String response = processRequest(request);
        log.info("processed request. response: %s".formatted(response));
        sendResponseToClient(response);
        socketChannel.close();
        log.info("socketChannel closed");
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
