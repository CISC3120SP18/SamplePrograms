package edu.cuny.brooklyn.graphics;


import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
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

public class SequentialTimelineAnimationDemoView extends BaseView {

	public SequentialTimelineAnimationDemoView() {
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
		final Timeline[] timelines = new Timeline[5];
		timelines[0] = new Timeline();
//		AnimationBooleanInterpolator xInterp = new AnimationBooleanInterpolator();
		timelines[0].getKeyFrames().addAll(
				new KeyFrame(Duration.millis(0), new KeyValue(node.translateXProperty(), 0),
						 new KeyValue(node.translateYProperty(), 0)),
				new KeyFrame(Duration.millis(5000), new KeyValue(node.translateXProperty(), 400f)));
		
//		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
//				new KeyValue(node.translateXProperty(), 100, Interpolator.EASE_BOTH)));
//		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000),
//				new KeyValue(node.rotateProperty(), 90)));
//		timeline.getKeyFrames().add(
//				new KeyFrame(Duration.millis(5000),
//				new KeyValue(node.translateXProperty(), 100, xInterp)));
//		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000),
//				new KeyValue(node.translateXProperty(), 100, xInterp)));
//		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(2000),
//				new KeyValue(node.rotateProperty(), 90, Interpolator.EASE_BOTH)));
		timelines[1] = new Timeline();
		timelines[1].getKeyFrames()
				.addAll(new KeyFrame(Duration.millis(5000), new KeyValue(node.translateXProperty(), 500f),
						new KeyValue(node.translateYProperty(), 100f)),
						new KeyFrame(Duration.millis(5000), new KeyValue(node.rotateProperty(), 90f)));

		timelines[2] = new Timeline();
		timelines[2].getKeyFrames()
				.addAll(new KeyFrame(Duration.millis(5000), new KeyValue(node.translateYProperty(), 400f)));
		
		timelines[3] = new Timeline();
		timelines[3].getKeyFrames()
				.addAll(new KeyFrame(Duration.millis(5000), new KeyValue(node.translateXProperty(), 400f),
						new KeyValue(node.translateYProperty(), 500f)),
						new KeyFrame(Duration.millis(5000), new KeyValue(node.rotateProperty(), 230f)));
		
		timelines[4] = new Timeline();
		timelines[4].getKeyFrames()
				.addAll(new KeyFrame(Duration.millis(5000), new KeyValue(node.translateXProperty(), 0f),
						new KeyValue(node.translateYProperty(), 0f)));
		
		SequentialTransition sequence = new SequentialTransition(timelines);
		sequence.play();
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
