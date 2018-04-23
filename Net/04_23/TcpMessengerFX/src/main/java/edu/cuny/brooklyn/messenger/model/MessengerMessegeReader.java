package edu.cuny.brooklyn.messenger.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.messenger.model.TcpMessenger.MessageLine;


public class MessengerMessegeReader implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessengerMessegeReader.class);

    private String name;
    
    /////////////////////////////////
    /* accessed by multiple thread */
    private Socket socket;
    private boolean requestToCloseListener;
    MessageLine messageLine;
    /////////////////////////////////

    public MessengerMessegeReader(String name, Socket socket, MessageLine messageLine) {
        if (socket == null) {
            throw new IllegalArgumentException("The passed socket is null.");
        }
        this.name = name!=null?name:"";
        this.socket = socket;
        
        requestToCloseListener = false;
        this.messageLine = messageLine;
    }

    @Override
    public void run() {
        LOGGER.info(String.format("%s: MessengerMessegeReader: starting ...%n",name));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String msg;
            while (!requestToCloseListener && (msg = reader.readLine()) != null) {
                LOGGER.debug(String.format("%s received: %s%n", name, msg));
                messageLine.setMessage(msg);
                /* we exit this loop by closing the socket readLine relies on when the code
                   has already entered readLine to perform blocked read */
            }
            LOGGER.debug(String.format("%s: exiting...%n", name));
        } catch (SocketException e) {
            if (requestToCloseListener) {
                LOGGER.debug("closing the listener thread by closing the socket.");
            } else {
                // LOGGER.debug("unintended socket error.", e);
                LOGGER.debug("socket closed.");
            }
        } catch (IOException e) {
            LOGGER.error("attempt to read from socket.", e);
        }
        LOGGER.info(String.format("%s: MessengerMessegeReader: exiting ...%n",name));
    }


    public void close() throws IOException {
        requestToCloseListener = true;
    }

}
