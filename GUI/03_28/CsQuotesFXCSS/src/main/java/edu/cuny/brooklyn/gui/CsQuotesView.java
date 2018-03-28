package edu.cuny.brooklyn.gui;

//import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CsQuotesView {
//	private static final Insets PADDING = new Insets(20f, 20f, 20f, 20f);
//	private static final double hGap = 5f;
//	private static final double vGap = 5f;
	private static final int GRID_PANE_GRIDS_IN_ROW = 10;
	private static final int GRID_PANE_GRIDS_IN_COL = 10;
	
	private double imageWidth;
	private double imageHeight;
	
	private CsQuotesModel csQuotesModel; 
	
	private GridPane gridPane;
	private ImageView portrait;
	private Text quoteText;
	private Button nextQuoteButton;

	public CsQuotesView(CsQuotesModel model, double mainSceneWidth, double mainSceneHeight) {
		csQuotesModel = model;
		
		
		gridPane = new GridPane();
//		gridPane.setPadding(PADDING);
//		gridPane.setHgap(hGap);
//		gridPane.setVgap(vGap);

		// divide the pane into equal width grid of 10 in a row
		double gridWidth = mainSceneWidth / GRID_PANE_GRIDS_IN_ROW;
		for (int i = 0; i < GRID_PANE_GRIDS_IN_ROW; i++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(gridWidth));
		}

		double gridHeight = mainSceneHeight / GRID_PANE_GRIDS_IN_COL;
		for (int i = 0; i < GRID_PANE_GRIDS_IN_COL; i++) {
			gridPane.getRowConstraints().add(new RowConstraints(gridHeight));
		}

		// add a Text as the title of the scene
		Text mainSceneTitle = new Text("Quotations in Computer Science");
		mainSceneTitle.getStyleClass().add("scene-title");
		gridPane.add(mainSceneTitle, 1, 1, 8, 1);

		// add an image to show portrait of the person whose quote is being displayed
		StackPane imageHolder = new StackPane();
		portrait = new ImageView();
		imageHolder.getChildren().add(portrait);
		imageHolder.setAlignment(Pos.TOP_LEFT);
		gridPane.add(imageHolder, 0, 3, 3, 3);
		imageWidth = 3*gridWidth;
		imageHeight = 3*gridHeight;

		StackPane quoteTextHolder = new StackPane();
		quoteTextHolder.setId("cs-quote-text-holder");
		quoteText = new Text();
		quoteText.setWrappingWidth(gridWidth * 6);
		quoteText.setTextAlignment(TextAlignment.JUSTIFY);
		quoteTextHolder.getChildren().add(quoteText);
		quoteTextHolder.setAlignment(Pos.TOP_LEFT);
		gridPane.add(quoteTextHolder, 3, 2, 6, 6);

		nextQuoteButton = new Button("Next Quote");
		nextQuoteButton.setId("brooklyn-orange-next-quote");
		gridPane.add(nextQuoteButton, 6, 8, 4, 1);
		
		setAutoUpdate();
	}
	

	public Button getNextQuoteButton() {
		return nextQuoteButton;
	}
	
	public Parent getRoot() {
		return gridPane;
	}
	
	public void updateView(int quoteIndex) {
		portrait.setImage(new Image(csQuotesModel.getPortrait(quoteIndex), imageWidth, imageHeight, true, true));
		quoteText.setText(csQuotesModel.getQuote(quoteIndex) + "     --" + csQuotesModel.getAuthor(quoteIndex));
	}
	
	private void setAutoUpdate() {
		csQuotesModel.activeQuoteIndexProperty().addListener((obsv, oldv, newv) -> updateView(newv.intValue()));
	}
}
