package edu.cuny.brooklyn.web;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MainViewController {

	@FXML
	private TextField addressField;

	@FXML
	private Button goButton;

	@FXML
	private WebView webview;
	
	private WebEngine webEngine; 

	public void initialize() {
		webEngine = webview.getEngine();
		goButton.setOnAction(e -> go());
		addressField.setOnAction(e -> go());
	}
	
	private void go() {
		webEngine.load(addressField.getText());
	}
}
