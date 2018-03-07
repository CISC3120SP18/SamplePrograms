package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StagesFXApp extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/* 
		 * A JavaFX Stage is the top level container for JavaFX components,
		 * a Stage is associated with a Window called the owner Window, 
		 * and the Window can be made visible. Each JavaFX application has
		 * a primary Stage that is created by the JavaFX platform. A user
		 * is free to create additional Stages. 
		 */
		primaryStage.setTitle("This is the primary Stage");
		primaryStage.show();
		
		Stage decoratedStage = new Stage(StageStyle.DECORATED);
		decoratedStage.setTitle("This is a Stage with the StageStyle.DECORATED style.");
		/* A window can have a list of icons, each is an image. The 
		 * applications needs to be able to locate these images.  */
		decoratedStage.getIcons().add(new Image("cat.png"));
		decoratedStage.show();
		
		Stage undecoratedStage = new Stage(StageStyle.UNDECORATED);
		undecoratedStage.setTitle("This is a Stage with the StageStyle.UNDECORATED style.");
		undecoratedStage.getIcons().add(new Image("cat.png"));
		undecoratedStage.show();
		
		Stage transparentStage = new Stage(StageStyle.TRANSPARENT);
		transparentStage.setTitle("This is a Stage with the StageStyle.TRANSPARENT style.");
		transparentStage.getIcons().add(new Image("cat.png"));
		transparentStage.show();	
		
		Stage utilityStage = new Stage(StageStyle.UTILITY);
		utilityStage.setTitle("This is a Stage with the StageStyle.UTILITY style.");
		utilityStage.getIcons().add(new Image("cat.png"));
		utilityStage.show();
		
		/* A Window has "modality", by default, a window's modality is
		 * set as Modality.NONE. You may initialize a Stage's modality
		 * as Modality.APPLICATION_MODAL, which blocks other windows 
		 * from receiving any inputs, or as Modality.WINDOW_MODEL, which
		 * blocks the parent Window */
		Stage parentStageToWindowModalStage = new Stage();
		parentStageToWindowModalStage.setTitle("A Stage serving as the parent of another Stage.");
		parentStageToWindowModalStage.show();
		
		Stage windowModalStage = new Stage();
		windowModalStage.initOwner(parentStageToWindowModalStage);
		windowModalStage.initModality(Modality.WINDOW_MODAL);
		windowModalStage.setTitle("A stage initialized with Modality.WINDOW_MODAL");
		windowModalStage.show();
		
		Stage appModalStage = new Stage();
		appModalStage.initModality(Modality.APPLICATION_MODAL);
		appModalStage.setTitle("This a Stage initialized with Modality.APPLICATION_MODAL");
		appModalStage.getIcons().add(new Image("cat.png"));
		appModalStage.show();
	}
}
