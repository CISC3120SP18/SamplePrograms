package edu.cuny.brooklyn.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CsQuotesFXFXMLApp extends Application {
	private final static String APP_TITLE = "Quotations in Computer Science";
	private final static String MAIN_VIEW_FXML = "fxml_mainview.fxml";
	
	private CsQuotesTimerController timerController;

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane mainPane = (Pane) fxmlLoader.load(getClass().getResource(MAIN_VIEW_FXML).openStream());
		CsQuotesController controller = (CsQuotesController) fxmlLoader.getController();
		
		CsQuotesModel model = new CsQuotesModel();
		controller.setModel(model);
		
		timerController = new CsQuotesTimerController(model);
		timerController.startTimer();
	
		Scene mainScene = new Scene(mainPane);
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	
	@Override
	public void stop() {
		timerController.stopTimer();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
