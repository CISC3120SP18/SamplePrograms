package edu.cuny.brooklyn.graphics;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TimelineAnimationDemoView extends BaseView {

	public TimelineAnimationDemoView() {
		ColumnConstraints column = new ColumnConstraints(600., 600., Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT,
				true);
		pane.getColumnConstraints().add(column);

		RowConstraints row = new RowConstraints(600., 600., Double.MAX_VALUE);
		pane.getRowConstraints().add(row);

		Node node = createTimelineNode();
		Rectangle r = new Rectangle(0f, 0f, 600f, 600f);
		r.setFill(Color.WHITE);
		pane.add(r,  0,  0);
		pane.add(node, 0, 0);
		GridPane.setConstraints(node, 0, 0,  1, 1, HPos.LEFT, VPos.TOP, Priority.NEVER, Priority.NEVER);

		Button button = new Button("Start ...");
		pane.add(button, 0, 1);

		button.setOnAction(e -> startTimelineAnimation(node));
	}

	private void startTimelineAnimation(Node node) {
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		// AnimationBooleanInterpolator interp = new AnimationBooleanInterpolator();
		// we need x, y, and rotation to define key frames in time
		timeline.getKeyFrames().addAll(
				new KeyFrame(Duration.ZERO, new KeyValue(node.translateXProperty(), 0f),
						new KeyValue(node.translateYProperty(), 0f),
						new KeyValue(node.rotateProperty(), 0f)),
				new KeyFrame(Duration.millis(5000), new KeyValue(node.translateXProperty(), 400f),
						new KeyValue(node.translateYProperty(), 0f),
						new KeyValue(node.rotateProperty(), 0f)),
				new KeyFrame(Duration.millis(15000), new KeyValue(node.translateXProperty(), 500f),
						new KeyValue(node.translateYProperty(), 100f),
						new KeyValue(node.rotateProperty(), 90f)),
				new KeyFrame(Duration.millis(20000), new KeyValue(node.translateXProperty(), 500f),
						new KeyValue(node.translateYProperty(), 400f),
						new KeyValue(node.rotateProperty(), 90f)),
				new KeyFrame(Duration.millis(25000), new KeyValue(node.translateXProperty(), 400f),
						new KeyValue(node.translateYProperty(), 500f),
						new KeyValue(node.rotateProperty(), 235f)),
				new KeyFrame(Duration.millis(30000), new KeyValue(node.translateXProperty(), 0f),
						new KeyValue(node.translateYProperty(), 0f),
						new KeyValue(node.rotateProperty(), 235f)),
				new KeyFrame(Duration.millis(35000), new KeyValue(node.translateXProperty(), 0f),
						new KeyValue(node.translateYProperty(), 0f),
						new KeyValue(node.rotateProperty(), 360f))
				);
		timeline.play();
	}

	private Node createTimelineNode() {
		Group group = new Group();
		Node node = FancyShape.createAirPlane();
		group.getChildren().add(node);

		return group;
	}
	
	class AnimationBooleanInterpolator extends Interpolator {
		@Override
		protected double curve(double t) {
			return Math.abs(0.5 - t) * 2;
		}
	}
}
