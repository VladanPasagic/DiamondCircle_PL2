package org.unibl.etf;

import org.unibl.etf.exception.InvalidDimensionOfMatrixException;
import org.unibl.etf.exception.InvalidNumberOfPlayersException;
import org.unibl.etf.frame.OptionFrame;

import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Main
{
    private static Handler handler;

    private static String name = "Logger";

    public static String getLoggerName()
    {
        return name;
    }
    static
    {
        File gamesPlayed =  new File(Utilities.Constants.gamesPlayedFilePath);
        File logger = new File(Utilities.Constants.logFilesFilePath);
        if (!gamesPlayed.exists())
        {
            gamesPlayed.mkdir();
        }
        if (!logger.exists())
        {
            logger.mkdir();
        }
    }
    static{
        try {
            handler = new FileHandler(Utilities.Constants.logFilesFilePath + File.separatorChar + Utilities.getLogName());
            Logger.getLogger(Main.name).addHandler(handler);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        OptionFrame optionFrame = OptionFrame.getInstance();
        JButton optionFrameButton = optionFrame.getButton();
        optionFrameButton.addActionListener(e -> {
            int numOfPlayers = 0;
            int matrixDimensionSize = 0;
            boolean pass = true;
            try {
                JTextField numberOfPlayers = optionFrame.getTfNumberOfPlayers();
                numOfPlayers = Integer.parseInt(numberOfPlayers.getText());
                if (numOfPlayers>4 || numOfPlayers<2)
                {
                    JOptionPane.showMessageDialog(optionFrame, Utilities.wordWrap("The number of players isn't in the specified boundaries."));
                    pass = false;
                    try
                    {
                        throw new InvalidNumberOfPlayersException();
                    }
                    catch(InvalidNumberOfPlayersException ex)
                    {
                        Logger.getLogger(Main.name).log(Level.INFO, ex.fillInStackTrace().toString());
                    }
                }
                JTextField dimension = optionFrame.getTfDimension();
                matrixDimensionSize = Integer.parseInt(dimension.getText());
                if (matrixDimensionSize<7 || matrixDimensionSize>10)
                {
                    JOptionPane.showMessageDialog(optionFrame, Utilities.wordWrap("The dimensions of the matrix aren't in the specified boundaries."));
                    pass = false;
                    try
                    {
                        throw new InvalidDimensionOfMatrixException();
                    }
                    catch(InvalidDimensionOfMatrixException ex)
                    {
                        Logger.getLogger(Main.name).log(Level.INFO, ex.fillInStackTrace().toString());
                    }
                }

            }catch (NumberFormatException ex)
            {
                    JOptionPane.showMessageDialog(optionFrame, Utilities.wordWrap("Please write numbers in the correct format."));
                    pass = false;
                    Logger.getLogger(Main.name).log(Level.SEVERE, ex.fillInStackTrace().toString());
            }
            if (pass)
            {
                optionFrame.dispose();
                DiamondCircle.start(numOfPlayers, matrixDimensionSize);
            }
        });
    }
}
