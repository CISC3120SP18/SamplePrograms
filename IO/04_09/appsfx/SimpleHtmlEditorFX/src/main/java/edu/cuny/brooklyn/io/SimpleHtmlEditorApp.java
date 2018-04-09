/**
 * This is the start-up code of an exercise. See TODO's in the MainViewController 
 * and the SimpleHtmlEditor classes for more details. The summaries of the
 * assignments are,
 * (1) TODO 1. Complete the readFile method in the SimpleHtmlEditor class
 * (2) TODO 2. Complete the saveTheFile method in the SimpleHtmlEditor class
 * (3) TODO 3. Design and complete the Help|About menu functionality. 
 * (4) TODO 4. (Bonus) Add a section of the history of opened files in the 
 *             File menu. 
 */

package edu.cuny.brooklyn.io;

import edu.cuny.brooklyn.io.controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleHtmlEditorApp extends Application {
	private final static String FXML_MAIN_VIEW = "view/fxml_mainview.fxml";
	private final static String APP_TITLE = "Simple Html Editor";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_MAIN_VIEW));
		Parent root = loader.load();

		
		MainViewController controller = loader.getController();
		controller.setStage(primaryStage); // inject dependency of a Stage to the controller
		
		primaryStage.setTitle(APP_TITLE);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
