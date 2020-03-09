package Figures;

import java.awt.*;

public class Rectangle extends Polygon {

	public Rectangle(Color borderColor, Point location, Color color, Point point) {
		super(borderColor, location, color, point, 1);
	}

	public int getWidth() {
		return 2 * Math.abs(getLocation().x - getPoints()[0].x);
	}

	public int getHeight() {
		return 2 * Math.abs(getLocation().y - getPoints()[0].y);
	}

	public Point getTopLeftRectPoint() {
		Point p = getPoints()[0];
		Point l = getLocation();
		int width = getWidth();
		int height = getHeight();

		if (l.x >= p.x && l.y >= p.y) {
			return p;
		}
		if (l.x >= p.x && l.y <= p.y) {
			return new Point(p.x, p.y - height);
		}
		if (l.x <= p.x && l.y >= p.y) {
			return new Point(p.x - width, p.y);
		}
		if (l.x <= p.x && l.y <= p.y) {
			return new Point(p.x - width, p.y - height);
		}
		return null;
	}

	public java.awt.Polygon createPolygon() {
		int[] xPoints = new int[4];
		int[] yPoints = new int[4];

		Point topLeftPoint = getTopLeftRectPoint();

		xPoints[0] = xPoints[3] = topLeftPoint.x;
		yPoints[0] = yPoints[1] = topLeftPoint.y;

		xPoints[2] = xPoints[1] = topLeftPoint.x + getWidth();
		yPoints[2] = yPoints[3] = topLeftPoint.y + getHeight();

		return new java.awt.Polygon(xPoints, yPoints, xPoints.length);
	}

	@Override
	public void drawServiceLines(Graphics graphics) {
		Point topLeftPoint = getTopLeftRectPoint();
		int width = getWidth();
		int height = getHeight();

		graphics.drawLine(topLeftPoint.x, topLeftPoint.y, topLeftPoint.x + width, topLeftPoint.y + height);
		graphics.drawLine(topLeftPoint.x + width, topLeftPoint.y, topLeftPoint.x, topLeftPoint.y + height);
	}
}