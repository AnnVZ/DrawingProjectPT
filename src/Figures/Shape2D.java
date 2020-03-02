package Figures;

import java.awt.*;

public abstract class Shape2D extends Figure {

	protected Color color;

	public Shape2D() {

	}

	public Shape2D(Color borderColor, Point location, Color color) {
		super(borderColor, location);
		this.color = color;
	}

	public Shape2D(Color borderColor, int xLocation, int yLocation, Color color) {
		super(borderColor, new Point(xLocation, yLocation));
		this.color = color;
	}

	@Override
	public void move(int xDifference, int yDifference) {
		location.x += xDifference;
		location.y += yDifference;
	}

	public abstract void drawServiceLines(Graphics graphics);

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}