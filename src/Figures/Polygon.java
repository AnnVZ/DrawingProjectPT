package Figures;

import java.awt.*;

public class Polygon extends Shape2D {

	private Point[] points;
	/*
		points:
			polygon - all polygon points
			ellipse - first point
			circle - first point
			triangle - all three points as triangle is not regular
			rectangle - first point
			square - first point
			rhomb - first point
			regular polygon - point on circle (first selected point)
	*/

    public Polygon() {

	}

	public Polygon(Color borderColor, Point location, Color color, Point[] points) {
		super(borderColor, location, color);
		this.points = points;
	}

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

	@Override
	public void draw(Graphics graphics, Frame frame) {
		java.awt.Polygon polygon = createPolygon();

		graphics.setColor(getColor());
		graphics.fillPolygon(polygon);
		graphics.setColor(getBorderColor());
		graphics.drawPolygon(polygon);
	}

    public java.awt.Polygon createPolygon() {
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        for (int i = 0; i < points.length; ++i) {
            xPoints[i] = points[i].x;
            yPoints[i] = points[i].y;
        }

        return new java.awt.Polygon(xPoints, yPoints, points.length);
    }

    @Override
    public void drawServiceLines(Graphics graphics) {
        //empty
    }

	@Override
	public void move(int xDifference, int yDifference) {
    	Point location = getLocation();
		setLocation(new Point(location.x + xDifference, location.y + yDifference));

		for (Point point : points) {
			point.x += xDifference;
			point.y += yDifference;
		}
	}

	@Override
	public boolean contains(Point point) {
		return createPolygon().contains(point);
	}
}