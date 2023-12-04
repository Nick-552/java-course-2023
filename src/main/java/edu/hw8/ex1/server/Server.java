package edu.hw8.ex1.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Server {


    private static final int PORT = 8080;

    private static final String HOST = "localhost";

    private static final int MAX_CONNECTIONS = 2;

    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    private ServerSocketChannel channel;

    @SneakyThrows
    public void start() {
        channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        ServerSocket serverSocket = channel.socket();
        serverSocket.bind(new InetSocketAddress(HOST, PORT));
        log.info("Starting server on port " + PORT);
        while (channel.isOpen()) {
            SocketChannel client = channel.accept();
            if (client == null) {
                continue;
            }
            executorService.execute(new RequestHandler(client));
        }
        log.info("server shutdowned");
    }

    @SneakyThrows
    public void shutdownNow() {
        channel.close();
        executorService.shutdownNow();
        executorService.close();
    }
}
