package Figures;

import java.awt.*;

public class Polyline extends Shape1D {

	private Segment[] segments;

	public Polyline(Color borderColor, Point location, Point point, int num) {
		super(borderColor, location, location);
		Segment[] segments = new Segment[num];
		for (int i = 0; i < num; i++) {
			segments[i] = new Segment(borderColor, new Point(point), new Point(point));
		}
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