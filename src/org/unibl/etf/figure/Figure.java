package org.unibl.etf.figure;

import org.unibl.etf.thread.GameThread;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Figure {

    private int helpVariable;
    private final FigureColor color;
    private long timeOfMovement = 0;
    private int collectedDiamonds = 0;
    private int currentPosition;
    private boolean inHole = false;
    private boolean finished = false;
    private final ArrayList<Integer> visitedFields = new ArrayList<>();

    protected Figure(int currentPosition, FigureColor color) {
        this.currentPosition = currentPosition;
        visitedFields.add(currentPosition);
        this.color = color;
        this.helpVariable = GameThread.getGameThread().getDimension();
    }

    public void removeVisitedFields()
    {
        visitedFields.clear();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Figure figure)
        {
            return this.color == figure.color;
        }
        return false;
    }

    public int getHelpVariable() {
        return helpVariable;
    }

    public void setHelpVariable(int helpVariable)
    {
        this.helpVariable = helpVariable;
    }

    public void setFinished() {
        this.finished = true;
        GameThread.getGameThread().addFinishedFigure();
    }

    public boolean hasFinished() {
        return finished;
    }

    public int getCollectedDiamonds() {
        return collectedDiamonds;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void deductHelpVariable() {
        helpVariable--;
    }

    public void addDiamonds(int add) {
        collectedDiamonds += add;
    }


    public Color getColor() {
        return FigureColor.getColor(color);
    }

    public long getTimeOfMovement() {
        return timeOfMovement;
    }

    public void setTimeOfMovement(long timeOfMovement) {
        this.timeOfMovement += timeOfMovement;
    }

    public ArrayList<Integer> getVisitedFields() {
        return visitedFields;
    }

    public void addVisitedField(int visited) {
        visitedFields.add(visited);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public boolean isInHole() {
        return inHole;
    }

    public void setInHole() {
        this.inHole = true;
        GameThread.getGameThread().addFigureInHole();
    }

    public abstract void move(int spacesMoved, int dimension) throws InterruptedException;

    private String writeVisitedFields() {
        String visited = "";
        int length = visitedFields.size();
        for (int i = 0; i < length - 1; i++) {
            visited += (visitedFields.get(i)+1) + "-";
        }
        visited += visitedFields.get(length - 1);
        return visited;
    }

    @Override
    public String toString() {
        return "(" + this.getClass().getSimpleName() + ", " + color + ") - " + "visited fields (" + writeVisitedFields()
                + ") - " + "finished " + hasFinished()+"\n" + "Total time spent in movement: " + getTimeOfMovement() +"ms";
    }

    protected static int getQuadrant(int x) {
        int dimension = GameThread.getGameThread().getDimension();
        if (dimension * dimension / 2 < x) {
            if (x % dimension >= dimension / 2) {
                return 3;
            } else {
                return 4;
            }
        } else {
            if (x % dimension >= dimension / 2) {
                return 2;
            } else {
                return 1;
            }
        }
    }

    protected int move(int dimension) {
        int x = getCurrentPosition();
        int helpVar = getHelpVariable();
        if ((x + 1 - helpVar) % dimension == 0) {
            setCurrentPosition(x + dimension - 1);
        } else if (x / dimension == helpVar - 1) {
            setCurrentPosition(x - dimension - 1);
        } else if ((x + helpVar) % dimension == 0 && getQuadrant(x) != 1) {
            setCurrentPosition(x - dimension + 1);
        } else if (x / dimension == (dimension - helpVar + 1) && getVisitedFields().size() > 1 && (getQuadrant(x) == 1 || getQuadrant(x) == 4)) {
            int y = x+1;
            setCurrentPosition(y);
            deductHelpVariable();
        } else {
            int quadrant = getQuadrant(x);
            switch (quadrant) {
                case 1 -> setCurrentPosition(x - dimension + 1);
                case 2 -> setCurrentPosition(x + dimension + 1);
                case 3 -> setCurrentPosition(x + dimension - 1);
                case 4 -> setCurrentPosition(x - dimension - 1);
            }
        }
        return x;
    }
}

