package edu.cuny.brooklyn.gui;

import java.io.Serializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CsQuotesController implements Serializable {
	private static final long serialVersionUID = 8415380762906053627L;

	private CsQuotesModel model;

	@FXML
	private Label sceneTitleLabel;

	@FXML
	private Label quoteTextLabel;

	@FXML
	private ImageView portraitHolder;

	@FXML
	private Button nextQuoteButton;

	@FXML
	void initialize() {
	}

	@FXML
	public void nextQuote() {
		validateModel();
		model.advanceActiveQuoteIndex();
		updateView(model.getActiveQuoteIndex());
	}
	
	public void setModel(CsQuotesModel model) {
		this.model = model;
		initialize(0);
		setAutoUpdate();
	}
	

	private void initialize(int startIndex) {
		validateModel();
		model.setActiveQuoteIndex(startIndex);
		updateView(startIndex);
		nextQuoteButton.setOnAction(e -> nextQuote());
	}


	private void updateView(int quoteIndex) {
		portraitHolder.setImage(new Image(model.getPortrait(quoteIndex), 300, 300, true, true));
		quoteTextLabel.setText(model.getQuote(quoteIndex) + "     --" + model.getAuthor(quoteIndex));
	}

	private void validateModel() {
		if (model == null) {
			throw new IllegalStateException(
					"CSQuotesModel must not be null. Set the model in CsQuotesController before the View is initialized.");
		}
	}
	
	private void setAutoUpdate() {
		validateModel();
		model.activeQuoteIndexProperty().addListener((obsv, oldv, newv) -> updateView(newv.intValue()));
	}
}
