package org.unibl.etf.io;

import org.unibl.etf.Main;
import org.unibl.etf.Player;
import org.unibl.etf.figure.Figure;
import org.unibl.etf.thread.GameThread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class GameWriter {

    ArrayList<Player> players;

    public GameWriter(ArrayList<Player> players)
    {
        this.players = players;
        writeGame();
    }

    private void writeGame()
    {
        try {
            String fileName = getNameOfFile();
            FileWriter fw = new FileWriter(fileName);

            for (int i =0; i<players.size();i++)
            {
                fw.write("Player " + (i+1) + " -  " + players.get(i).getName() + "\n");
                Figure[] figures = players.get(i).getFigures();
                for (int j = 0; j<figures.length;j++)
                {
                    fw.write("Figure " + (j+1) + " - "+ figures[j]);
                    fw.write("\n");
                }
                fw.write("\n");
            }
            fw.write("Total time elapsed in game: " + GameThread.getGameThread().getTimer().getCurrentTimeElapsed());
            fw.close();
        }
        catch(IOException ex)
        {
            Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
        }

    }

    private String getNameOfFile()
    {
        Calendar instance = Calendar.getInstance();
        String time = instance.get(Calendar.HOUR_OF_DAY) + "_" + instance.get(Calendar.MINUTE) + "_" + instance.get(Calendar.SECOND);
        return "GamesPlayed" + File.separatorChar + "GAME_ "+time + ".txt";
    }
}
