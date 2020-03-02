package Figures;

import java.awt.*;

public class Segment extends Shape1D {

	public Segment() {

	}

	public Segment(Color borderColor, Point location, Point secondPoint) {
		super(borderColor, location, secondPoint);
	}

	public Segment(Color borderColor, int xLocation, int yLocation, int x, int y) {
		super(borderColor, new Point(xLocation, yLocation), new Point(x, y));
	}

	@Override
	public void draw(Graphics graphics, Frame frame) {
		graphics.setColor(borderColor);
		graphics.drawLine(location.x, location.y, secondPoint.x, secondPoint.y);
	}

	@Override
	public void move(int xDifference, int yDifference) {
		super.move(xDifference, yDifference);

		secondPoint.x += xDifference;
		secondPoint.y += yDifference;
	}

    @Override
    public boolean contains(int x, int y) {
		if (x <= Math.max(location.x, secondPoint.x) && x >= Math.min(location.x, secondPoint.x) &&
            y <= Math.max(location.y, secondPoint.y) && y >= Math.min(location.y, secondPoint.y)) {
		    return location.x == secondPoint.x || valueOfLineAtPoint(x) == y;
        }
        return false;
    }
}