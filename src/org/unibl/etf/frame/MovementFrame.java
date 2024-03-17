package org.unibl.etf.frame;

import org.unibl.etf.Main;
import org.unibl.etf.Player;
import org.unibl.etf.figure.Figure;
import org.unibl.etf.thread.GameThread;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MovementFrame extends JFrame {

    private static MovementFrame frame = null;

    public synchronized static MovementFrame getInstance(Player player, Figure figure) throws InterruptedException {
        try {
            if (frame != null) {
                frame.dispose();
            }
            frame = new MovementFrame(player, figure);
        } catch (ConcurrentModificationException ex) {
            Logger.getLogger(Main.getLoggerName()).log(Level.WARNING, ex.fillInStackTrace().toString());
        }
        return frame;
    }
    public MovementFrame(Player player, Figure figure) throws InterruptedException, ConcurrentModificationException
    {
        int dimension = GameThread.getGameThread().getDimension();
        setTitle("Movement of " + player.getName() + " " + figure.getClass().getSimpleName());
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 500));
        JButton[] buttons = new JButton[dimension*dimension];
        setLayout(new GridLayout(dimension, dimension));

        for (int i = 0; i<dimension*dimension;i++)
        {
            buttons[i] = new JButton(""+(i+1));
            buttons[i].setEnabled(false);
            getContentPane().add(buttons[i]);
        }
        setVisible(true);
        ArrayList<Integer> visited = figure.getVisitedFields();

        for (Integer integer : visited) {
            buttons[integer].setBackground(figure.getColor());
        }


    }



}
