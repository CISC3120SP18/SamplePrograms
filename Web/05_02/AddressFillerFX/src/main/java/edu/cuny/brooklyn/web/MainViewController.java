package edu.cuny.brooklyn.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainViewController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);

	@FXML
	private TextField addressField;

	@FXML
	private TextField cityField;

	@FXML
	private TextField stateField;

	@FXML
	private TextField zipCodeField;

	@FXML
	private TextField countryField;
	
    @FXML
    private Label statusLabel;
	
	private AddressService addressService;

	public void initialize() {
		addressService = new AddressService();
		
		zipCodeField.textProperty().addListener((observable, oldValue, newValue) -> processZipCodeField(newValue));
	}
	
	// what if we have networking problem, is this app still responsive? 
	private void processZipCodeField(String zipCode) {
		if (zipCode.length() < 5) {
			return;
		}
		
		try {
			Address address = addressService.getAddress(zipCode);
			LOGGER.info("Got address: " + address.toString());
			
			countryField.setText(address.getCountry());
			stateField.setText(address.getState());
			cityField.setText(address.getCity());
			LOGGER.info("Address set on the UI.");
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			statusLabel.setText("Address service may not be available. Enter all fields manually.");
		} catch (IllegalArgumentException e) {
			statusLabel.setText(e.getMessage());
		}
		
	}

}
