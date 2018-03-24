package edu.cuny.brooklyn.graphics;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class SequentialTransitionDemoView extends BaseView {

	public SequentialTransitionDemoView() {
		ColumnConstraints column = new ColumnConstraints(600., 600., Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT,
				true);
		pane.getColumnConstraints().add(column);

		RowConstraints row = new RowConstraints(600., 600., Double.MAX_VALUE);
		pane.getRowConstraints().add(row);

		Node node = createPathTransitionNode();
		pane.add(node, 0, 0);

		Button button = new Button("Start ...");
		pane.add(button, 0, 1);

		button.setOnAction(e -> startSequentialTransition(node));
	}

	private void startSequentialTransition(Node node) {
		final Duration SEC_2 = Duration.millis(2000);
		final Duration SEC_3 = Duration.millis(3000);

		PauseTransition pt = new PauseTransition(Duration.millis(1000));
		FadeTransition ft = new FadeTransition(SEC_3);
		ft.setFromValue(1.0f);
		ft.setToValue(0.3f);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		TranslateTransition tt = new TranslateTransition(SEC_2);
		tt.setFromX(-100f);
		tt.setToX(100f);
		tt.setCycleCount(2);
		tt.setAutoReverse(true);
		RotateTransition rt = new RotateTransition(SEC_3);
		rt.setByAngle(180f);
		rt.setCycleCount(4);
		rt.setAutoReverse(true);
		ScaleTransition st = new ScaleTransition(SEC_2);
		st.setByX(1.5f);
		st.setByY(1.5f);
		st.setCycleCount(2);
		st.setAutoReverse(true);

		SequentialTransition seqT = new SequentialTransition(node, pt, ft, tt, rt, st);
		seqT.play();
	}

	private Node createPathTransitionNode() {
		Group group = new Group();
		Node node = FancyShape.createAirPlane();
		group.getChildren().add(node);

		return group;
	}
}
