package Figures;

import java.awt.*;

public abstract class Figure {

	private Color borderColor;

	private Point location;
	/*
		location:

			segment - first selected point
			ray - first selected point (beginning of the ray)
			line - first selected point
			polyline - first selected point

			polygon - first selected point
			ellipse - central point
			circle - central point
			triangle - first selected point
			rectangle - central point
			square - central point
			rhomb - central point
			regular polygon - central point (second selected)
	*/

	protected Figure() {

	}

	protected Figure(Color borderColor, Point location) {
		this.borderColor = borderColor;
		this.location = location;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public Point getLocation() {
		return location;
	}

	public void setBorderColor(Color color) {
		this.borderColor = color;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public abstract void draw(Graphics graphics, Frame frame);

	public abstract void move(int xDifference, int yDifference);

	public abstract boolean contains(Point point);
}