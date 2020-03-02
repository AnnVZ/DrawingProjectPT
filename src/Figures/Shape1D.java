package Figures;

import java.awt.*;

public abstract class Shape1D extends Figure {

	protected Point secondPoint;
	/*
		secondPoint:

			segment - second selected point
			ray - second selected point (indicates direction of the ray)
			line - second selected point
			polyline - empty

			polygon - first selected point
			ellipse - central point
			circle - central point
			triangle - first selected point
			rectangle - central point
			square - central point
			rhomb - central point
			regular polygon - central point
	*/

	public Shape1D() {

	}

	public Shape1D(Color borderColor, Point location, Point secondPoint) {
		super(borderColor, location);
		this.secondPoint = secondPoint;
	}

	public Shape1D(Color borderColor, int xLocation, int yLocation, int x, int y) {
		super(borderColor, new Point(xLocation, yLocation));
		this.secondPoint = new Point(x, y);
	}

	@Override
	public void move(int xDifference, int yDifference) {
		location.x += xDifference;
		location.y += yDifference;
	}

	protected int valueOfLineAtPoint(int x) {
		if (location.x == secondPoint.x) {
			return -1;
		}
		//y = a * x + b
		int a = (location.y - secondPoint.y) / (location.x - secondPoint.x);
		int b = location.y - a * location.x;

		return a * x + b;
	}

	public Point getSecondPoint() {
		return secondPoint;
	}

	public void setSecondPoint(Point point) {
		this.secondPoint = point;
	}

	public void setSecondPoint(int x, int y) {
		this.secondPoint = new Point(x, y);
	}
}