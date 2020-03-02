package Figures;

import java.awt.*;

public abstract class Shape2D extends Figure {

	private Color color;

	protected Shape2D() {

	}

	protected Shape2D(Color borderColor, Point location, Color color) {
		super(borderColor, location);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void drawServiceLines(Graphics graphics);
}