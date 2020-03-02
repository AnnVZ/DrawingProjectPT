package Figures;

import java.awt.*;

public class Segment extends Shape1D {

	public Segment() {

	}

	public Segment(Color borderColor, Point location, Point secondPoint) {
		super(borderColor, location, secondPoint);
	}

	@Override
	public void draw(Graphics graphics, Frame frame) {
		Point location = getLocation();
		Point secondPoint = getSecondPoint();

		graphics.setColor(getBorderColor());
		graphics.drawLine(location.x, location.y, secondPoint.x, secondPoint.y);
	}

    @Override
    public boolean contains(Point point) {
		Point location = getLocation();
		Point secondPoint = getSecondPoint();

		if (point.x <= Math.max(location.x, secondPoint.x) && point.x >= Math.min(location.x, secondPoint.x) &&
            point.y <= Math.max(location.y, secondPoint.y) && point.y >= Math.min(location.y, secondPoint.y)) {
		    return location.x == secondPoint.x || valueOfLineAtPoint(point.x) == point.y;
        }
        return false;
    }
}