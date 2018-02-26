package edu.cuny.brooklyn.oop;

import java.util.ArrayList;
import java.util.Random;

import edu.cuny.brooklyn.oop.shape.Circle;
import edu.cuny.brooklyn.oop.shape.Rectangle;
import edu.cuny.brooklyn.oop.shape.Shape;

public class ShapeObjectApp 
{
    public static void main(String[] args) {
        ArrayList<Shape> shapeList = makeRandomShapes(10);
        computeAreas(shapeList);
    }

    public static ArrayList<Shape> makeRandomShapes(int numOfShapes) {
        ArrayList<Shape> shapeList = new ArrayList<Shape>();

        Random rng = new Random();
        // shapes of random types
        for (int i = 0; i < numOfShapes; i++) {
            int rn = rng.nextInt(2);
            if (rn == 0) {
                double radius = rng.nextDouble() * 100;
                String name = "Circle_" + i;
                Circle circle = new Circle(name, radius);
                shapeList.add(circle);
            } else {
                double width = rng.nextDouble() * 100;
                double length = rng.nextDouble() * 100;
                String name = "Rectangle_" + i;
                Rectangle rectangle = new Rectangle(name, width, length);
                shapeList.add(rectangle);
            }
        }

        return shapeList;
    }
    
    public static void computeAreas(ArrayList<Shape> shapeList) {
        // TODO: compute and display the areas of the shapes in the shapeList
        /* Example output:
        Shape Circle_0: 11390.464859501682
        Shape Circle_1: 526.8470433043791
        Shape Circle_2: 18145.971578410303
        Shape Circle_3: 47.03261775558176
        Shape Circle_4: 783.0829150987071
        Shape Rectangle_5: 2883.463954716244
        Shape Circle_6: 29879.569294310906
        Shape Circle_7: 9213.521171994787
        Shape Rectangle_8: 3296.9637709123817
        Shape Circle_9: 10154.0796895291 */
    }
}
