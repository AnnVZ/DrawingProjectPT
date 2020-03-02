package Figures;

import java.awt.*;

public class Polyline extends Shape1D {

	private Figures.Segment[] segments;

	public Polyline() {

	}

	public Polyline(Color borderColor, Point location, Point secondPoint, Segment[] segments) {
		super(borderColor, location, secondPoint);
		this.segments = segments;
	}

	public Polyline(Color borderColor, int xLocation, int yLocation, Point secondPoint, Segment[] segments) {
		super(borderColor, new Point(xLocation, yLocation), secondPoint);
		this.segments = segments;
	}

	@Override
	public void draw(Graphics graphics, Frame frame) {
		for (Segment segment : segments) {
			segment.draw(graphics, frame);
		}
	}

	@Override
	public void move(int xDifference, int yDifference) {
		super.move(xDifference, yDifference);

		for (Segment segment : segments) {
			segment.move(xDifference, yDifference);
		}
	}

    @Override
    public boolean contains(int x, int y) {
		for (Segment segment : segments) {
			if (segment.contains(x, y)) {
				return true;
			}
		}
		return false;
    }

    public Segment[] getSegments() {
		return segments;
	}

	public void setSegments(Segment[] segments) {
		this.segments = segments;
	}
}