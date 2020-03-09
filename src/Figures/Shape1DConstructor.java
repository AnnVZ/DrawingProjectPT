package Figures;

import java.awt.*;

@FunctionalInterface
public interface Shape1DConstructor<T> {
    T apply(Color borderColor, Point location, Point secondPoint);
}