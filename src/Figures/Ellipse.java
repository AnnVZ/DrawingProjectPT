package Figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Polygon {

	public Ellipse(Color borderColor, Point location, Color color, Point point) {
		super(borderColor, location, color, point, 1);
	}

	public int getWidth() {
		return 2 * Math.abs(getLocation().x - getPoints()[0].x);
	}

	public int getHeight() {
		return 2 * Math.abs(getLocation().y - getPoints()[0].y);
	}

	public Point getTopLeftRectPoint() {
		Point p = getPoints()[0];
		Point l = getLocation();
		int width = getWidth();
		int height = getHeight();

		if (l.x >= p.x && l.y >= p.y) {
			return p;
		}
		if (l.x >= p.x && l.y <= p.y) {
			return new Point(p.x, p.y - height);
		}
		if (l.x <= p.x && l.y >= p.y) {
			return new Point(p.x - width, p.y);
		}
		if (l.x <= p.x && l.y <= p.y) {
			return new Point(p.x - width, p.y - height);
		}
		return null;
	}

	private Ellipse2D createEllipse() {
		Point topLeftPoint = getTopLeftRectPoint();

		return new Ellipse2D.Double(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
	}

	@Override
	public void draw(Graphics graphics, Frame frame) {
		Point topLeftPoint = getTopLeftRectPoint();
		int width = getWidth();
		int height = getHeight();

		graphics.setColor(getColor());
		graphics.fillOval(topLeftPoint.x, topLeftPoint.y, width, height);
		graphics.setColor(getBorderColor());
		graphics.drawOval(topLeftPoint.x, topLeftPoint.y, width, height);
	}

	@Override
	public void drawServiceLines(Graphics graphics) {
		Point topLeftPoint = getTopLeftRectPoint();

		graphics.drawRect(topLeftPoint.x, topLeftPoint.y, getWidth(), getHeight());
	}

	@Override
	public boolean contains(Point point) {
		return createEllipse().contains(point);
	}
}