package window;

import Figures.*;
import Figures.Polygon;
import Figures.Rectangle;
import listeners.ListenerContext;
import listeners.MouseHandler;
import listeners.MouseMotionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private Frame frame;
    private int curX, curY;
    private Color currentColor;
    private Color currentBorderColor;
    private Figures currentFigureType;
    private int currentVertexCount;
    private int currentVertexNumber;
    private ArrayList<Figure> figures;
    private boolean isDrawing;
    private boolean isMoving;
    private boolean isPolyDrawing;
    private int indexToMove;

    public enum Figures {
        SEGMENT, RAY, LINE, POLYLINE, CIRCLE, ELLIPSE, RECTANGLE, TRIANGLE, POLYGON, REGULARPOLYGON, RHOMB, SQUARE
    }

    {
        currentColor = Color.GREEN;
        currentBorderColor = Color.BLACK;
        currentFigureType = Figures.SEGMENT;
        figures = new ArrayList<>();
        indexToMove = -1;
    }

    public DrawingPanel(JFrame frame) {
        this.frame = frame;
        this.setSize(frame.getWidth(), frame.getHeight());

        ListenerContext listenerContext = new ListenerContext() {
            @Override
            public int curX() {
                return curX;
            }

            @Override
            public void setCurX(int x) {
                curX = x;
            }

            @Override
            public int curY() {
                return curY;
            }

            @Override
            public void setCurY(int y) {
                curY = y;
            }

            @Override
            public Color currentColor() {
                return currentColor;
            }

            @Override
            public void setCurrentColor(Color color) {
                currentColor = color;
            }

            @Override
            public Color currentBorderColor() {
                return currentBorderColor;
            }

            @Override
            public void setCurrentBorderColor(Color color) {
                currentBorderColor = color;
            }

            @Override
            public Figures currentFigureType() {
                return currentFigureType;
            }

            @Override
            public void setCurrentFigureType(Figures type) {
                currentFigureType = type;
            }

            @Override
            public boolean isDrawing() {
                return isDrawing;
            }

            @Override
            public void setIsDrawing(boolean isD) {
                isDrawing = isD;
            }

            @Override
            public boolean isMoving() {
                return isMoving;
            }

            @Override
            public void setIsMoving(boolean isM) {
                isMoving = isM;
            }

            @Override
            public boolean isPolyDrawing() {
                return isPolyDrawing;
            }

            @Override
            public void setIsPolyDrawing(boolean isPD) {
                isPolyDrawing = isPD;
            }

            @Override
            public int indexToMove() {
                return indexToMove;
            }

            @Override
            public void setIndexToMove(int ind) {
                indexToMove = ind;
            }

            @Override
            public ArrayList<Figure> figures() {
                return figures;
            }

            @Override
            public int currentVertexCount() {
                return currentVertexCount;
            }

            @Override
            public void setCurrentVertexCount(int count) {
                currentVertexCount = count;
            }

            @Override
            public int currentVertexNumber() {
                return currentVertexNumber;
            }

            @Override
            public void setCurrentVertexNumber(int num) {
                currentVertexNumber = num;
            }
        };

        MouseHandler mh = new MouseHandler(listenerContext);
        addMouseListener(mh);

        MouseMotionHandler mmh = new MouseMotionHandler(listenerContext);
        addMouseMotionListener(mmh);
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

    public void setCurrentVertexCount(int currentVertexCount) {
        this.currentVertexCount = currentVertexCount;
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