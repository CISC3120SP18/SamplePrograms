/**
 * This is the start-up code for an exercise. The application as is crashes when launched. 
 * The tasks are described in the assignment description, see the class website, and the 
 * Blackboard site to locate the assignment due date and  the assignment description. 
 */

package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CalculatorFXApp extends Application {
	private final static String MAIN_VIEW_FXML = "fxml_mainview.fxml";
	private final static String APP_TITLE = "Simple JavaFX Calculator";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane mainPane = (Pane) FXMLLoader.load(getClass().getResource(MAIN_VIEW_FXML));
		Scene mainScene = new Scene(mainPane);

		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
}
