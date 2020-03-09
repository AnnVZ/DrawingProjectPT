import Figures.*;
import Figures.Polygon;
import Figures.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private Frame frame;
    private Color currentColor;
    private Color currentBorderColor;
    private Figures currentFigureType;
    private int currentCount;
    private ArrayList<Figure> figures;
    private int curX, curY;
    private boolean isDrawing;
    private boolean isMoving;
    private boolean isPolyDrawing;
    private int indexToMove;
    private int vertexNumber;

    public enum Figures {
        LINE, RAY, SEGMENT, POLYLINE, POLYGON, ELLIPSE, CIRCLE, RECTANGLE, REGULARPOLYGON, RHOMB, SQUARE, TRIANGLE
    }

    {
        currentColor = Color.GREEN;
        currentBorderColor = Color.BLACK;
        currentFigureType = Figures.SEGMENT;
        figures = new ArrayList<>();
        indexToMove = -1;
    }

    DrawingPanel(JFrame frame) {
        this.frame = frame;
        this.setSize(frame.getWidth(), frame.getHeight());

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
//        addKeyListener(new KeyListener());
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void setCurrentBorderColor(Color currentBorderColor) {
        this.currentBorderColor = currentBorderColor;
    }

    public void setCurrentFigureType(Figures currentFigureType) {
        this.currentFigureType = currentFigureType;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            curX = me.getX();
            curY = me.getY();

            if (SwingUtilities.isLeftMouseButton(me)) {
                isDrawing = true;

                switch (currentFigureType) {
//                    Segment.class;
                    case SEGMENT:
                        figures.add(new Segment(currentBorderColor, new Point(curX, curY), new Point(curX, curY)));
                        break;
                    case RAY:
                        figures.add(new Ray(currentBorderColor, new Point(curX, curY), new Point(curX, curY)));
                        break;
                    case LINE:
                        figures.add(new Line(currentBorderColor, new Point(curX, curY), new Point(curX, curY)));
                        break;
                    case POLYLINE:
                        if (isPolyDrawing) {
                            if (vertexNumber < currentCount) {
                                Figure currentFigure = figures.get(figures.size() - 1);
                                if (vertexNumber < currentCount - 1) {
                                    ((Polyline) currentFigure).getSegments()[vertexNumber].setLocation(new Point(curX, curY));
                                    ((Polyline) currentFigure).getSegments()[vertexNumber].setSecondPoint(new Point(curX, curY));
                                }
                                ((Polyline) currentFigure).getSegments()[vertexNumber - 1].setSecondPoint(new Point(curX, curY));
                                vertexNumber++;
                            }
                            if (vertexNumber == currentCount)
                                isPolyDrawing = false;
                        } else {
                            vertexNumber = 1;
                            isPolyDrawing = true;
                            Segment[] segments = new Segment[currentCount - 1];
                            for (int i = 0; i < currentCount - 1; i++)
                                segments[i] = new Segment(currentBorderColor, new Point(curX, curY), new Point(curX, curY));
                            figures.add(new Polyline(currentBorderColor, new Point(curX, curY), segments));
                        }
                        break;
                    case CIRCLE:
                        figures.add(new Circle(currentBorderColor, new Point(curX, curY), currentColor, new Point(curX, curY)));
                        break;
                    case ELLIPSE:
                        figures.add(new Ellipse(currentBorderColor, new Point(curX, curY), currentColor, new Point(curX, curY)));
                        break;
                    case RECTANGLE:
                        figures.add(new Rectangle(currentBorderColor, new Point(curX, curY), currentColor, new Point(curX, curY)));
                        break;
                    case TRIANGLE:
                        if (isPolyDrawing) {
                            if (vertexNumber < currentCount) {
                                Figure currentFigure = figures.get(figures.size() - 1);
                                ((Polygon) currentFigure).getPoints()[vertexNumber].setLocation(curX, curY);
                                vertexNumber++;
                            }
                            if (vertexNumber == currentCount)
                                isPolyDrawing = false;
                        } else {
                            vertexNumber = 1;
                            currentCount = 3;
                            isPolyDrawing = true;
                            Point[] trianglePoints = new Point[currentCount];
                            for (int i = 0; i < currentCount; i++)
                                trianglePoints[i] = new Point(curX, curY);
                            figures.add(new Triangle(currentBorderColor, new Point(curX, curY), currentColor, trianglePoints));
                        }
                        break;
                    case POLYGON:
                        if (isPolyDrawing) {
                            if (vertexNumber < currentCount) {
                                Figure currentFigure = figures.get(figures.size() - 1);
                                ((Polygon) currentFigure).getPoints()[vertexNumber].setLocation(curX, curY);
                                vertexNumber++;
                            }
                            if (vertexNumber == currentCount)
                                isPolyDrawing = false;
                        } else {
                            vertexNumber = 1;
                            isPolyDrawing = true;
                            Point[] polygonPoints = new Point[currentCount];
                            for (int i = 0; i < currentCount; i++)
                                polygonPoints[i] = new Point(curX, curY);
                            figures.add(new Polygon(currentBorderColor, new Point(curX, curY), currentColor, polygonPoints));
                        }
                        break;
                    case REGULARPOLYGON:
                        figures.add(new RegularPolygon(currentBorderColor, new Point(curX, curY), currentColor, new Point(curX, curY), currentCount));
                        break;
                    case RHOMB:
                        figures.add(new Rhomb(currentBorderColor, new Point(curX, curY), currentColor, new Point(curX, curY)));
                        break;
                    case SQUARE:
                        figures.add(new Square(currentBorderColor, new Point(curX, curY), currentColor, new Point(curX, curY)));
                        break;
                    default:
                        figures.add(new Segment(currentBorderColor, new Point(curX, curY), new Point(curX, curY)));
                        break;
                }
            }

            if (SwingUtilities.isRightMouseButton(me)) {
                isMoving = true;

                for (int i = figures.size() - 1; i >= 0; i--) {
                    if (figures.get(i).contains(new Point(curX, curY))) {
                        indexToMove = i;
                        break;
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            isDrawing = false;
            isMoving = false;
            indexToMove = -1;
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {
        public void mouseDragged(MouseEvent me) {
            if (!isMoving) {
                curX = me.getX();
                curY = me.getY();

                Figure currentFigure = figures.get(figures.size() - 1);
                Point point;

                switch (currentFigureType) {
                    case SEGMENT:
                        ((Segment) currentFigure).setSecondPoint(new Point(curX, curY));
                        break;
                    case RAY:
                        ((Ray) currentFigure).setSecondPoint(new Point(curX, curY));
                        break;
                    case LINE:
                        ((Line) currentFigure).setSecondPoint(new Point(curX, curY));
                        break;
                    case POLYLINE:
                        ///////////////
                        break;
                    case CIRCLE:
                        point = ((Circle) currentFigure).getPoints()[0];
                        int len = Math.min(Math.abs(curX - point.x), Math.abs(curY - point.y)) / 2;
                        int x = curX >= point.x ? point.x + len : point.x - len;
                        int y = curY >= point.y ? point.y + len : point.y - len;
                        currentFigure.setLocation(new Point(x, y));
                        break;
                    case ELLIPSE:
                        point = ((Ellipse) currentFigure).getPoints()[0];
                        currentFigure.setLocation(new Point((point.x + curX) / 2, (point.y + curY) / 2));
                        break;
                    case RECTANGLE:
                        point = ((Rectangle) currentFigure).getPoints()[0];
                        currentFigure.setLocation(new Point((point.x + curX) / 2, (point.y + curY) / 2));
                        break;
                    case TRIANGLE:
                        //////////////
                        break;
                    case POLYGON:
                        /////////////
                        break;
                    case REGULARPOLYGON:
                        currentFigure.setLocation(new Point(curX, curY));
                        break;
                    case RHOMB:
                        point = ((Rhomb) currentFigure).getPoints()[0];
                        currentFigure.setLocation(new Point((point.x + curX) / 2, (point.y + curY) / 2));
                        break;
                    case SQUARE:
                        point = ((Square) currentFigure).getPoints()[0];
                        len = Math.min(Math.abs(curX - point.x), Math.abs(curY - point.y)) / 2;
                        x = curX >= point.x ? point.x + len : point.x - len;
                        y = curY >= point.y ? point.y + len : point.y - len;
                        currentFigure.setLocation(new Point(x, y));
                        break;
                    default:
                        currentFigure.setLocation(new Point(curX, curY));
                        break;
                }
            } else {
                if (indexToMove >= 0) {
                    figures.get(indexToMove).move(me.getX() - curX, me.getY() - curY);

                    curX = me.getX();
                    curY = me.getY();
                }
            }
        }

        public void mouseMoved(MouseEvent e) {
            if (isPolyDrawing && vertexNumber > 0) {
                curX = e.getX();
                curY = e.getY();

                Figure currentFigure = figures.get(figures.size() - 1);

                switch (currentFigureType) {
                    case POLYLINE:
                        ((Polyline) currentFigure).getSegments()[vertexNumber - 1].setSecondPoint(new Point(curX, curY));
                        break;
                    case POLYGON:
                        ((Polygon) currentFigure).getPoints()[vertexNumber].setLocation(curX, curY);
                        break;
                    case TRIANGLE:
                        ((Triangle) currentFigure).getPoints()[vertexNumber].setLocation(curX, curY);
                        break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();

        Graphics2D f = (Graphics2D) g;
        f.setStroke(new BasicStroke(0.6f));

        for (Figure figure : figures) {
            figure.draw(g, frame);
        }

        f.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2, new float[]{5f, 5f}, 0f));
        f.setColor(Color.GRAY);
        f.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (!figures.isEmpty() && isDrawing && figures.get(figures.size() - 1) instanceof Shape2D) {
            ((Shape2D) figures.get(figures.size() - 1)).drawServiceLines(g);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(frame.getWidth(), frame.getHeight());
    }
}