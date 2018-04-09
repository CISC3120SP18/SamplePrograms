package edu.cuny.brooklyn.io.controller;

import java.nio.file.Path;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class NotificationHelper {
	public static void showFileNotFound(Path path) {
		Alert alert = new Alert(AlertType.WARNING, "The application cannot find the file at " + path, ButtonType.OK);
		alert.showAndWait();
	}

	public static void showReadingError(Path path) {
		Alert alert = new Alert(AlertType.WARNING, "The application cannot read the file at " + path, ButtonType.OK);
		alert.showAndWait();
	}

	public static void showWritingError(Path path) {
		Alert alert = new Alert(AlertType.WARNING, "The application cannot write the file at " + path, ButtonType.OK);
		alert.showAndWait();
	}
}
