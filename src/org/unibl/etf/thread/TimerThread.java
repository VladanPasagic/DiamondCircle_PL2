package org.unibl.etf.thread;

import javax.swing.*;

import org.unibl.etf.Main;
import org.unibl.etf.frame.MainFrame;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class TimerThread extends Thread{

    private long currentTimeElapsed = 0;
    private static TimerThread instance = null;
    private static boolean paused = true;

    public long getCurrentTimeElapsed()
    {
        return currentTimeElapsed;
    }

    public static TimerThread getInstance()
    {
        if (instance == null)
        {
            instance = new TimerThread();
        }
        return instance;
    }
    public void run()
    {
        MainFrame frame = MainFrame.getInstance();
        JTextField tf = frame.getTimer();
        long start = System.currentTimeMillis();
        long end;
        do {
            try {
                end = System.currentTimeMillis();
                currentTimeElapsed = currentTimeElapsed + (end -start)/1000;
                tf.setText("Game Duration: " + currentTimeElapsed  + " s ");
                start = end;
                Thread.sleep(1000);
                while (paused) {
                    Thread.sleep(50);
                    start = System.currentTimeMillis();
                }
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            }
        }while(true);
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
