package org.unibl.etf.thread;

import org.unibl.etf.Field;
import org.unibl.etf.Main;
import org.unibl.etf.figure.FloatingFigure;
import org.unibl.etf.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ButtonColoringThread extends Thread{
    @Override
    public void run()
    {
        do {
            JButton[] buttons = MainFrame.getInstance().getFieldButtons();
            Field[] fields = GameThread.getGameThread().getMovementMatrix();

            for (int i = 0;i<fields.length;i++)
            {
                if (fields[i].isHole())
                {

                    if (fields[i].getFigures().size()!=0) {
                        if (fields[i].getFigures().getFirst() instanceof FloatingFigure)
                            buttons[i].setBackground(fields[i].getFigures().getFirst().getColor());
                        else
                            buttons[i].setBackground(Color.black);
                    }
                    else

                        buttons[i].setBackground(Color.black);
                }
                else if (fields[i].getFigures().size()!=0)
                {
                    buttons[i].setBackground(fields[i].getFigures().getFirst().getColor());
                }
                else
                {
                    buttons[i].setBackground(Color.white);
                }
            }
            try {
                Thread.sleep(50);
            }
            catch(InterruptedException | NoSuchElementException ex)
            {
                Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            }

        }while(true);
    }
}
