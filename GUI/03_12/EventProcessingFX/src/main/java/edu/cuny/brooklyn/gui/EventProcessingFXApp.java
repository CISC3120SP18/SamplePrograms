package edu.cuny.brooklyn.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EventProcessingFXApp extends Application {
	private StringBuilder sb;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Rectangle rectangle = new Rectangle(400., 100., Color.BLUE);
		root.getChildren().add(rectangle);
		rectangle.setX(0);
		rectangle.setY(0);

		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: black;");
		pane.setLayoutY(100);
		pane.setPrefSize(400, 300);
		Circle circle = new Circle(100, Color.ALICEBLUE);
		circle.relocate(20, 50);
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(new Double[] { 50.0, 0.0, 0.0, 200.0, 100.0, 200.0 });
		triangle.relocate(250, 50);
		triangle.setFill(Color.GREEN);
		pane.getChildren().addAll(circle, triangle);
		root.getChildren().add(pane);

		Scene scene = new Scene(root, 400., 400.);

		sb = new StringBuilder();
		// default event capturing and bubbling chain
		// event capturing: from root to leaf, invokes event-filter method 
		primaryStage.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			sb.setLength(0);
			sb.append("captured at primaryStage");
			System.out.println("event filter: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			sb.append("-> captured at scene");
			System.out.println("event filter: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		root.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			sb.append("-> captured at root");
			System.out.println("event filter: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			sb.append("-> captured at rectangle");
			System.out.println("event filter: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		pane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			sb.append("-> captured at pane");
			System.out.println("event filter: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		circle.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			sb.append("-> captured at circle");
			System.out.println("event filter: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		triangle.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			sb.append("-> captured at triangle");
			System.out.println("event filter: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		
		
		// event bubbling, in reverse order, invoke event handler
		// circle.setOnMouseclicked (...) is equivalent to 
//		circle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
//			sb.append("-> bubbled at circle");
//			System.out.println(sb.toString().replaceAll("->", "\n\t->"));
//			System.out
//					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
//			// e.consume();
//		});
		circle.setOnMouseClicked(e -> {
			sb.append("-> bubbled at circle");
			System.out.println(sb.toString().replaceAll("->", "\n\t->"));
			System.out
					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		triangle.setOnMouseClicked(e -> {
			sb.append("-> bubbled at triangle");
			System.out.println(sb.toString().replaceAll("->", "\n\t->"));
			System.out
					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		rectangle.setOnMouseClicked(e -> {
			sb.append("-> bubbled at rectangle");
			System.out.println(sb.toString().replaceAll("->", "\n\t->"));			
			System.out
					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		pane.setOnMouseClicked(e -> {
			sb.append("-> bubbled at pane");
			System.out.println(sb.toString().replaceAll("->", "\n\t->"));			
			System.out
					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		root.setOnMouseClicked(e -> {
			sb.append("-> bubbled at root");
			System.out.println(sb.toString().replaceAll("->", "\n\t->"));			
			System.out
					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		scene.setOnMouseClicked(e -> {
			sb.append("-> bubbled at scene");
			System.out.println(sb.toString().replaceAll("->", "\n\t->"));			
			System.out
					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});
		primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			sb.append("-> bubbled at primaryStage");
			System.out.println(sb.toString().replaceAll("->", "\n\t->"));
			System.out
					.println("event handler: mouse clicked: source -> target:" + e.getSource() + "->" + e.getTarget());
			// e.consume();
		});

		
		primaryStage.setTitle("EventProcessingFX");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
