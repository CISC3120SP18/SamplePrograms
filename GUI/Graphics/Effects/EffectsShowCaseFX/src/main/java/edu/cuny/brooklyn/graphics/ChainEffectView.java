package edu.cuny.brooklyn.graphics;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChainEffectView extends EffectBaseView {
	public ChainEffectView() {
		pane.add(createChainEffectNode(), 0, 0);
	}

	private Node createChainEffectNode() {
		Rectangle rect = new Rectangle();
		rect.setFill(Color.RED);
		rect.setWidth(200);
		rect.setHeight(100);
		rect.setX(20.0f);
		rect.setY(20.0f);

		DropShadow ds = new DropShadow();
		ds.setOffsetY(15.0);
		ds.setOffsetX(15.0);
		ds.setColor(Color.GRAY);

		Reflection reflection = new Reflection();
		ds.setInput(reflection);

		rect.setEffect(ds);

		return rect;
	}
}
