package edu.cuny.brooklyn.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddressAutoFillerFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(AddressAutoFillerFXApp.class);
	private final static String MAIN_VIEW_FXML = "fxml_mainview.fxml";
	
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
		Parent root = loader.load(getClass().getResourceAsStream(MAIN_VIEW_FXML));
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Address AutoFiller");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
}
