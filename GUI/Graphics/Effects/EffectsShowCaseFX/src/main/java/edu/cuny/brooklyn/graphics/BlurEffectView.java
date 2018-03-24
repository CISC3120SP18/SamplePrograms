package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.MotionBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BlurEffectView extends EffectBaseView {
	public BlurEffectView() {
		pane.add(createBoxBlurNode(0.0, 0.0), 0, 0);
		pane.add(createBoxBlurNode(5.0, 5.0), 1, 0);
		pane.add(createBoxBlurNode(10.0, 10.0), 2, 0);
		
		pane.add(createMotionBlurNode(0.0, 0.0), 0, 1);
		pane.add(createMotionBlurNode(10.0, 90.0), 1, 1);
		pane.add(createMotionBlurNode(20.0, 180.0), 2, 1);
		
		pane.add(createGaussianBlurNode(0.0), 0, 2);
		pane.add(createGaussianBlurNode(5.0), 1, 2);
		pane.add(createGaussianBlurNode(15.0), 2, 2);

	}

	private Node createBoxBlurNode(double width, double height) {
		Group g = new Group();
		BoxBlur boxBlur = new BoxBlur();
		boxBlur.setWidth(width);
		boxBlur.setHeight(height);
		boxBlur.setIterations(3);

		Text text = new Text();
		text.setText("Box Blur!");
		text.setFill(Color.web("0x3b596d"));
		text.setFont(Font.font(null, FontWeight.BOLD, 50));
		text.setX(10);
		text.setY(50);
		text.setEffect(boxBlur);

		g.getChildren().add(text);

		return g;
	}

	private Node createMotionBlurNode(double radius, double angle) {
		Group g = new Group();
		MotionBlur motionBlur = new MotionBlur();
		motionBlur.setRadius(radius);
		motionBlur.setAngle(angle);

		Text text = new Text();
		text.setText("Motion Blur!");
		text.setFill(Color.web("0x3b596d"));
		text.setFont(Font.font(null, FontWeight.BOLD, 50));
		text.setX(10);
		text.setY(50);
		text.setEffect(motionBlur);

		g.getChildren().add(text);

		return g;
	}
	
	private Node createGaussianBlurNode(double radius) {
		Group g = new Group();
		GaussianBlur gaussianBlur = new GaussianBlur(radius);

		Text text = new Text();
		text.setText("Gaussian Blur!");
		text.setFill(Color.web("0x3b596d"));
		text.setFont(Font.font(null, FontWeight.BOLD, 50));
		text.setX(10);
		text.setY(50);
		text.setEffect(gaussianBlur);

		g.getChildren().add(text);

		return g;
	}

}
