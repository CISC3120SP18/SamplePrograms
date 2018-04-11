package edu.cuny.brooklyn.net;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class NetIfExplorerCliApp 
{
    public static void main( String[] args ) throws SocketException
    {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while(interfaces.hasMoreElements()) {
            NetworkInterface netIf = interfaces.nextElement();
            System.out.println("-------------------------------------------------------------");
            System.out.println(getDisplayNetworkInterface(netIf));
            System.out.println(getDisplaySubInterfaces(netIf.getSubInterfaces()));
        }
    }
    
    private static String getDisplayNetworkInterface(NetworkInterface netIf) throws SocketException {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%20s: %s%n",  "Display Name", netIf.getDisplayName()))
          .append(String.format("%20s: %s%n",  "Name", netIf.getName()))
          .append(String.format("%20s: %d%n",  "index", netIf.getIndex()))
          .append(String.format("%20s: %s%n",  "MTU", netIf.getMTU()!=-1?Integer.toString(netIf.getMTU()):"N/A"))
          .append(String.format("%20s: %s%n",  "Is loopback", netIf.isLoopback()?"Yes":"No"))
          .append(String.format("%20s: %s%n",  "Is virtual", netIf.isVirtual()?"Yes":"No"))
          .append(String.format("%20s: %s%n",  "Is PointToPoint", netIf.isPointToPoint()?"Yes":"No"))
          .append(String.format("%20s: %s%n",  "Is up", netIf.isUp()?"Yes":"No"))
          .append(String.format("%20s: %s%n",  "Support Multicast:", netIf.supportsMulticast()?"Yes":"No"))
          .append(String.format("%20s: %-90s%n"
                , "Hardware Address"
                , netIf.getHardwareAddress()!=null?getDisplayHardwareAddress(netIf.getHardwareAddress()):"N/A"))
          .append(getDisplayInetAddresses(netIf.getInetAddresses()))
          .append(getDisplayInterfaceAddresses(netIf.getInterfaceAddresses()));
        return sb.toString();
    }
    
    private static String getDisplayInterfaceAddresses(List<InterfaceAddress> addressList) {
        StringBuilder sb = new StringBuilder();
        for (InterfaceAddress address: addressList) {
            sb.append(String.format("%20s: %s%n", "Interface Address", address.toString()));
        }
        return sb.toString();
    }
    
    
    private static String getDisplayHardwareAddress(byte[] address) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<address.length; i++) {
            sb.append(String.format("%02X%s", address[i], (i<address.length - 1)?"-":""));
        }
        return sb.toString();
    }
    
    private static String getDisplayInetAddresses(Enumeration<InetAddress> addresses) {
        StringBuilder sb = new StringBuilder();
        // the 2nd method of go through an Enumeration. (where is the 1st method in this program?)
        List<InetAddress> addressList = Collections.list(addresses);
        for (InetAddress address: addressList) {
            sb.append(String.format("%20s: %s%n",  "InetAddress", address.toString()));
        }
        return sb.toString();
    }
    
    private static String getDisplaySubInterfaces(Enumeration<NetworkInterface> netInterfaces) throws SocketException {
        StringBuilder sb = new StringBuilder();
        while(netInterfaces.hasMoreElements()) {
            NetworkInterface netIf = netInterfaces.nextElement();
            sb.append(getDisplayNetworkInterface(netIf) + "\n");
        }
        return sb.toString();
    }
}
