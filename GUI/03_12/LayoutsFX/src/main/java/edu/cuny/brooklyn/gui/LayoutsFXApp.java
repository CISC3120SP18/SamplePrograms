package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutsFXApp extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage demoStage = new Stage();
		Scene scene = buildMainScene(demoStage);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Scene buildMainScene(Stage demoStage) {
		final Class<?>[] layoutClasses = { HBox.class, VBox.class, TilePane.class, GridPane.class, FlowPane.class,
				AnchorPane.class, StackPane.class, BorderPane.class };
		final double widthOfScene = 850;
		final double heightOfScene = 400;

		VBox vbox = new VBox();
		for (int i = 0; i < layoutClasses.length; i++) {
			Button button = new Button("Create Demo for " + layoutClasses[i].getSimpleName());
			button.setUserData(layoutClasses[i]);
			button.setOnAction(e -> showDemoStage(button));
			vbox.getChildren().add(button);
		}
		Scene scene = new Scene(vbox, widthOfScene, heightOfScene);
		return scene;
	}

	private void showDemoStage(Button button) {
		Scene scene = null;
		Class<?> layoutClazz = (Class<?>) button.getUserData();
		if (layoutClazz.isAssignableFrom(HBox.class)) {
			scene = buildHBoxLayoutScene();
		} else if (layoutClazz.isAssignableFrom(VBox.class)) {
			scene = buildVBoxLayoutScene();
		} else if (layoutClazz.isAssignableFrom(TilePane.class)) {
			scene = buildTilePaneLayoutScene();
		} else if (layoutClazz.isAssignableFrom(GridPane.class)) {
			scene = buildGridPaneLayoutScene();
		} else if (layoutClazz.isAssignableFrom(FlowPane.class)) {
			scene = buildFlowPaneLayoutScene();
		} else if (layoutClazz.isAssignableFrom(AnchorPane.class)) {
			scene = buildAnchorPaneLayoutScene();
		} else if (layoutClazz.isAssignableFrom(StackPane.class)) {
			scene = buildStackPaneLayoutScene();
		} else if (layoutClazz.isAssignableFrom(BorderPane.class)) {
			scene = buildBorderPaneLayoutScene();
		}

		Stage demoStage = new Stage();
		demoStage.setScene(scene);
		demoStage.show();
	}

	private Scene buildHBoxLayoutScene() {
		final double numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;
		HBox hbox = new HBox();

		for (int i = 0; i < numOfTextFields; i++) {
			hbox.getChildren().add(new TextField("Course # " + i));
		}
		hbox.getChildren().add(new Button("Submit"));

		return new Scene(hbox, widthOfScene, heightOfScene);
	}

	private Scene buildVBoxLayoutScene() {
		final double numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;
		VBox vbox = new VBox();

		for (int i = 0; i < numOfTextFields; i++) {
			vbox.getChildren().add(new TextField("Course # " + i));
		}
		vbox.getChildren().add(new Button("Submit"));

		return new Scene(vbox, widthOfScene, heightOfScene);
	}

	private Scene buildTilePaneLayoutScene() {
		final double numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;
		TilePane tilePane = new TilePane();
		tilePane.setPrefColumns(2);
		for (int i = 0; i < numOfTextFields; i++) {
			tilePane.getChildren().add(new TextField("Course # " + i));
		}
		tilePane.getChildren().add(new Button("Submit"));

		return new Scene(tilePane, widthOfScene, heightOfScene);
	}

	private Scene buildGridPaneLayoutScene() {
		final int numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;
		final int numOfColumns = 2;
		GridPane gridPane = new GridPane();

		int row;
		int col;
		for (int i = 0; i < numOfTextFields; i++) {
			row = i / numOfColumns;
			col = i % numOfColumns;
			gridPane.add(new TextField("Course # " + i), col, row);
		}
		row = numOfTextFields / numOfColumns;
		col = numOfTextFields % numOfColumns;
		gridPane.add(new Button("Submit"), col, row);

		return new Scene(gridPane, widthOfScene, heightOfScene);
	}

	private Scene buildFlowPaneLayoutScene() {
		final double numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;

		FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
		for (int i = 0; i < numOfTextFields; i++) {
			flowPane.getChildren().add(new TextField("Course # " + i));
		}
		flowPane.getChildren().add(new Button("Submit"));

		return new Scene(flowPane, widthOfScene, heightOfScene);
	}

	private Scene buildAnchorPaneLayoutScene() {
		final double numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;
		final double anchorIntervalY = 50.;

		AnchorPane anchorPane = new AnchorPane();

		for (int i = 0; i < numOfTextFields; i++) {
			TextField textField = new TextField("Course # " + i);
			AnchorPane.setTopAnchor(textField, 10. + anchorIntervalY * i);
			AnchorPane.setLeftAnchor(textField, 10.0);
			AnchorPane.setRightAnchor(textField, 65.0);
			anchorPane.getChildren().add(textField);
		}

		Button button = new Button("Submit");

		AnchorPane.setTopAnchor(button, 10. + anchorIntervalY * numOfTextFields);
		AnchorPane.setLeftAnchor(button, 10.0);
		anchorPane.getChildren().add(button);

		return new Scene(anchorPane, widthOfScene, heightOfScene);
	}
	
	private Scene buildStackPaneLayoutScene() {
		final double numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;

		StackPane stackPane = new StackPane();
		for (int i = 0; i < numOfTextFields; i++) {
			stackPane.getChildren().add(new TextField("Course # " + i));
		}
		stackPane.getChildren().add(new Button("Submit"));

		return new Scene(stackPane, widthOfScene, heightOfScene);
	}
	
	private Scene buildBorderPaneLayoutScene() {
		final double numOfTextFields = 4;
		final double widthOfScene = 850;
		final double heightOfScene = 400;

		BorderPane borderPane = new BorderPane();
		VBox vbox = new VBox();
		for (int i = 0; i < numOfTextFields; i++) {
			vbox.getChildren().add(new TextField("Course # " + i));
		}
		borderPane.setCenter(vbox);
		borderPane.setRight(new Button("Submit"));

		return new Scene(borderPane, widthOfScene, heightOfScene);
	}
}
