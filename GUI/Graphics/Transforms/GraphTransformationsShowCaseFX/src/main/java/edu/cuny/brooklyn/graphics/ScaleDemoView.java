package edu.cuny.brooklyn.graphics;

import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

public class ScaleDemoView extends TransformBaseView {
	
	public ScaleDemoView() {
		ColumnConstraints column = new ColumnConstraints(600., 600., Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true);
		pane.getColumnConstraints().add(column);
		
		RowConstraints row = new RowConstraints(600., 600., Double.MAX_VALUE);
		pane.getRowConstraints().add(row);
		
		Scale scale = new Scale();

		pane.add(createScalingNode(scale), 0, 0);
		
		Button button = new Button("Scale!");
		scale.setPivotX(200);
		button.setOnAction(e -> scaleXY(scale));
		pane.add(button, 0, 1);
	}

	private Node createScalingNode(Scale scale) {
		Group group = new Group();
		
		Rectangle rectangle = new Rectangle(200, 100);
		rectangle.setFill(Color.SKYBLUE);
		
		Circle circle = new Circle(100);
		circle.setCenterX(300);
		circle.setCenterY(50);
		circle.setFill(Color.DARKORANGE);
		
		group.getChildren().addAll(rectangle, circle);
		group.getTransforms().add(scale);
		
		return group;
	}
	
	private void scaleXY(Scale scale) {
		scale.setX(scale.getX() + 0.1);
		scale.setY(scale.getY() + 0.1);
	}
}
