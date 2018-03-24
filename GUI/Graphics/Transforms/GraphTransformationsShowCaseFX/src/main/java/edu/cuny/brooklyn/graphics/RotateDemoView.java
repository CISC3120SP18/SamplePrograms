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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class RotateDemoView extends TransformBaseView {
	public RotateDemoView() {

		ColumnConstraints column = new ColumnConstraints(600., 600., Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true);
		pane.getColumnConstraints().add(column);
		
		RowConstraints row = new RowConstraints(600., 600., Double.MAX_VALUE);
		pane.getRowConstraints().add(row);
		
		Rotate rotate = new Rotate(0, 300, 300);
		pane.add(createRotationDemoNode(rotate), 0, 0);
		
		Button button = new Button("Rotate");
		pane.add(button,  0,  1);
		button.setOnAction(e -> rotate.setAngle(rotate.getAngle() + 30));
	}

	private Node createRotationDemoNode(Rotate rotate) {
		Group group = new Group();
		
		Circle circle = new Circle(300);
		circle.setFill(Color.WHITE);
		circle.setCenterX(300);
		circle.setCenterY(300);

		Rectangle rectTop = new Rectangle(200, 100);
		rectTop.setX(200);
		rectTop.setY(100);
		rectTop.setFill(Color.STEELBLUE);
		rectTop.getTransforms().add(rotate);
		
		Rectangle rectBottom = new Rectangle(200, 100);
		rectBottom.setX(200);
		rectBottom.setY(400);
		rectBottom.setFill(Color.STEELBLUE);
		rectBottom.getTransforms().add(rotate);

		Text text = new Text("Rotation!");
		text.setX(200);
		text.setY(250);
		text.setFont(new Font(20));
		

		group.getChildren().addAll(circle, rectTop, rectBottom, text);
		text.getTransforms().add(rotate);
		return group;
	}
}
