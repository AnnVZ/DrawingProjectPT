package listeners;

import Figures.*;
import Figures.Polygon;
import Figures.Rectangle;
import window.DrawingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MouseHandler extends MouseAdapter {

    private ListenerContext context;

    private static Map<DrawingPanel.Figures, Shape1DConstructor<Figure>> shape1DMap = new HashMap<>();
    private static Map<DrawingPanel.Figures, Shape2DConstructor<Figure>> shape2DMap = new HashMap<>();

    static {
        shape1DMap.put(DrawingPanel.Figures.SEGMENT, Segment::new);
        shape1DMap.put(DrawingPanel.Figures.RAY, Ray::new);
        shape1DMap.put(DrawingPanel.Figures.LINE, Line::new);

        shape2DMap.put(DrawingPanel.Figures.CIRCLE, Circle::new);
        shape2DMap.put(DrawingPanel.Figures.ELLIPSE, Ellipse::new);
        shape2DMap.put(DrawingPanel.Figures.RECTANGLE, Rectangle::new);
        shape2DMap.put(DrawingPanel.Figures.RHOMB, Rhomb::new);
        shape2DMap.put(DrawingPanel.Figures.SQUARE, Square::new);
    }

    public MouseHandler(ListenerContext listenerContext) {
        context = listenerContext;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        context.setCurX(me.getX());
        context.setCurY(me.getY());

        if (SwingUtilities.isLeftMouseButton(me)) {
            if (!context.isPolyDrawing()) {
                context.setIsDrawing(true);

                Figure figure;

                Shape1DConstructor<Figure> shape1DConstructor = shape1DMap.get(context.currentFigureType());
                Shape2DConstructor<Figure> shape2DConstructor = shape2DMap.get(context.currentFigureType());

                if (shape1DConstructor != null) {
                    figure = shape1DConstructor.apply(context.currentBorderColor(), new Point(context.curX(), context.curY()), new Point(context.curX(), context.curY()));
                } else if (shape2DConstructor != null) {
                    figure = shape2DConstructor.apply(context.currentBorderColor(), new Point(context.curX(), context.curY()), context.currentColor(), new Point(context.curX(), context.curY()));
                } else if (context.currentFigureType().equals(DrawingPanel.Figures.REGULARPOLYGON)) {
                    figure = new RegularPolygon(context.currentBorderColor(), new Point(context.curX(), context.curY()), context.currentColor(), new Point(context.curX(), context.curY()), context.currentVertexCount());
                } else {
                    context.setCurrentVertexNumber(1);
                    context.setIsPolyDrawing(true);

                    switch (context.currentFigureType()) {
                        case POLYLINE:
                            figure = new Polyline(context.currentBorderColor(), new Point(context.curX(), context.curY()), new Point(context.curX(), context.curY()), context.currentVertexCount() - 1);
                            break;
                        case TRIANGLE:
                            context.setCurrentVertexCount(3);
                        default:
                            figure = new Polygon(context.currentBorderColor(), new Point(context.curX(), context.curY()), context.currentColor(), new Point(context.curX(), context.curY()), context.currentVertexCount());
                            break;
                    }
                }

                context.figures().add(figure);

            } else {
                if (context.currentVertexNumber() < context.currentVertexCount()) {
                    Figure currentFigure = context.figures().get(context.figures().size() - 1);

                    switch (context.currentFigureType()) {
                        case POLYLINE:
                            if (context.currentVertexNumber() < context.currentVertexCount() - 1) {
                                ((Polyline) currentFigure).getSegments()[context.currentVertexNumber()].setLocation(new Point(context.curX(), context.curY()));
                                ((Polyline) currentFigure).getSegments()[context.currentVertexNumber()].setSecondPoint(new Point(context.curX(), context.curY()));
                            }
                            ((Polyline) currentFigure).getSegments()[context.currentVertexNumber() - 1].setSecondPoint(new Point(context.curX(), context.curY()));
                            break;
                        default: //triangle or polygon
                            ((Polygon) currentFigure).getPoints()[context.currentVertexNumber()].setLocation(context.curX(), context.curY());
                    }

                    context.setCurrentVertexNumber(context.currentVertexNumber() + 1);
                }
                if (context.currentVertexNumber() == context.currentVertexCount())
                    context.setIsPolyDrawing(false);
            }
        }

        if (SwingUtilities.isRightMouseButton(me)) {
            context.setIsMoving(true);

            for (int i = context.figures().size() - 1; i >= 0; i--) {
                if (context.figures().get(i).contains(new Point(context.curX(), context.curY()))) {
                    context.setIndexToMove(i);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        context.setIsDrawing(false);
        context.setIsMoving(false);
        context.setIndexToMove(-1);
    }
}

/*try {
            Segment o = (Segment) Segment.class.getConstructors()[0].newInstance(context.currentBorderColor(), new Point(context.curX(), context.curY()), new Point(context.curX(), context.curY()));
            Class<? extends Segment> aClass = o.getClass();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/