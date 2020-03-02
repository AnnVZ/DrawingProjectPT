package Figures;

import java.awt.*;

public class Rectangle extends Polygon {

	public Rectangle() {

	}

	public Rectangle(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color, points);
	}

	public Rectangle(Color borderColor, int xLocation, int yLocation, Color color, Point[] points) {
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
		graphics.fillRect(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
		graphics.setColor(borderColor);
		graphics.drawRect(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
	}

	@Override
	public void drawServiceLines(Graphics graphics) {
		Point topLeftPoint = getTopLeftRectPoint();

		graphics.drawLine(topLeftPoint.x, topLeftPoint.y, topLeftPoint.x + getWidth(), topLeftPoint.y + getHeight());
		graphics.drawLine(topLeftPoint.x + getWidth(), topLeftPoint.y, topLeftPoint.x, topLeftPoint.y + getHeight());
	}

	private java.awt.Polygon createPolygon() {
		int n = 4;

		int[] xPoints = new int[n];
		int[] yPoints = new int[n];

		Point topLeftPoint = getTopLeftRectPoint();

		xPoints[0] = xPoints[3] = topLeftPoint.x;
		yPoints[0] = yPoints[1] = topLeftPoint.y;

		xPoints[2] = xPoints[1] = topLeftPoint.x + getWidth();
		yPoints[2] = yPoints[3] = topLeftPoint.y + getHeight();

		return new java.awt.Polygon(xPoints, yPoints, n);
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