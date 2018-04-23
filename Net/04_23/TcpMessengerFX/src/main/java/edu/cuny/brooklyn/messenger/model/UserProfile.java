package edu.cuny.brooklyn.messenger.model;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserProfile {
    // TODO: this is the base for deal with multiple users
    private final static Logger LOGGER = LoggerFactory.getLogger(UserProfile.class);
    
    public static String getUserIdentity() {
        String username = System.getProperty("user.name");
        String hostname = "unknown";
        String port = "unknown";
        
        InetAddress localMachine;
        try {
            localMachine = InetAddress.getLocalHost();
            hostname = localMachine.getHostName();
            port = Integer.toString(TcpMessengerConfig.getListenerBindPort());
        } catch (UnknownHostException e) {
            LOGGER.warn("Cannot obtain local host name, and use \"unknown\" as the hostname.", e);
        }
        
        return username + "@" + hostname + ":" + port;
    }
}
