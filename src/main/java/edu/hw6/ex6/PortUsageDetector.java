package edu.hw6.ex6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PortUsageDetector {

    private static final int LAST_PORT = 49151;

    private static final String LINE_OF_TABLE = "%-10s%-7s%s\n";

    private static final Map<Integer, String> KNOWN_PORTS = Map.of(
        80, "HyperText Transfer Protocol",
        21, "File Transfer Protocol",
        25, "Simple Mail Transfer Protocol",
        22, "Secure Shell",
        443, "HyperText Transfer Protocol Secure",
        53, "Domain Name System"
    );

    public static String getOccupiedPorts() {
        StringBuilder stringBuilder = new StringBuilder(String.format(LINE_OF_TABLE, "Протокол", "Порт", "Сервис"));
        for (int portNumber = 0; portNumber <= LAST_PORT; portNumber++) {
            try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
                // Порт свободен
            } catch (IOException e) {
                stringBuilder.append(String.format(LINE_OF_TABLE, "TCP", portNumber, KNOWN_PORTS.get(portNumber)));
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(portNumber)) {
                // Порт свободен
            } catch (IOException e) {
                stringBuilder.append(String.format(LINE_OF_TABLE, "UDP", portNumber, KNOWN_PORTS.get(portNumber)));
            }
        }
        return stringBuilder.toString();
    }
}
