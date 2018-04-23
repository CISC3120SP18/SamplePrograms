package edu.cuny.brooklyn.net.messenger.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class NotificationHelper {
    public static void showCannotConnect() {
        Alert alert = new Alert(AlertType.ERROR, "Cannot connect to peer.", ButtonType.OK); 
        alert.showAndWait();
    }

    public static void showCannotDisconnect() {
        Alert alert = new Alert(AlertType.ERROR, "Cannot disconnect from peer.", ButtonType.OK); 
        alert.showAndWait();
    }

    public static void showUnknowHost() {
        Alert alert = new Alert(AlertType.WARNING, "Unknown host.", ButtonType.OK); 
        alert.showAndWait();
    }

    public static void showSocketIOException(String msg) {
        Alert alert = new Alert(AlertType.ERROR, "Network error encounted: " + msg, ButtonType.OK); 
        alert.showAndWait();
    }

    public static void showListenerInterrupted() {
        Alert alert = new Alert(AlertType.ERROR, "Failed to initiliaze TCP messenger", ButtonType.OK); 
        alert.showAndWait();
    }

    public static void showSendToPeerFailure() {
        Alert alert = new Alert(AlertType.ERROR, "Failed to transmit the message to peer", ButtonType.OK); 
        alert.showAndWait();
    }

}
