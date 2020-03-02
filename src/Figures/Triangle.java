package Figures;

import java.awt.*;

public class Triangle extends Polygon {

	public Triangle() {

	}

	public Triangle(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color, points);
	}

	public Triangle(Color borderColor, int xLocation, int yLocation, Color color, Point[] points) {
		super(borderColor, new Point(xLocation, yLocation), color, points);
	}
}