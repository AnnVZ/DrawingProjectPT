package listeners;

import Figures.Figure;
import window.DrawingPanel;

import java.awt.*;
import java.util.ArrayList;

public interface ListenerContext {
    int curX();
    void setCurX(int x);

    int curY();
    void setCurY(int y);

    Color currentColor();
    void setCurrentColor(Color color);

    Color currentBorderColor();
    void setCurrentBorderColor(Color color);

    DrawingPanel.Figures currentFigureType();
    void setCurrentFigureType(DrawingPanel.Figures type);

    boolean isDrawing();
    void setIsDrawing(boolean isD);

    boolean isMoving();
    void setIsMoving(boolean isM);

    boolean isPolyDrawing();
    void setIsPolyDrawing(boolean isPD);

    int indexToMove();
    void setIndexToMove(int ind);

    ArrayList<Figure> figures();

    int currentVertexCount();
    void setCurrentVertexCount(int count);

    int currentVertexNumber();
    void setCurrentVertexNumber(int num);
}
