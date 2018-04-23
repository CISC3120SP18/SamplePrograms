package edu.cuny.brooklyn.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpMessengerThreadedFullDuplexServer {
    private final static int SERVER_LISTENING_PORT = 62110;
    private final static int SERVER_BACKLOG = 5;
    private final static String SERVER_BIND_ADDRESS = "0.0.0.0";

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Server stats.");
        InetAddress serverBindAddress = InetAddress.getByName(SERVER_BIND_ADDRESS);
        try (ServerSocket listeningSocket = new ServerSocket(SERVER_LISTENING_PORT, SERVER_BACKLOG,
                serverBindAddress)) {
            try (Socket socketCommWithClient = listeningSocket.accept()) {
                Thread inputThread = new Thread(new InputMsgHandlerRunnable("Server", socketCommWithClient));
                inputThread.start();
                Thread outputThread = new Thread(new OutputMsgHandlerRunnable("Server", socketCommWithClient));
                outputThread.start();
                // block the try-with-resources block before threads exit.
                inputThread.join();
                outputThread.join();
            }
        }
    }
}