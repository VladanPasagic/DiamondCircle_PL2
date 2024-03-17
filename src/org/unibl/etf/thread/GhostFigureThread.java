package org.unibl.etf.thread;

import org.unibl.etf.Field;
import org.unibl.etf.Main;
import org.unibl.etf.figure.GhostFigure;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class GhostFigureThread extends Thread{

    private final GhostFigure figure;
    private boolean paused;
    public GhostFigureThread(GhostFigure figure)
    {
        this.figure = figure;
    }

    @Override
    public void run()
    {
        Field[] array = GameThread.getGameThread().getMovementMatrix();
        try {
            do {
                Random rand = new Random(System.currentTimeMillis());
                int dimension = GameThread.getGameThread().getDimension();
                int numberOfDiamonds = rand.nextInt(dimension-1)+2;
                do {
                    int spacesMoved = rand.nextInt(dimension)+2;
                    figure.move(spacesMoved, dimension);
                    array[figure.getCurrentPosition()].addDiamonds(1);
                    numberOfDiamonds--;
                }while(numberOfDiamonds>0);
                Thread.sleep(5000);
                do {
                    Thread.sleep(50);
                } while (paused);
            } while (true);
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
        }
    }

    public void pause()
    {
        paused = true;
    }

    public void restart()
    {
        paused = false;
    }

}
