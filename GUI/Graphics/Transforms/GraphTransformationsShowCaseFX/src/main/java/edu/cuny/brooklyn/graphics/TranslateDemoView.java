package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TranslateDemoView extends TransformBaseView {
	private double offset;
	
	public TranslateDemoView() {
		ColumnConstraints column = new ColumnConstraints(500, 500, Double.MAX_VALUE);
		RowConstraints row = new RowConstraints(500, 500, Double.MAX_VALUE);
		pane.getColumnConstraints().add(column);
		pane.getRowConstraints().add(row);
		
		Node node = createTranslationDemoNode();
		pane.add(node, 0, 0);
		Button button = new Button("->");
		pane.add(button, 0, 1);
		
		offset = 50;
		button.setOnAction(e -> translateNode(button, node.getParent(), node));
	}

	private Node createTranslationDemoNode() {
		Group group = new Group();
		Rectangle rect = new Rectangle(100, 200);
		rect.setFill(Color.SKYBLUE);
		group.getChildren().add(rect);
		return group;
	}
	
	private void translateNode(Button button, Parent parent, Node node) {
		double oldOffset = offset;
		if (node.getBoundsInParent().getMaxX() >= parent.getBoundsInLocal().getWidth()) {
			button.setText("<-");
			offset = -50;
		} else if (node.getBoundsInParent().getMinX() <= 0) {
			button.setText("->");
			offset = 50;
		}
		
		if (oldOffset == offset)
			translateNode(node, offset);
	}
	
	private void translateNode(Node node, double offset) {
		node.setTranslateX(node.getTranslateX()+offset);
	}
}
