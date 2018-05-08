package edu.cuny.brooklyn.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainViewController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainViewController.class);

	@FXML
	private Button previousPictureButton;

	@FXML
	private Button nextPictureButton;

	@FXML
	private Label pictureDateLabel;

	@FXML
	private Label titleLabel;
	
	@FXML
	private Label explanationLabel;

	@FXML
	private ScrollPane explanationPane;

	@FXML
	private ImageView imageView;
	
	@FXML
	private StackPane imageHolderPane;
	
	private NasaApodService nasaApodService;
	
	private LocalDate selectedDate;
	
	public void initialize() {
		imageView.setPreserveRatio(true);
		explanationPane.setFitToWidth(true);
		explanationPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		explanationPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		imageView.fitHeightProperty().bind(imageHolderPane.heightProperty());
		imageView.fitWidthProperty().bind(imageHolderPane.widthProperty());
		
		previousPictureButton.setOnAction(e -> prevNasaApod());
		nextPictureButton.setOnAction(e -> nextNasaApod());
	}

	public void setNasaApodService(NasaApodService service) {
		if (service == null) {
			throw new IllegalArgumentException("NasaApodService argument must not be null");
		}
		this.nasaApodService = service;
	}
	
	public void showFirst() throws IOException, URISyntaxException {
		showApod(null);
	}
	
	private void nextNasaApod() {
		LocalDate newDate = selectedDate.plusDays(1l);
		try {
			showApod(newDate);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} catch (URISyntaxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private void prevNasaApod()  {
		LocalDate newDate = selectedDate.minusDays(1l);
		try {
			showApod(newDate);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} catch (URISyntaxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private void showApod(LocalDate date) throws IOException, URISyntaxException {
		validateNasaApodService();
		NasaApod nasaApod = nasaApodService.getApod(date == null ? null : NasaApod.formatDate(date), true);
		titleLabel.setText(nasaApod.getTitle());
		pictureDateLabel.setText(nasaApod.getDateAsString());
		explanationLabel.setText(nasaApod.getExplanantion());
		
		if (nasaApod.getMediaType().equals("image")) {
			URI imageURI = nasaApod.getHdPictureURI() == null ? nasaApod.getMediaURI() : nasaApod.getHdPictureURI();
			imageView.setImage(new Image(imageURI.toString()));
		}
		selectedDate = nasaApod.getDate();
		
		if (LocalDate.now().equals(selectedDate)) {
			nextPictureButton.setDisable(true);
		} else {
			nextPictureButton.setDisable(false);
		}

	}


	private void validateNasaApodService() {
		if (nasaApodService == null) {
			throw new IllegalStateException("NasaApodService must not be null");
		}
	}
}
