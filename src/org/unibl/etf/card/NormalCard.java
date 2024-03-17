package org.unibl.etf.card;

import java.awt.image.BufferedImage;

public final class NormalCard extends Card{

    private final int numberOfMoves;

    public NormalCard(BufferedImage image, int numberOfMoves)
    {
        super(image);
        this.numberOfMoves = numberOfMoves;
    }

    public int getNumberOfMoves()
    {
        return numberOfMoves;
    }
}
