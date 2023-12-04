package edu.hw8.ex1.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class Client {

    private static final int BUFFER_SIZE = 1024;

    private final String host;

    private final int port;

    private SocketChannel clientChannel;

    @SneakyThrows
    public void start() {
        clientChannel = SocketChannel.open(new InetSocketAddress(host, port));
    }

    @SneakyThrows
    public String requestQuote(String keyword) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        buffer.put(keyword.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        while (buffer.hasRemaining()) {
            clientChannel.write(buffer);
        }
        log.info("sent message: " + keyword);
        buffer.clear();
        StringBuilder stringBuilder = new StringBuilder();
        while (clientChannel.read(buffer) > 0) {
            stringBuilder.append(new String(buffer.array(), StandardCharsets.UTF_8));
        }
        String response = stringBuilder.toString().trim();
        log.info("get response: " + response);
        return response;
    }

    @SneakyThrows
    public void close() {
        clientChannel.close();
    }
}
