package org.unibl.etf.figure;

import org.unibl.etf.Field;
import org.unibl.etf.thread.GameThread;

public final class SuperFastFigure extends Figure{

    public SuperFastFigure(int currentPosition, FigureColor color)
    {
        super(currentPosition, color);
    }

    @Override
    public void move(int spacesMoved, int dimension) throws InterruptedException {
        spacesMoved = spacesMoved * 2;
        spacesMoved+= getCollectedDiamonds();
        Field[] fields = GameThread.getGameThread().getMovementMatrix();
        if (getCurrentPosition() == GameThread.getGameThread().getStartingPosition())
            fields[getCurrentPosition()].setFigure(this);
        Thread.sleep(1000);
            do {
                int x = move(dimension);
                fields[x].removeFigure();
                if (fields[getCurrentPosition()].isHole()) {
                    this.setInHole();
                    fields[getCurrentPosition()].removeFigure();
                    spacesMoved = 0;
                }
                if (fields[getCurrentPosition()].getNumberOfDiamonds() != 0) {
                    addDiamonds(fields[getCurrentPosition()].clearDiamondsFrom());
                }
                if (getCurrentPosition() == GameThread.getGameThread().getFinish()) {
                    this.setFinished();
                    spacesMoved=0;
                }
                spacesMoved--;
                if (fields[getCurrentPosition()].getFigures().size() != 0 && spacesMoved==0) {
                    spacesMoved++;
                }
                fields[getCurrentPosition()].setFigure(this);
                Thread.sleep(500);
            } while (spacesMoved > 0);
        if (getCurrentPosition() == GameThread.getGameThread().getFinish() || fields[getCurrentPosition()].isHole())
        {
            fields[getCurrentPosition()].removeFigure();
        }
            addVisitedField(getCurrentPosition());
    }


}
