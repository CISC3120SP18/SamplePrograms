package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BloomEffectView extends EffectBaseView {
	public BloomEffectView() {
		pane.add(createBloomNode(0.00), 0, 0);
		pane.add(createBloomNode(0.25), 1, 0);
		pane.add(createBloomNode(0.50), 2, 0);
		pane.add(createBloomNode(0.75), 3, 0);
		pane.add(createBloomNode(1.00), 4, 0);
	}

	private Node createBloomNode(double threshold) {
		Group g = new Group();

		Bloom bloom = new Bloom();
		bloom.setThreshold(threshold);

		Rectangle rect = new Rectangle();
		rect.setX(10);
		rect.setY(10);
		rect.setWidth(160);
		rect.setHeight(80);
		rect.setFill(Color.DARKSLATEBLUE);

		Text text = new Text();
		text.setText("Bloom!");
		text.setFill(Color.ALICEBLUE);
		text.setFont(Font.font(null, FontWeight.BOLD, 40));
		text.setX(25);
		text.setY(65);
		text.setEffect(bloom);

		g.setCache(false);

		g.getChildren().add(rect);
		g.getChildren().add(text);

		return g;
	}
}
