package edu.cuny.brooklyn.graphics;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FadeTransitionDemoView extends BaseView {

	public FadeTransitionDemoView() {
		Node node = createFadeTransitionNode();
		pane.add(node, 0, 0);
		
		Button button = new Button("Start ...");
		pane.add(button, 0, 1);
		
		button.setOnAction(e -> startFadeTransition(node));
	}

	private void startFadeTransition(Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(3000), node);
		ft.setFromValue(1.0);
		ft.setToValue(0.3);
		ft.setCycleCount(4);
		ft.setAutoReverse(true);

		ft.play();
	}

	private Node createFadeTransitionNode() {
		Group group = new Group();
		Rectangle rect = new Rectangle(300, 300);
		group.getChildren().add(rect);
		rect.setArcHeight(50);
		rect.setArcWidth(50);
		rect.setFill(Color.VIOLET);

		return group;
	}
}
