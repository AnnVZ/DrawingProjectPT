package Figures;

import java.awt.*;

public class Square extends Rectangle {

	public Square() {

	}

	public Square(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color, points);
	}

	public Square(Color borderColor, int xLocation, int yLocation, Color color, Point[] points) {
		super(borderColor, new Point(xLocation, yLocation), color, points);
	}
}