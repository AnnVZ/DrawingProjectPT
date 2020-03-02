package Figures;

import java.awt.*;

public class Rhomb extends Polygon {

	public Rhomb() {

	}

	public Rhomb(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color, points);
	}

	public Rhomb(Color borderColor, int xLocation, int yLocation, Color color, Point[] points) {
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
		java.awt.Polygon polygon = createPolygon();

		graphics.setColor(color);
		graphics.fillPolygon(polygon);
		graphics.setColor(borderColor);
		graphics.drawPolygon(polygon);
	}

    @Override
    public void drawServiceLines(Graphics graphics) {
		java.awt.Polygon polygon = createPolygon();
		Point topLeftPoint = getTopLeftRectPoint();

		graphics.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[2], polygon.ypoints[2]);
		graphics.drawLine(polygon.xpoints[1], polygon.ypoints[1], polygon.xpoints[3], polygon.ypoints[3]);

		graphics.drawRect(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
    }

	private java.awt.Polygon createPolygon() {
	    int n = 4;

		int[] xPoints = new int[n];
		int[] yPoints = new int[n];

		Point topLeftPoint = getTopLeftRectPoint();

		xPoints[0] = xPoints[2] = location.x;
		xPoints[1] = topLeftPoint.x + getWidth();
		xPoints[3] = topLeftPoint.x;

		yPoints[0] = topLeftPoint.y;
		yPoints[1] = yPoints[3] = location.y;
		yPoints[2] = topLeftPoint.y + getHeight();

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