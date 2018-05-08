package edu.cuny.brooklyn.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NasaApodFX extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(NasaApodFX.class);
	private final static String MAIN_VIEW_FXML = "fxml_mainview.fxml";
	private final static String API_KEY = "NASA_API_KEY";
	private final static String API_KEY_PLACE_HOLDER = "YOUR_NASA_API_KEY";
	
	private String apiKey = "DEMO_KEY";
	private String apiKeyFilename = getClass().getSimpleName() + ".properties";
	
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

		primaryStage.setTitle("NASA's a picture of a day from the lonely planet in the vast Universe ...");
		primaryStage.setScene(scene);
		primaryStage.show();
		LOGGER.info("loaded the UI");
		
		try {
			if (!loadApiKey()) {
				showApiKeyAlert();
			}
		} catch (IOException e) {
			showAlertAndExit(e.getMessage());
		}
		LOGGER.info("loaded the API key as " + apiKey);

		NasaApodService service = new NasaApodService(apiKey); 
		MainViewController controller = loader.getController();
		controller.setNasaApodService(service);
		controller.showFirst();
		LOGGER.info("Initialized the NASA APOD service.");
	}
	
	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}
	

	private boolean loadApiKey() throws IOException {
		Properties properties = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream(getClass().getSimpleName() + ".properties");
		if (in == null) {
			throw new IOException("Cannot locate the application configuration file.");
		}
		properties.load(in);
		String apiKey = properties.getProperty(API_KEY);
		if (apiKey == null || (apiKey != null && apiKey.equals(API_KEY_PLACE_HOLDER))) {
			return false;
		} else {
			this.apiKey = apiKey;
			return true;
		}
	}
	
	private void showApiKeyAlert() {
		Alert alert = new Alert(AlertType.ERROR
				, "The application failed to load the NASA API key from " + apiKeyFilename + "."
				+ "The NASA DEMO API KEY will be used. "
				+ "However, the application may soon fail due to the rate limitation "
				+ "that NASA imposes on the DEMO KEY. "
				+ "You should obtain an API KEY from NASA "
				+ "at https://api.nasa.gov/index.html#apply-for-an-api-key "
				+ "relace YOUR_NASA_API_KEY by your actual API key in " + apiKeyFilename + ".");
		alert.showAndWait();
	}
	
	private void showAlertAndExit(String msg) {
		Alert alert = new Alert(AlertType.ERROR
				, "The application failed to load the required NASA API key, and must exit."
				+ "The error message is: " + msg);
		alert.showAndWait();
		Platform.exit();
	}
}
