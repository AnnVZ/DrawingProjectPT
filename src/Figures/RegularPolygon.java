package Figures;

import java.awt.*;

public class RegularPolygon extends Polygon {

	private int number;

	public RegularPolygon(Color borderColor, Point location, Color color, Point point, int number) {
		super(borderColor, location, color, point, 1);
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Point getPointOnCircle() {
		return getPoints()[0];
	}

	public java.awt.Polygon createPolygon() {
		double angle = 2 * Math.PI / number;

		int[] xPoints = new int[number];
		int[] yPoints = new int[number];

		double[] xV = new double[number];
		double[] yV = new double[number];

		Point location = getLocation();
		Point point = getPointOnCircle();

		xV[0] = point.x - location.x;
		yV[0] = point.y - location.y;
		for (int i = 1; i < number; ++i) {
			xV[i] = (int) (xV[i - 1] * Math.cos(angle) - yV[i - 1] * Math.sin(angle));
			yV[i] = (int) (xV[i - 1] * Math.sin(angle) + yV[i - 1] * Math.cos(angle));
		}

		for (int i = 0; i < number; ++i) {
			xPoints[i] = (int) xV[i] + location.x;
			yPoints[i] = (int) yV[i] + location.y;
		}

		return new java.awt.Polygon(xPoints, yPoints, number);
	}

    @Override
    public void drawServiceLines(Graphics graphics) {
		java.awt.Polygon polygon = createPolygon();

		for (int i = 0; i < number; ++i) {
			graphics.drawLine(getLocation().x, getLocation().y, polygon.xpoints[i], polygon.ypoints[i]);
		}
    }
}