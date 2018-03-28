package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *	Guideline (Skin this application with CSS) 
 *		1. Create folder src/main/resources where we place CSS files.
 *			For this, in Eclipse, select "src/main" folder (not the package), and
 *			from the Eclipse menu, do "File -> New -> Folder". Make sure you spell
 *			"resources" correctly. 
 *		2.	Create CSS file under src/main/resources, e.g., appmain.css. 
 *			For this, in Eclipse, from the menu, do 
 *				"File -> New -> Other -> Web -> CSS". 
 *			Make sure you create the CSS in the src/main/resources folder of
 *			the project you are working on.
 */
public class CsQuotesFXApp extends Application {
	private static final int INIT_MAIN_SCENE_WIDTH = 800;
	private static final int INIT_MAIN_SCENE_HEIGHT = 600;
	
	private static final String APP_TITLE = "Quotations in Computer Science";
	private static final String APP_ICON_IMAGE = "appmain.png";

	private static final String MAIN_CSS_FILE = "appmain.css";
	
	private CsQuotesTimerController timerController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		CsQuotesModel model = new CsQuotesModel();
		CsQuotesView view = new CsQuotesView(model, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
		CsQuotesController controller = new CsQuotesController(model, view);
		controller.initialize(0);
		
		timerController = new CsQuotesTimerController(model);
		timerController.startTimer();
		
		Scene mainScene = new Scene(view.getRoot(), INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
		mainScene.getStylesheets().add(MAIN_CSS_FILE);
		primaryStage.setTitle(APP_TITLE);
		primaryStage.getIcons().add(new Image(APP_ICON_IMAGE));
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
