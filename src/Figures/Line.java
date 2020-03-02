package Figures;

import java.awt.*;

public class Line extends Ray {

	public Line() {

	}

	public Line(Color borderColor, Point location, Point secondPoint) {
		super(borderColor, location, secondPoint);
	}

	public Line(Color borderColor, int xLocation, int yLocation, int x, int y) {
		super(borderColor, new Point(xLocation, yLocation), new Point(x, y));
	}

	@Override
	public void draw(Graphics graphics, Frame frame) {
		if (location.equals(secondPoint)) {
			graphics.setColor(borderColor);
			graphics.drawLine(location.x, location.y, secondPoint.x, secondPoint.y);
		} else {
			Point[] endPoints = getEndPointsToDraw(frame);

			graphics.setColor(borderColor);
			graphics.drawLine(endPoints[0].x, endPoints[0].y, endPoints[1].x, endPoints[1].y);
		}
	}

	private Point[] getEndPointsToDraw(Frame frame) {
		int width = frame.getWidth();
		int height = frame.getHeight();

		Point[] endPoints = new Point[2];

		if (location.x != secondPoint.x) {
			//y = a * x + b
			double a = (0.0 + location.y - secondPoint.y) / (0.0 + location.x - secondPoint.x);
			double b = location.y - a * location.x;

			endPoints[0] = new Point(0, (int) b);
			endPoints[1] = new Point(width, (int) (a * width + b));
		} else {
			endPoints[0] = new Point(location.x, 0);
			endPoints[1] = new Point(location.x, height);
		}

		return endPoints;
	}

	@Override
	public boolean contains(int x, int y) {
		return (location.x == secondPoint.x && location.x == x) || valueOfLineAtPoint(x) == y;
	}
}