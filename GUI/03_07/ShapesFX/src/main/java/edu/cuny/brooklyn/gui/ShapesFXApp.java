package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShapesFXApp extends Application {
	private static final double RECT_SCENE_WIDTH = 400.;
	private static final double RECT_SCENE_HEIGHT = 400.;
	private static final double RECT_WIDTH = 200.;
	private static final double RECT_HEIGHT = 200.;
	private static final double RECT_TOP_LEFT_X_POSITION = (RECT_SCENE_WIDTH - RECT_WIDTH)/2.;
	private static final double RECT_TOP_LEFT_Y_POSITION = (RECT_SCENE_HEIGHT - RECT_HEIGHT)/2.;
	private static final double CIRCLE_SCENE_WIDTH = 400.;
	private static final double CIRCLE_SCENE_HEIGHT = 400.;
	private static final double CIRCLE_CENTER_X = CIRCLE_SCENE_WIDTH/2.;
	private static final double CIRCLE_CENTER_Y = CIRCLE_SCENE_HEIGHT/2.;
	private static final double CIRCLE_RADIUS = 200.;


	private boolean hasOldX = false;
	private double xOld;
	private boolean movingRight = false;
	
	// UI components
	private Scene rectScene;
	private Scene circScene;
	private Rectangle rectangle;
	private Circle circle;
	private Stage primaryStage;


	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		rectScene = buildRectScene();
		circScene = buildCircleScene();
		registerEventHandlers();


		primaryStage.setScene(rectScene);
		primaryStage.setTitle("Rectangle on Primary Stage");
		primaryStage.show();
		System.out.println("after the primary stage is shown");
		// but did we see the rectangle at all on the primary stage?

		primaryStage.setScene(circScene);
		primaryStage.setTitle("Circle on Primary Stage");
		primaryStage.show();

		Stage secondaryStage = new Stage();
		secondaryStage.setTitle("Secondary Stage");
		secondaryStage.setScene(rectScene);
		secondaryStage.initModality(Modality.APPLICATION_MODAL);
//		secondaryStage.show();
		secondaryStage.showAndWait();
		// try one of the two above and observe the difference between 
		// show() and showAndWait(): when the statement below is executed
		System.out.println("after the secondary stage is shown");
	}

	private Scene buildRectScene() {
		Group root = new Group();
		rectangle = new Rectangle(RECT_TOP_LEFT_X_POSITION, RECT_TOP_LEFT_Y_POSITION, RECT_WIDTH,
				RECT_HEIGHT);
		rectangle.setFill(Color.RED);
		root.getChildren().add(rectangle);
		Scene rectScene = new Scene(root, RECT_SCENE_WIDTH, RECT_SCENE_HEIGHT);
		return rectScene;
	}
	
	private Scene buildCircleScene() {
		Group root = new Group();
		circle = new Circle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, CIRCLE_RADIUS);
		circle.setFill(Color.GREEN);
		root.getChildren().add(circle);
		Scene circScene = new Scene(root, CIRCLE_SCENE_WIDTH, CIRCLE_SCENE_HEIGHT);
		return circScene;
	}
	
	private void registerEventHandlers() {
		rectangle.setOnMouseEntered(e -> {
			if (movingRight) {
				System.out.println("Mouse entered from left");
			} else {
				System.out.println("Mouse entered from right");
			}
			primaryStage.setScene(circScene);
			primaryStage.setTitle("Circle on Primary Stage");
			primaryStage.show();
		});

		rectangle.setOnMouseMoved(e -> {
			if (!hasOldX) {
				xOld = e.getX();
				hasOldX = true;
			} else {
				double xNew = e.getX();
				if (xNew > xOld) {
					movingRight = true;
				} else {
					movingRight = false;
				}
			}
		});

		circle.setOnMouseExited(e -> {
			primaryStage.setScene(rectScene);
			primaryStage.setTitle("Rectangle on Primary Stage");
			primaryStage.show();
		});
	}
}
