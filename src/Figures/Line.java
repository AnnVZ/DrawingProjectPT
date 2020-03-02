package Figures;

import java.awt.*;

public class Line extends Ray {

    public Line() {

    }

    public Line(Color borderColor, Point location, Point secondPoint) {
        super(borderColor, location, secondPoint);
    }

    private Point[] getEndPointsToDraw(Frame frame) {
        Point[] endPoints = new Point[2];
        Point secondPoint = getSecondPoint();
        Point location = getLocation();

        if (location.equals(secondPoint)) {
            endPoints[0] = new Point(location);
            endPoints[1] = new Point(secondPoint);

            return endPoints;
        }

        int width = frame.getWidth();
        int height = frame.getHeight();

        if (location.x != secondPoint.x) {
            endPoints[0] = new Point(0, valueOfLineAtPoint(0));
            endPoints[1] = new Point(width, valueOfLineAtPoint(width));
        } else {
            endPoints[0] = new Point(location.x, 0);
            endPoints[1] = new Point(location.x, height);
        }

        return endPoints;
    }

    @Override
    public void draw(Graphics graphics, Frame frame) {
        Point[] endPoints = getEndPointsToDraw(frame);

        graphics.setColor(getBorderColor());
        graphics.drawLine(endPoints[0].x, endPoints[0].y, endPoints[1].x, endPoints[1].y);
    }

    @Override
    public boolean contains(Point point) {
        Point location = getLocation();

        return (location.x == getSecondPoint().x && location.x == point.x) || valueOfLineAtPoint(point.x) == point.y;
    }
}