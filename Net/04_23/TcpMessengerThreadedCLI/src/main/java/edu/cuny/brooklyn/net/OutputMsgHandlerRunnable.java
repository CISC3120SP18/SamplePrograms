package edu.cuny.brooklyn.net;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class OutputMsgHandlerRunnable implements Runnable {

    private Socket socket;
    private String name;

    public OutputMsgHandlerRunnable(String name, Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException("The passed socketis null.");
        }
        this.name = name!=null?name:"";
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.format("%s: OutputMessengerHandlerRunnable: starting ...%n",name);
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                Scanner scanner = new Scanner(System.in)) {
            String msg;
            while ((msg = scanner.nextLine()) != null) {
                writer.println(msg);
                writer.flush();
                if (msg.equals("Bye bye.")) break;
            }
            System.out.format("%s: exiting...%n", name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
