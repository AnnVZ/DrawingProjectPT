package Figures;

import java.awt.*;

public class RegularPolygon extends Polygon {

	private int number;

	public RegularPolygon() {

	}

	public RegularPolygon(Color borderColor, Point location, Color color, Point[] points, int number) {
		super(borderColor, location, color, points);
		this.number = number;
	}

	public RegularPolygon(Color borderColor, int xLocation, int yLocation, Color color, Point[] points, int number) {
		super(borderColor, new Point(xLocation, yLocation), color, points);
		this.number = number;
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

		for (int i = 0; i < number; ++i) {
			graphics.drawLine(location.x, location.y, polygon.xpoints[i], polygon.ypoints[i]);
		}
    }

	private java.awt.Polygon createPolygon() {
		double angle = 360.0 / number;

		int[] xPoints = new int[number];
		int[] yPoints = new int[number];

		int[] xV= new int[number];
		int[] yV= new int[number];

		xV[0] = points[0].x - location.x;
		yV[0] = points[0].y - location.y;
		for (int i = 1; i < number; ++i) {
			xV[i] = (int)(xV[i - 1] * Math.cos(angle) - yV[i - 1] * Math.sin(angle));
			yV[i] = (int)(xV[i - 1] * Math.sin(angle) + yV[i - 1] * Math.cos(angle));
		}

		for (int i = 0; i < number; ++i) {
			xPoints[0] = xV[i] + location.x;
			yPoints[0] = yV[i] + location.y;
		}

//		xPoints[0] = points[0].x;
//		yPoints[0] = points[0].y;
//		for (int i = 1; i < number; ++i) {
//			xPoints[i] = (int)(xPoints[i - 1] * Math.cos(angle) - yPoints[i - 1] * Math.sin(angle)) + location.x;
//			yPoints[i] = (int)(xPoints[i - 1] * Math.sin(angle) + yPoints[i - 1] * Math.cos(angle)) + location.y;
//		}

		return new java.awt.Polygon(xPoints, yPoints, number);
	}

	public Point getPointOnCircle() {
		return points[0];
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}