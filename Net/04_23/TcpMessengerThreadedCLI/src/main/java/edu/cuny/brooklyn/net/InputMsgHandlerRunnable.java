package edu.cuny.brooklyn.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class InputMsgHandlerRunnable implements Runnable {

    private Socket socket;
    private String name;

    public InputMsgHandlerRunnable(String name, Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("The passed socket is null.");
        }
        this.name = name!=null?name:"";
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.format("%s: InputMessengerHandlerRunnable: starting ...%n",name);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String msg;
            while ((msg = reader.readLine()) != null) {
                System.out.format("%s: %s%n", name, msg);
                if (msg.equals("Bye bye.")) break;
            }
            System.out.format("%s: exiting...%n", name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
