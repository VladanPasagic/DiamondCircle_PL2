package org.unibl.etf;

import java.util.Random;

import org.unibl.etf.figure.*;
public final class Player {

    private final String name;
    Figure[] figures = new Figure[4];

    public Player(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public Figure[] getFigures()
    {
        return figures;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Player player) {
            return player.name.equals(name);
        }
        return false;
    }

    public void setFigures(FigureColor color, int startingPosition)
    {
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0;i< figures.length;i++)
        {
            int random = rand.nextInt(3) + 1;
            switch (random) {
                case 1 -> figures[i] = new NormalFigure(startingPosition, color);
                case 2 -> figures[i] = new FloatingFigure(startingPosition, color);
                case 3 -> figures[i] = new SuperFastFigure(startingPosition, color);
            }
        }
    }
}
