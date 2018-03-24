package edu.cuny.brooklyn.graphics;

import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class PathTransitionDemoView extends BaseView {

	public PathTransitionDemoView() {
		ColumnConstraints column = new ColumnConstraints(600., 600., Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true);
		pane.getColumnConstraints().add(column);
		
		RowConstraints row = new RowConstraints(600., 600., Double.MAX_VALUE);
		pane.getRowConstraints().add(row);
		
		Node node = createPathTransitionNode();
		pane.add(node, 0, 0);

		Button button = new Button("Start ...");
		pane.add(button, 0, 1);

		button.setOnAction(e -> startPathTransition(node));
	}

	private void startPathTransition(Node node) {
		Path path = new Path();
		path.getElements().add(new MoveTo(20,20));
		path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
		path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));

		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(10000));
		pathTransition.setNode(node);
		pathTransition.setPath(path);
		pathTransition.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(4);
		pathTransition.setAutoReverse(true);

		pathTransition.play();
	}

	private Node createPathTransitionNode() {
		Group group = new Group();
		Node node = FancyShape.createAirPlane();
		group.getChildren().add(node);

		return group;
	}
}
