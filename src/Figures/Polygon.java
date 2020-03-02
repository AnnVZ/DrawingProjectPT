package Figures;

import java.awt.*;

public class Polygon extends Shape2D {

	protected Point[] points;
	/*
		points:
			polygon - all polygon points
			ellipse - first point
			circle - first point
			triangle - all three points as triangle is not regular
			rectangle - first point
			square - first point
			rhomb - first point
			regular polygon - point on circle (second selected point)
	*/

    public Polygon() {

	}

	public Polygon(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color);
		this.points = points;
	}

	public Polygon(Color borderColor, int xLocation, int yLocation, Color color, Point[] points) {
		super(borderColor, new Point(xLocation, yLocation), color);
		this.points = points;
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
	public void move(int xDifference, int yDifference) {
		super.move(xDifference, yDifference);

		for (Point point : points) {
			point.x += xDifference;
			point.y += yDifference;
		}
	}

	@Override
	public void drawServiceLines(Graphics graphics) {
		//empty
	}

	@Override
	public boolean contains(int x, int y) {
		return createPolygon().contains(x, y);
	}

	private java.awt.Polygon createPolygon() {
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];

		for (int i = 0; i < points.length; ++i) {
			xPoints[i] = points[i].x;
			yPoints[i] = points[i].y;
		}

		return new java.awt.Polygon(xPoints, yPoints, points.length);
	}

	public void changePoint(int index, Point newPoint) {
		if (index < points.length) {
			points[index] = newPoint;
		}
	}

	public void changePoint(int index, int newX, int newY) {
		if (index < points.length) {
			points[index] = new Point(newX, newY);
		}
	}

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}
}