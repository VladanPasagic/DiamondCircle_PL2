package org.unibl.etf.thread;

import org.unibl.etf.Main;
import org.unibl.etf.Utilities;
import org.unibl.etf.frame.MainFrame;
import org.unibl.etf.io.FileCounter;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class FileCounterThread extends Thread{

    public void run()
    {
        JLabel gamesPlayed = MainFrame.getInstance().getlGamesPlayed();
        do {
            try {
                int totalGamesPlayed = FileCounter.countFilesInDirectory().size();
                gamesPlayed.setText(Utilities.wordWrap("Games played in total: " + totalGamesPlayed));
                Thread.sleep(5000);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Main.getLoggerName()).log(Level.INFO, ex.fillInStackTrace().toString());
            }
        }while(true);
    }
}
