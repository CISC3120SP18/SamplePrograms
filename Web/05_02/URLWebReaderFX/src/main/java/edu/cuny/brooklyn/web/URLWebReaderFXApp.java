package edu.cuny.brooklyn.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class URLWebReaderFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(URLWebReaderFXApp.class);
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
		URL viewURL = getClass().getResource(MAIN_VIEW_FXML);
		if (viewURL == null) {
			throw new IllegalStateException("Cannot locate " + MAIN_VIEW_FXML);
		}
		logFxmlViewUrl(viewURL);
		
		Parent root = loader.load(viewURL.openStream());
		
		primaryStage.setTitle("URL Web Reader");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}

	private void logFxmlViewUrl(URL viewUrl) throws IOException {
		// where does the class loader loads the resources?
		// URL classURL = getClass().getResource(getClass().getSimpleName() + ".class");

		LOGGER.info("The view FXML file is at " + viewUrl.toString());
		LOGGER.info("The file name of the URL is " + viewUrl.getFile());
		URLConnection conn = viewUrl.openConnection();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				LOGGER.info("FXML View: " + line);
			}
		}
	}
}
