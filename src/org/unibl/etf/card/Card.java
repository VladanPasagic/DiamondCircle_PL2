package org.unibl.etf.card;

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public abstract class Card {

    private final ImageIcon imageIcon;

    protected Card(BufferedImage image)
    {
        imageIcon = new ImageIcon(image);
    }

    public ImageIcon getImage()
    {
        return imageIcon;
    }
}
