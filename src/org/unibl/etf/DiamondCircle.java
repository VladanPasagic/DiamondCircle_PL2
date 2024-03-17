package org.unibl.etf;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.unibl.etf.exception.DuplicateValueException;
import org.unibl.etf.exception.EmptyFieldException;
import org.unibl.etf.frame.*;
import org.unibl.etf.thread.GameThread;
public final class DiamondCircle {
    boolean hasStarted = false;


    public static void start(int numOfPlayers, int dimension) {
        new DiamondCircle(numOfPlayers, dimension);
    }

    private DiamondCircle(int numOfPlayers, int dimension) {
        ArrayList<Player> players = new ArrayList<>();



        NamesFrame frame = NamesFrame.getInstance(numOfPlayers);
        JButton button = frame.getButton();
            button.addActionListener(e -> {
                boolean pass = true;
                JTextField[] fields = frame.getTextFields();
                for (int i = 0; pass && i < fields.length; i++) {
                    String playerName = fields[i].getText();
                    if (playerName.isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "The name on position: " + (i + 1) + " shouldn't be empty");
                            pass = false;
                            try
                            {
                                throw new EmptyFieldException();
                            }
                            catch(EmptyFieldException ex) {
                                Logger.getLogger(Main.getLoggerName()).log(Level.INFO, ex.fillInStackTrace().toString());
                            }
                    }
                }
                for (int i = 0; pass && i < fields.length; i++) {
                    String playerName = fields[i].getText();
                    if (!players.contains(new Player(playerName))) {
                        players.add(new Player(playerName));
                    } else {
                        JOptionPane.showMessageDialog(frame, "Duplicate entry please try again.");
                        pass = false;
                        try {
                            throw new DuplicateValueException();
                        } catch (DuplicateValueException ex) {
                            Logger.getLogger(Main.getLoggerName()).log(Level.INFO, ex.fillInStackTrace().toString());
                        }
                    }
                }
                if (pass) {

                    frame.dispose();
                    MainFrame mainFrame = MainFrame.getInstance(dimension, players);
                    JButton startStop = mainFrame.getStartStopButton();
                    GameThread gameThread = new GameThread(dimension, players);
                    gameThread.start();
                    startStop.addActionListener(e1 -> {
                        hasStarted = !hasStarted;
                        if (hasStarted) {
                            gameThread.restart();
                        } else {
                            gameThread.pause();
                        }
                    });
                    JButton filesList = MainFrame.getInstance().getbShowResultList();
                    filesList.addActionListener(e12 -> FilesFrame.getInstance());
                }
            });
        }
    }

