package org.unibl.etf.thread;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.Main;
import org.unibl.etf.frame.MainFrame;

public final class DescriptionThread extends Thread{

    private final ArrayList<DescriptionData> descriptionList = new ArrayList<>(1);
    private static boolean stop = true;

    public void run()
    {
        MainFrame frame = MainFrame.getInstance();
        JTextArea textArea = frame.getDescriptionArea();
        do {
            try
            {
            do {
                Thread.sleep(50);
            }while(descriptionList.size()==0);
            textArea.setText(descriptionList.get(0).toString());
                Thread.sleep(1000);
                while (!stop) {
                    Thread.sleep(50);
                }
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Main.getLoggerName()).log(Level.WARNING, ex.fillInStackTrace().toString());
            }
        }while(true);
    }

    public void addDescription(DescriptionData data)
    {
        descriptionList.clear();
        descriptionList.add(data);
    }


    public void pause()
    {
        stop = false;
    }

    public void restartTimer()
    {
        stop = true;
    }
}
