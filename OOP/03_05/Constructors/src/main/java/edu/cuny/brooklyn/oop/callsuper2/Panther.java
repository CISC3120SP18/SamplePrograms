package edu.cuny.brooklyn.oop.callsuper2;

/**
 * if you see warning messages,
 * Multiple markers at this line
	- Access restriction: The type 'Color' is not API (restriction on required library '...\jfxrt.jar')
	- Occurrence of 'Color'
 * Install e(fx)clipse Eclipse plugin in Eclipse IDE
 *    1. launch Eclipse
 *    2. click on the "Help" menu
 *    3. click "Install New Software"
 *    4. select work with "All avaialble sites"
 *    5. type "e(fx)clipse" to filter plugins/packages
 *    6. check "e(fx)clipse" and install ...
 */
import javafx.scene.paint.Color;

public class Panther extends Feline {
	private Color color;

	public Panther(String name) {
		super(name);
		color = Color.BLACK;
	}

	public Panther(String name, Color color) {
		super(name);
		this.color = color;
	}

	@Override
	public void makeNoise() {
		System.out.println("Roar...");
	}

	public Color getColor() {
		return color;
	}
}
