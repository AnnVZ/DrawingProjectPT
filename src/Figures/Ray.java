package Figures;

import java.awt.*;

public class Ray extends Segment {

	public Ray() {

	}

	public Ray(Color borderColor, Point location, Point secondPoint) {
		super(borderColor, location, secondPoint);
	}

	public Ray(Color borderColor, int xLocation, int yLocation, int x, int y) {
		super(borderColor, new Point(xLocation, yLocation), new Point(x, y));
	}

	@Override
	public void draw(Graphics graphics, Frame frame) {
		if (location.equals(secondPoint)) {
			graphics.setColor(borderColor);
			graphics.drawLine(location.x, location.y, secondPoint.x, secondPoint.y);
		} else {
			Point endPoint = getEndPointToDraw(frame);

			graphics.setColor(borderColor);
			graphics.drawLine(location.x, location.y, endPoint.x, endPoint.y);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		if (location.x == secondPoint.x)
			return location.y >= secondPoint.y ? y <= location.y : y >= location.y;
		if (valueOfLineAtPoint(x) == y)
			return location.x > secondPoint.x ? x <= location.x : x >= location.x;
		return false;
	}

	private Point getEndPointToDraw(Frame frame) {
		int width = frame.getWidth();
		int height = frame.getHeight();
		int x, y;

		if (location.x != secondPoint.x) {
			//y = a * x + b
			double a = (0.0 + location.y - secondPoint.y) / (0.0 + location.x - secondPoint.x);
			double b = location.y - a * location.x;

			x = secondPoint.x >= location.x ? width : 0;
			y = secondPoint.x >= location.x ? (int) (a * width + b) : (int) b;
		} else {
			x = location.x;
			y = secondPoint.y >= location.y ? height : 0;
		}
		return new Point(x, y);
	}
}