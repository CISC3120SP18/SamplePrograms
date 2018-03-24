package edu.cuny.brooklyn.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ShadowEffectView  extends EffectBaseView  {

	public ShadowEffectView() {
		pane.add(createShadowNode(0.0, BlurType.GAUSSIAN), 0, 0);
		pane.add(createShadowNode(5.0, BlurType.GAUSSIAN), 1, 0);
		pane.add(createShadowNode(10.0, BlurType.GAUSSIAN), 2, 0);
		
		pane.add(createShadowNode(5.0, BlurType.ONE_PASS_BOX), 1, 1);
		pane.add(createShadowNode(10.0, BlurType.ONE_PASS_BOX), 2, 1);
		
		pane.add(createShadowNode(5.0, BlurType.TWO_PASS_BOX), 1, 2);
		pane.add(createShadowNode(10.0, BlurType.TWO_PASS_BOX), 2, 2);
		
		pane.add(createShadowNode(5.0, BlurType.THREE_PASS_BOX), 1, 3);
		pane.add(createShadowNode(10.0, BlurType.THREE_PASS_BOX), 2, 3);
	}

	Node createShadowNode(double radius, BlurType type) {
		Group g = new Group();

		Shadow shadow1 = new Shadow();
		shadow1.setRadius(radius);
		shadow1.setBlurType(type);
		shadow1.setColor(Color.color(0.4, 0.5, 0.5));

		Text text = new Text();
		text.setEffect(shadow1);
		text.setCache(true);
		text.setX(10.0);
		text.setY(70.0);
		text.setFill(Color.web("0x3b596d"));
		text.setText("Shadow Effect");
		text.setFont(Font.font(null, FontWeight.BOLD, 40));

		Shadow shadow2 = new Shadow();
		shadow2.setRadius(radius);

		Circle circle = new Circle();
		circle.setEffect(shadow2);
		shadow2.setBlurType(type);
		circle.setCenterX(50.0);
		circle.setCenterY(125.0);
		circle.setRadius(30);
		circle.setFill(Color.STEELBLUE);
		circle.setCache(true);

		g.getChildren().add(text);
		g.getChildren().add(circle);
		return g;
	}
}
