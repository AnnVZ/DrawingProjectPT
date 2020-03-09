package listeners;

import Figures.*;
import Figures.Polygon;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionHandler implements MouseMotionListener {
    private ListenerContext context;

    public MouseMotionHandler(ListenerContext listenerContext) {
        context = listenerContext;
    }

    public void mouseDragged(MouseEvent me) {
        if (!context.isMoving()) {
            context.setCurX(me.getX());
            context.setCurY(me.getY());

            Figure currentFigure = context.figures().get(context.figures().size() - 1);

            if (currentFigure instanceof Segment) {
                ((Shape1D) currentFigure).setSecondPoint(new Point(context.curX(), context.curY()));
            } else if (currentFigure instanceof RegularPolygon) {
                currentFigure.setLocation(new Point(context.curX(), context.curY()));
            } else if (currentFigure instanceof Circle || currentFigure instanceof Square) {
                Point point = ((Polygon) currentFigure).getPoints()[0];
                int len = Math.min(Math.abs(context.curX() - point.x), Math.abs(context.curY() - point.y)) / 2;
                int x = context.curX() >= point.x ? point.x + len : point.x - len;
                int y = context.curY() >= point.y ? point.y + len : point.y - len;
                currentFigure.setLocation(new Point(x, y));
            } else if (currentFigure instanceof Polygon && !(currentFigure instanceof Triangle) && !currentFigure.getClass().equals(Polygon.class)) {
                Point point = ((Polygon) currentFigure).getPoints()[0];
                currentFigure.setLocation(new Point((point.x + context.curX()) / 2, (point.y + context.curY()) / 2));
            }
        } else {
            if (context.indexToMove() >= 0) {
                context.figures().get(context.indexToMove()).move(me.getX() - context.curX(), me.getY() - context.curY());

                context.setCurX(me.getX());
                context.setCurY(me.getY());
            }
        }
    }

    public void mouseMoved(MouseEvent me) {
        if (context.isPolyDrawing()) {
            context.setCurX(me.getX());
            context.setCurY(me.getY());

            Figure currentFigure = context.figures().get(context.figures().size() - 1);

            switch (context.currentFigureType()) {
                case POLYLINE:
                    ((Polyline) currentFigure).getSegments()[context.currentVertexNumber() - 1].setSecondPoint(new Point(context.curX(), context.curY()));
                    break;
                default:
                    ((Polygon) currentFigure).getPoints()[context.currentVertexNumber()].setLocation(context.curX(), context.curY());
                    break;
            }
        }
    }
}
