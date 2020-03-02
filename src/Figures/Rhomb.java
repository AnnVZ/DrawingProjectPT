package Figures;

import java.awt.*;

public class Rhomb extends Polygon {

	public Rhomb() {

	}

	public Rhomb(Color borderColor, Point location, Color color, Point point) {
		super(borderColor, location, color, null);
		Point[] points = new Point[1];
		points[0] = point;
		setPoints(points);
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

		Point location = getLocation();

		Point topLeftPoint = getTopLeftRectPoint();

		xPoints[0] = xPoints[2] = location.x;
		xPoints[1] = topLeftPoint.x + getWidth();
		xPoints[3] = topLeftPoint.x;

		yPoints[0] = topLeftPoint.y;
		yPoints[1] = yPoints[3] = location.y;
		yPoints[2] = topLeftPoint.y + getHeight();

		return new java.awt.Polygon(xPoints, yPoints, xPoints.length);
	}

    @Override
    public void drawServiceLines(Graphics graphics) {
		java.awt.Polygon polygon = createPolygon();
		Point topLeftPoint = getTopLeftRectPoint();

		graphics.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[2], polygon.ypoints[2]);
		graphics.drawLine(polygon.xpoints[1], polygon.ypoints[1], polygon.xpoints[3], polygon.ypoints[3]);

		graphics.drawRect(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
    }
}