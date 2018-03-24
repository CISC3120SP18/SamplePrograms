package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InnerShadowEffectView  extends EffectBaseView  {

	public InnerShadowEffectView() {
		pane.add(createInnerShadowNode(2.0, 2.0), 0, 0);
		pane.add(createInnerShadowNode(5.0, 5.0), 1, 0);
		pane.add(createInnerShadowNode(8.0, 8.0), 2, 0);
	}

	Node createInnerShadowNode(double xOffset, double yOffset) {
		Group g = new Group();
		InnerShadow is = new InnerShadow();
		is.setOffsetX(xOffset);
		is.setOffsetY(yOffset);

		Text t = new Text();
		t.setEffect(is);
		t.setX(20);
		t.setY(100);
		t.setText("Inner Shadow");
		t.setFill(Color.RED);
		t.setFont(Font.font("null", FontWeight.BOLD, 60));

		t.setTranslateX(0);
		t.setTranslateY(0);

		g.getChildren().add(t);

		return t;
	}
}
