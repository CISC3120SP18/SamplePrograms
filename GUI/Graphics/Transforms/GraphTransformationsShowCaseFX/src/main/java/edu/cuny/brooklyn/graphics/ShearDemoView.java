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
import javafx.scene.transform.Shear;

public class ShearDemoView extends TransformBaseView {
	public ShearDemoView() {
		ColumnConstraints column = new ColumnConstraints(600., 600., Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true);
		pane.getColumnConstraints().add(column);
		
		RowConstraints row = new RowConstraints(600., 600., Double.MAX_VALUE);
		pane.getRowConstraints().add(row);
		
		Shear shear = new Shear();

		pane.add(createShearNode(shear), 0, 0);
		
		Button button = new Button("Shear!!");
		// shear.setPivotX(200);
		button.setOnAction(e -> shearX(shear));
		pane.add(button, 0, 1);
	}

	private Node createShearNode(Shear shear) {
		Group group = new Group();
		
		Rectangle rectangle = new Rectangle(200, 100);
		rectangle.setFill(Color.SKYBLUE);
		
		Circle circle = new Circle(100);
		circle.setCenterX(300);
		circle.setCenterY(50);
		circle.setFill(Color.DARKORANGE);
		
		group.getChildren().addAll(rectangle, circle);
		group.getTransforms().add(shear);
		
		return group;
	}
	
	private void shearX(Shear shear) {
		shear.setX(shear.getX() + 0.1);
	}
}
