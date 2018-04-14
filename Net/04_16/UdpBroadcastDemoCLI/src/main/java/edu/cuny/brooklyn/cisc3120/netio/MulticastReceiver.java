package edu.cuny.brooklyn.cisc3120.netio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {
    private final static int MULTICAST_SERVER_PORT = 60202;
    private final static int BUFFER_SIZE = 1024;
    
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: MulticastClient group");
            return;
        }
        String multicast_group = args[0];
        
        try (MulticastSocket socket = new MulticastSocket(MULTICAST_SERVER_PORT)) {
            InetAddress group = InetAddress.getByName(multicast_group);
            socket.joinGroup(group);

            byte[] buf = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData());
            System.out.format("Group %s received: %s%n", multicast_group, received);
            socket.leaveGroup(group);
        }
    }
}
