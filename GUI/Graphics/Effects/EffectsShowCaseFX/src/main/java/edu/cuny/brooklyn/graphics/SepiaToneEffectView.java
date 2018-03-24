package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SepiaToneEffectView extends EffectBaseView {
	public SepiaToneEffectView() {
		pane.add(createSepiaToneEffectNode(0.0), 0, 0);
		pane.add(createSepiaToneEffectNode(0.3), 1, 0);
		pane.add(createSepiaToneEffectNode(0.5), 2, 0);
		pane.add(createSepiaToneEffectNode(1.0), 3, 0);
	}

	private Node createSepiaToneEffectNode(double level) {
		Group group = new Group();

		SepiaTone sepiaTone = new SepiaTone();
		sepiaTone.setLevel(level);

		Image image = new Image("boat.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(200);
		imageView.setPreserveRatio(true);
		imageView.setEffect(sepiaTone);
		group.getChildren().add(imageView);

		return group;
	}
}
