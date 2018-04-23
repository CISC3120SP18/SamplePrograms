package edu.cuny.brooklyn.messenger.model;

public class TcpMessengerConfig {
    // TODO: can make the setting's configurable. This class is the beginning
    private final static String LISTENER_BIND_ADDRESS = "0.0.0.0";
    private final static int LISTENER_BIND_PORT = 63111;
    private final static int LISTENER_BACK_LOG = 5;
    private final static int SOCKET_ACCEPT_TIMEOUT = 15000;
    
    public final static String PORT_KEY = "port";
    
    private static int listeningPort = -1;
    
    
    
    public static String getListenerBindAddress() {
        return LISTENER_BIND_ADDRESS;
    }
    
    public static int getListenerBindPort() {
        if (listeningPort < 0)
            return LISTENER_BIND_PORT;
        else
            return listeningPort;
    }
    
    public static int getListenerBackLog() {
        return LISTENER_BACK_LOG;
    }
    
    public static void setLitensingPort(int port) {
        listeningPort = port;
    }
    
    public static int getSocketAcceptTimeout() {
        return SOCKET_ACCEPT_TIMEOUT;
    }
}
