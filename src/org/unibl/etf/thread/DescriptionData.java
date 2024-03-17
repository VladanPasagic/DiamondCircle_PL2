package org.unibl.etf.thread;

import org.unibl.etf.Player;
import org.unibl.etf.figure.Figure;

public final class DescriptionData {

    private final Player player;
    private final int turnCount;
    Figure figure;
    private final int startPosition;
    private final int distanceTraveled;
    private final int diamondsCollected;

    public DescriptionData(Player player, Figure figure, int turnCount, int startPosition, int distanceTraveled, int diamondsCollected)
    {
        this.player = player;
        this.figure = figure;
        this.turnCount = turnCount;
        this.startPosition = startPosition +1;
        this.distanceTraveled = distanceTraveled;
        this.diamondsCollected = diamondsCollected;
    }

    @Override
    public String toString()
    {
        return "Turn " + turnCount + "\nPlayer " + player.getName() + " is moving figure "+figure.getClass().getSimpleName() + "\nfrom position " + startPosition +".\nTotal distance to travel: " + distanceTraveled+ ". Diamonds collected: " + diamondsCollected + ".";
    }
}
