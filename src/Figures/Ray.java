package Figures;

import java.awt.*;

public class Ray extends Segment {

    public Ray() {

    }

    public Ray(Color borderColor, Point location, Point secondPoint) {
        super(borderColor, location, secondPoint);
    }

    private Point getEndPointToDraw(Frame frame) {
        Point secondPoint = getSecondPoint();
        Point location = getLocation();

        if (location.equals(secondPoint)) {
            return new Point(secondPoint);
        }

        int width = frame.getWidth();
        int height = frame.getHeight();
        int x, y;

        if (location.x != secondPoint.x) {
            x = secondPoint.x >= location.x ? width : 0;
            y = secondPoint.x >= location.x ? valueOfLineAtPoint(width) : valueOfLineAtPoint(0);
        } else {
            x = location.x;
            y = secondPoint.y >= location.y ? height : 0;
        }

        return new Point(x, y);
    }

    @Override
    public void draw(Graphics graphics, Frame frame) {
        Point endPoint = getEndPointToDraw(frame);
        Point location = getLocation();

        graphics.setColor(getBorderColor());
        graphics.drawLine(location.x, location.y, endPoint.x, endPoint.y);
    }

    @Override
    public boolean contains(Point point) {
        Point secondPoint = getSecondPoint();
        Point location = getLocation();

        if (location.x == secondPoint.x)
            return location.y >= secondPoint.y ? point.y <= location.y : point.y >= secondPoint.y;
        if (valueOfLineAtPoint(point.x) == point.y)
            return location.x > secondPoint.x ? point.x <= location.x : point.x >= secondPoint.x;
        return false;
    }
}