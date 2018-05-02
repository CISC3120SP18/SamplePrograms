package edu.cuny.brooklyn.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

public class MainViewController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);
	@FXML
	private TextField urlField;

	@FXML
	private Button goButton;

	@FXML
	private Label statusLabel;

	@FXML
	private ScrollPane htmlContentPane;

	@FXML
	private Label htmlContentLabel;

	@FXML
	private WebView webview;

	public void initialize() {
		urlField.setText("http://www.brooklyn.cuny.edu/web/home.php");
		goButton.setOnAction(e -> readUrl(urlField.getText()));
	}

	// The app may become non-reponsive for a while, how would you deal with it?
	private void readUrl(String webResource) {
		try {
			URL url = new URL(webResource);
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
				String line;
				StringBuilder sb = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					LOGGER.info("Line: " + line);
					sb.append(line).append("\n");
				}
				LOGGER.info("Finished reading the web resource.");
				String htmlContent = sb.toString();
				htmlContentLabel.setText(htmlContent);
				webview.getEngine().loadContent(htmlContent);
			}
		} catch (MalformedURLException e) {
			LOGGER.error(e.getMessage(), e);
			statusLabel.setText(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			statusLabel.setText(e.getMessage());
		}
	}

}
