package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ScenesFXApp extends Application {
	private final static double SCENE_WIDTH = 500;
	private final static double SCENE_HEIGHT = 400;
	private final static double RECTANGLE_WIDTH = 400;
	private final static double RECTANGLE_HEIGHT = 300;
	private final static double RECTANGLE_X = (SCENE_WIDTH - RECTANGLE_WIDTH)/2.;
	private final static double RECTANGLE_Y = (SCENE_HEIGHT - RECTANGLE_HEIGHT)/2.;
	private final static double CIRCLE_RADIUS = 200;
	private final static double CIRCLE_CENTER_X = 250;
	private final static double CIRCLE_CENTER_Y = 200;

	private final static String APP_TITLE_PRIMARY = "Primary: Demonstrating JavaFX Scenes";
	private final static String APP_TITLE_SECONDARY = "Secondary: Demonstrating JavaFX Scenes";
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		final Scene rectScene = buildSceneWithRectangle();
		final Scene circScene = buildSceneWithCircle();
		
		primaryStage.setTitle(APP_TITLE_PRIMARY);
		primaryStage.setScene(rectScene);	
		primaryStage.show();
		
		Stage secondaryStage = new Stage();
		secondaryStage.setTitle(APP_TITLE_SECONDARY);
		secondaryStage.setScene(circScene);
		// we will make an infinite loop to show
		//    a stage can set with different scenes at a time
		//    a scene can only be added to a stage at a time
//		secondaryStage.setOnHidden(e->{
//			if (secondaryStage.getScene() == circScene) {
//				secondaryStage.setScene(rectScene);
//			} else {
//				secondaryStage.setScene(circScene);
//			}
//			if (primaryStage.getScene() == rectScene) {
//				primaryStage.setScene(circScene);
//			} else {
//				primaryStage.setScene(rectScene);
//			}
//			primaryStage.show();
//		});
//		primaryStage.setOnHidden(e->{
//			secondaryStage.show();
//		});	
		secondaryStage.show();
		
	}
	
	private Scene buildSceneWithRectangle() {
		Group root = new Group();
		Rectangle rectangle = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT, Color.RED);
		rectangle.setX(RECTANGLE_X);
		rectangle.setY(RECTANGLE_Y);
		root.getChildren().add(rectangle);
		Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		return scene;
	}
	
	private Scene buildSceneWithCircle() {
		Group root = new Group();
		Circle circle = new Circle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, CIRCLE_RADIUS, Color.BLUE);
		root.getChildren().add(circle);
		Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
		return scene;
	}	
}
