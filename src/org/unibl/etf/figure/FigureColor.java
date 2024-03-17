package org.unibl.etf.figure;

import java.awt.Color;
public enum FigureColor {
    RED,
    GREEN,
    BLUE,
    YELLOW,
    GRAY,
    BLACK;

    public static Color getColor(FigureColor color)
    {
        if (color == RED)
            return Color.red;
        if (color == GREEN)
            return Color.green;
        if (color == BLUE)
            return Color.blue;
        if (color == YELLOW)
            return Color.yellow;
        if (color == GRAY)
            return Color.gray;
        return Color.black;
    }

}
