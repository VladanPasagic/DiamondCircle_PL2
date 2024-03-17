package org.unibl.etf.figure;

import org.unibl.etf.Field;
import org.unibl.etf.thread.GameThread;

public final class GhostFigure extends Figure{

    public GhostFigure(int currentPosition, FigureColor color) {
        super(currentPosition, color);
    }

    @Override
    public void move(int spacesMoved, int dimension) {
        Field[] fields = GameThread.getGameThread().getMovementMatrix();
        addVisitedField(getCurrentPosition());
        do {
            spacesMoved--;
            this.move(dimension);
            if (fields[getCurrentPosition()].getFigures().size() != 0) {
                spacesMoved++;
            }
            if (getCurrentPosition() == GameThread.getGameThread().getFinish()) {
                setCurrentPosition(GameThread.getGameThread().getStartingPosition());
                setHelpVariable(dimension);
                removeVisitedFields();
                addVisitedField(GameThread.getGameThread().getStartingPosition());
                spacesMoved++;
            }
            if (getCurrentPosition() == GameThread.getGameThread().getStartingPosition())
            {
                spacesMoved++;
            }
        }while(spacesMoved>0);
    }
}
