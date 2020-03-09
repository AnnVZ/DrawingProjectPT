package Figures;

import java.awt.*;

@FunctionalInterface
public interface Shape2DConstructor<T> {
    T apply(Color borderColor, Point location, Color color, Point point);
}