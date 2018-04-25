package edu.cuny.brooklyn.net.messenger.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.messenger.model.TcpMessenger;
import edu.cuny.brooklyn.messenger.model.TcpMessengerConfig;
import edu.cuny.brooklyn.messenger.model.UserProfile;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TcpMessengerController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TcpMessengerController.class);
    
    @FXML
    private TextArea messageHistoryTextArea;

    @FXML
    private TextField messageTextField;

    @FXML
    private Button messageButton;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Button connectionButton;

    private TcpMessenger messenger = new TcpMessenger();

    private Thread listenerThread = null;

    @FXML
    void initialize() {
        // TODO: app should exit when initialization fails
        // Run MessengerListener in the background
        listenerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LOGGER.debug("Starting startListener from FML initialize.");
                    messenger.startListener();
                    LOGGER.debug("Turned off startListener from FML initialize.");
                } catch (UnknownHostException e) {
                    LOGGER.warn("Unknown host: " + TcpMessengerConfig.getListenerBindAddress(), e);
                    Platform.runLater(() -> NotificationHelper.showUnknowHost());
                } catch (IOException e) {
                    LOGGER.error("I/O Exception " + TcpMessengerConfig.getListenerBindAddress(), e);
                    Platform.runLater(() -> NotificationHelper.showSocketIOException(e.getMessage()));
                } catch (InterruptedException e) {
                    LOGGER.info("The listener thread is interrupted.", e);
                    Platform.runLater(() -> NotificationHelper.showListenerInterrupted());
                }
            }});
        listenerThread.setDaemon(true);
        listenerThread.start();

        
        messenger.getMessageLine()
            .messageLineProperty()
            .addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> obs, String oldMsg, String newMsg) {
                LOGGER.debug(String.format("MessageLine's changeListener heard: (obs, old, new): (%s, %s, %s)", obs,
                        oldMsg, newMsg));
                messageHistoryTextArea.setText(messageHistoryTextArea.getText() 
                        + "\n" 
                        + "Remote" 
                        + ": " 
                        + newMsg);
            }});
        
        messenger.getConnectionStatus()
            .connectionStatusProperty()
            .addListener((observed, oldStatus, newStatus) -> {
                LOGGER.debug(String.format("ConnectionStatus's changeListener heard: (obs, old, new): (%b, %b, %b)"
                        , observed, oldStatus, newStatus));
                connectionButton.setText(newStatus?"Disonnect":"Connect");
            });
    }
    
    @FXML
    void processConnection(ActionEvent event) {
        if (messenger.isConnected()) {
            try {
                messenger.disconnectFromPeer();
            } catch (IOException e) {
                NotificationHelper.showCannotDisconnect();
            }
        } else {
            messenger.setEndPoint(addressTextField.getText(), Integer.parseInt(portTextField.getText()));
            try {
                messenger.connectToPeer();
                connectionButton.setText("Disconnect");
                LOGGER.info(String.format("%s has connected to peer.", UserProfile.getUserIdentity()));
            } catch (IOException e) {
                NotificationHelper.showCannotConnect();
            } catch (InterruptedException e) {
                LOGGER.info("messenger is interrupted.");
            }
        }
    }

    @FXML
    void sendMessage(ActionEvent event) {
        try {
            messenger.sendToPeer(messageTextField.getText());
            messageHistoryTextArea.setText(messageHistoryTextArea.getText() 
                    + "\n" 
                    + UserProfile.getUserIdentity() 
                    + ": " 
                    + messageTextField.getText());
            messageTextField.clear();
        } catch (IOException e) {
            NotificationHelper.showSendToPeerFailure();
        }
    }

    
    @FXML
    public void shutdown() {
        LOGGER.debug("shuting down ...");
        try {
            messenger.close();
            LOGGER.debug("Release resources used by messenger.");
        } catch (IOException e) {
            LOGGER.error("Cannot shutdown the messenger properly.", e);
        }
    }
}
