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

public class IntraDayPriceFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(IntraDayPriceFXApp.class);
	private final static String MAIN_VIEW_FXML = "fxml_mainview.fxml";
	private final static String API_KEY = "ALPHAVANTAGE_API_KEY";
	private final static String API_KEY_PLACE_HOLDER = "YOUR_ALPHAVANTAGE_API_KEY";

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

		primaryStage.setTitle("Intra-Day Equity Share Price");
		primaryStage.setScene(scene);
		primaryStage.show();
		LOGGER.info("loaded the UI");
		
		String apiKey = null;
		try {
			apiKey = loadApiKey();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR
					, "The application failed to load the required API key for Alpha Vantage, and must exit."
					+ "The error message is: " + e.getMessage());
			alert.showAndWait();
			Platform.exit();
		}
		LOGGER.info("loaded the API key.");

		IntradaySharePriceService service = new IntradaySharePriceService(apiKey); 
		MainViewController controller = loader.getController();
		controller.setSecuritySharePriceShare(service);
		LOGGER.info("Initialized the share price service.");
	}

	@Override
	public void stop() {
		LOGGER.info("Exiting the app...");
	}

	private String loadApiKey() throws IOException {
		Properties properties = new Properties();
		InputStream in = getClass().getClassLoader().getResourceAsStream(getClass().getSimpleName() + ".properties");
		if (in == null) {
			throw new IOException("Cannot locate the application configuration file.");
		}
		properties.load(in);
		String apiKey = properties.getProperty(API_KEY);
		if (apiKey != null && apiKey.equals(API_KEY_PLACE_HOLDER)) {
			throw new IOException("You must replace in the configuration file YOUR_ALPHAVANTAGE_API_KEY by your actual API key obtained from Alpha Vantage.");
		}
		return apiKey;
	}
}
