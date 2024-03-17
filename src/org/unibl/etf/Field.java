package org.unibl.etf;

import org.unibl.etf.figure.Figure;

import java.util.LinkedList;

public final class Field {
    private LinkedList<Figure> figures;
    private boolean isHole = false;
    private int numberOfDiamonds = 0;


    public Field()
    {
        figures = new LinkedList<>();
    }

    public LinkedList<Figure> getFigures()
    {
        return figures;
    }

    public void removeFigure()
    {
        LinkedList<Figure> figures1 = new LinkedList<>();
        if (figures.size()>1)
        {
            figures1.add(figures.get(1));
        }
        figures = figures1;
    }
    public void setFigure(Figure figure)
    {
        figures.addFirst(figure);
    }
    public int getNumberOfDiamonds()
    {
        return numberOfDiamonds;
    }
    public boolean isHole()
    {
        return isHole;
    }

    public void setHole(boolean isHole)
    {
        this.isHole = isHole;
    }

    public void addDiamonds(int add)
    {
        numberOfDiamonds+=add;
    }

    public int clearDiamondsFrom()
    {
        int num = numberOfDiamonds;
        numberOfDiamonds=0;
        return num;
    }

}
