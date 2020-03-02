package Figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Polygon {

	public Ellipse() {

	}

	public Ellipse(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color, points);
	}

	public Ellipse(Color borderColor, int xLocation, int yLocation, Color color, Point[] points) {
		super(borderColor, new Point(xLocation, yLocation), color, points);
	}

	public int getWidth() {
		return 2 * Math.abs(location.x - points[0].x);
	}

	public int getHeight() {
		return 2 * Math.abs(location.y - points[0].y);
	}

	@Override
	public void draw(Graphics graphics, Frame frame) {
		Point topLeftPoint = getTopLeftRectPoint();

		graphics.setColor(color);
		graphics.fillOval(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
		graphics.setColor(borderColor);
		graphics.drawOval(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
	}

	@Override
	public void drawServiceLines(Graphics graphics) {
		Point topLeftPoint = getTopLeftRectPoint();

		graphics.drawRect(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
	}

	@Override
	public boolean contains(int x, int y) {
		return createEllipse().contains(x, y);
	}

	private Ellipse2D createEllipse() {
		Point topLeftPoint = getTopLeftRectPoint();

		return new Ellipse2D.Double(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
	}

	public Point getTopLeftRectPoint() {
		if (location.x >= points[0].x && location.y >= points[0].y) {
			return points[0];
		}
		if (location.x >= points[0].x && location.y <= points[0].y) {
			return new Point(points[0].x, points[0].y - getHeight());
		}
		if (location.x <= points[0].x && location.y >= points[0].y) {
			return new Point(points[0].x - getWidth(), points[0].y);
		}
		if (location.x <= points[0].x && location.y <= points[0].y) {
			return new Point(points[0].x - getWidth(), points[0].y - getHeight());
		}
		return null;
	}
}