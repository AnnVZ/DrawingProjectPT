package Figures;

import java.awt.*;

public abstract class Shape1D extends Figure {

	private Point secondPoint;
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

	protected Shape1D() {

	}

	protected Shape1D(Color borderColor, Point location, Point secondPoint) {
		super(borderColor, location);
		this.secondPoint = secondPoint;
	}

	public Point getSecondPoint() {
		return secondPoint;
	}

	public void setSecondPoint(Point point) {
		this.secondPoint = point;
	}

	protected int valueOfLineAtPoint(int x) {
		Point location = getLocation();

		if (location.x == secondPoint.x) {
			return -1;
		}

		//y = a * x + b
		int a = (location.y - secondPoint.y) / (location.x - secondPoint.x);
		int b = location.y - a * location.x;

		return a * x + b;
	}

	@Override
	public void move(int xDifference, int yDifference) {
		Point location = getLocation();
		setLocation(new Point(location.x + xDifference, location.y + yDifference));

		secondPoint.x += xDifference;
		secondPoint.y += yDifference;
	}
}