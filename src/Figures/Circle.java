package Figures;

import java.awt.*;

public class Circle extends Ellipse {

	public Circle() {

	}

	public Circle(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color, points);
	}

	public Circle(Color borderColor, int xLocation, int yLocation, Color color, Point[] points) {
		super(borderColor, new Point(xLocation, yLocation), color, points);
	}
}