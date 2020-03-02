import Figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private Frame frame;
    private Color color;
    private ArrayList<Figure> figures;
    private int prevX, prevY;
    private boolean isDrawing;

    {
        color = Color.GREEN;
        figures = new ArrayList<>();
        isDrawing = false;
    }

    DrawingPanel(JFrame frame) {
        this.frame = frame;
        this.setSize(frame.getWidth(), frame.getHeight());

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    void setColor(Color color) {
        this.color = color;
    }

    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            prevX = me.getX();
            prevY = me.getY();
            figures.add(new RegularPolygon(Color.BLACK, new Point(prevX, prevY), color, new Point(prevX, prevY), 5));
            isDrawing = true;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            isDrawing = false;
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {
        public void mouseDragged(MouseEvent me) {
            prevX = me.getX();
            prevY = me.getY();

            Figure f = figures.get(figures.size() - 1);
            Point point = ((RegularPolygon) f).getPoints()[0];

            f.setLocation(new Point(prevX, prevY));
        }

        public void mouseMoved(MouseEvent e) {
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();

        Graphics2D f = (Graphics2D) g;
        f.setStroke(new BasicStroke(0.6f));

        for (Figure figure: figures) {
            figure.draw(g, frame);
        }

        f.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2, new float[] {5f, 5f}, 0f));
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