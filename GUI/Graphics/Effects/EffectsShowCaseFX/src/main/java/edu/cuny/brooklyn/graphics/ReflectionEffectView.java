package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ReflectionEffectView extends EffectBaseView {
	public ReflectionEffectView() {
		pane.add(createReflectionNode(0.00), 0, 0);
		pane.add(createReflectionNode(0.50), 1, 0);
		pane.add(createReflectionNode(0.75), 2, 0);
		pane.add(createReflectionNode(1.00), 3, 0);
	}

	private Node createReflectionNode(double fraction) {
		Group group = new Group();
		Reflection reflection = new Reflection();
		reflection.setFraction(fraction);

		Text text = new Text();
		text.setX(10.0);
		text.setY(50.0);
		text.setCache(true);
		text.setText("Reflection!");
		text.setFill(Color.web("0x3b596d"));
		text.setFont(Font.font(null, FontWeight.BOLD, 40));
		text.setEffect(reflection);

		group.getChildren().add(text);
		return group;
	}

}
