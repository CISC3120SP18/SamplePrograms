package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CsQuotesFXApp extends Application {
	private static final int INIT_MAIN_SCENE_WIDTH = 800;
	private static final int INIT_MAIN_SCENE_HEIGHT = 600;
	
	private static final String APP_TITLE = "Quotations in Computer Science";
	private static final String APP_ICON_IMAGE = "appmain.png";

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		CsQuotesModel model = new CsQuotesModel();
		CsQuotesView view = new CsQuotesView(model, INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
		CsQuotesController controller = new CsQuotesController(model, view);
		controller.initialize(0);
		
		Scene mainScene = new Scene(view.getRoot(), INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
		primaryStage.setTitle(APP_TITLE);
		primaryStage.getIcons().add(new Image(APP_ICON_IMAGE));
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
