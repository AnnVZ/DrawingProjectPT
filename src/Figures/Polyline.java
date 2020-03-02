package Figures;

import java.awt.*;

public class Polyline extends Shape1D {

	private Segment[] segments;

	public Polyline() {

	}

	public Polyline(Color borderColor, Point location, Point secondPoint, Segment[] segments) {
		super(borderColor, location, secondPoint);
		this.segments = segments;
	}

	public Segment[] getSegments() {
		return segments;
	}

	public void setSegments(Segment[] segments) {
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
    public boolean contains(Point point) {
		for (Segment segment : segments) {
			if (segment.contains(point)) {
				return true;
			}
		}
		return false;
    }
}