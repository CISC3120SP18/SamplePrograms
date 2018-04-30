package edu.cuny.brooklyn.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WebBrowserFX extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(WebBrowserFX.class);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init() {
		LOGGER.info("Launching the app ...");		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Parent root = loader.load(getClass().getResourceAsStream("fxml_mainview.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Simple Web Browser");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
}
