package edu.cuny.brooklyn.messenger.model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TcpMessenger {
    private final static Logger LOGGER = LoggerFactory.getLogger(TcpMessenger.class);
    private OutputStream outputStream;
    private Thread inputThread;
    private Socket socket;


    private MessengerMessegeReader messengerMessageReader; // thread
    private ConnectionStatus connected;
    
    private String peerAddress;
    private int listeningPort;
    private String userIdentity;
    
    private MessageLine messageLine; // property. use its change listener. safe
    
    private boolean listening;

    public TcpMessenger() {
        outputStream = null;
        connected = new ConnectionStatus();
        connected.setConnectionStatus(false);
        socket = null;
        messengerMessageReader = null;
        userIdentity = UserProfile.getUserIdentity();
        messageLine = new MessageLine();
        messageLine.setMessage(null);
        listening = true;
        inputThread = null;
    }
    
    public void startListener() throws UnknownHostException, IOException, InterruptedException {
        InetAddress serverBindAddress = InetAddress.getByName(TcpMessengerConfig.getListenerBindAddress());
        try (ServerSocket listeningSocket = new ServerSocket(
                TcpMessengerConfig.getListenerBindPort(), 
                TcpMessengerConfig.getListenerBackLog(),
                serverBindAddress)) {
            listeningSocket.setSoTimeout(TcpMessengerConfig.getSocketAcceptTimeout());
            while (listening) {
                try {
                    Socket socketCommWithClient = listeningSocket.accept();
                    if (!connected.isConnected() && socketCommWithClient != null) {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (socket != null && !socket.isClosed()) {
                            socket.close();
                        }
                        LOGGER.debug("connected status: " + connected.isConnected());
                        Platform.runLater(() -> connected.setConnectionStatus(true));
                        socket = socketCommWithClient;
                        outputStream = socket.getOutputStream();
                        messengerMessageReader = new MessengerMessegeReader(userIdentity, socket, messageLine);
                        inputThread = new Thread(messengerMessageReader);
                        inputThread.start();
                        LOGGER.info("startListener thread is being blocked.");
                        // block the try-with-resources block before threads exit.
                        // inputThread.join();
                        messengerMessageReader = null;
                        LOGGER.info("Exiting from the startListener.");
                    } else {
                        if (socketCommWithClient != null && !socketCommWithClient.isClosed()) {
                            socketCommWithClient.close();
                        }
                    }
                } catch (SocketTimeoutException e) {
                    LOGGER.debug("ServerSocket timed out, respawn.");
                }
            }
        }
    }

    public void connectToPeer() throws IOException, InterruptedException {
        socket = new Socket();
        SocketAddress address = new InetSocketAddress(peerAddress, listeningPort);
        socket.connect(address);
        connected.setConnectionStatus(true);
        outputStream = socket.getOutputStream();
        messengerMessageReader = new MessengerMessegeReader(userIdentity, socket, messageLine);
        inputThread = new Thread(messengerMessageReader);
        inputThread.start();
    }
    

    public synchronized void disconnectFromPeer() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        connected.setConnectionStatus(false);
    }
    
    public void setEndPoint(String address, int port) {
        peerAddress = address;
        listeningPort = port;
    }

    public boolean isConnected() {
        return connected.isConnected();
    }
    
    public synchronized void close() throws IOException {
        connected.setConnectionStatus(false);
        
        listening = false;
        
        if (socket != null) {
            socket.close();
        }
        
        if (outputStream != null) {
            outputStream.close();
        }
        
        // LOGGER.debug("interrupting input thread using thread " + inputThread);
        // inputThread.interrupt();
    }

    public void sendToPeer(String msg) throws IOException {
        msg = msg + "\n";
        outputStream.write(msg.getBytes());
        LOGGER.debug(String.format("%s sends to peer: %s.", UserProfile.getUserIdentity(), msg));
    }
    
    public MessengerMessegeReader getMessegeReader() {
        return messengerMessageReader;
    }
    

    
    public MessageLine getMessageLine() {
        /*
        if (messageLine == null) {
            LOGGER.error("messageLine is null");
        } else {
            LOGGER.info("messageLine is OK");
        }
        */
        return messageLine;
    }    
    
    public ConnectionStatus getConnectionStatus() {
        /*
        if (connected == null) {
            LOGGER.error("connectionStatus is null");
        } else {
            LOGGER.error("connectionStatus is OK");
        }
        */
        return connected;
    }
    
    
    public class MessageLine {
        // Define a variable to store the property: a line of message read from the
        // socket
        private StringProperty msg = new SimpleStringProperty();

        // Define a getter for the property's value
        public final String getMessage() {
            return msg.get();
        }

        // Define a setter for the property's value
        public final void setMessage(String msg) {
            this.msg.set(msg);
        }

        // Define a getter for the property itself
        public StringProperty messageLineProperty() {
            return msg;
        }
    }    
    
    public class ConnectionStatus {
        private BooleanProperty connected = new SimpleBooleanProperty();
        
        // Define a getter for the property's value
        public final boolean isConnected() {
            return connected.get();
        }

        // Define a setter for the property's value
        public final void setConnectionStatus(boolean connected) {
            this.connected.set(connected);
        }

        // Define a getter for the property itself
        public BooleanProperty connectionStatusProperty() {
            return connected;
        }
    }
}
