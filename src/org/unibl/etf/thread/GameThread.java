package org.unibl.etf.thread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.unibl.etf.Field;
import org.unibl.etf.Main;
import org.unibl.etf.Player;
import org.unibl.etf.Utilities;
import org.unibl.etf.figure.*;
import org.unibl.etf.card.*;
import org.unibl.etf.frame.MainFrame;
import org.unibl.etf.io.GameWriter;
import org.unibl.etf.listener.LabelListen;

import static org.unibl.etf.Utilities.*;

public class GameThread extends Thread{


    private static GameThread gameThread = null;
    private final int startingPosition;

    private final int finish;
    private int turnCounter = 0;
    private int figuresInHoles = 0;
    private int figuresFinished = 0;
    private final ArrayList<Player> players;
    private LinkedList<Card> cards;
    private final JLabel[] labels;
    private final Field[] movementMatrix;

    private final int dimension;
    boolean paused = true;

    DescriptionThread description = new DescriptionThread();
    TimerThread timer = new TimerThread();
    GhostFigureThread ghostFigureThread;
    FileCounterThread fileCounterThread = new FileCounterThread();

    ButtonColoringThread buttonColoringThread = new ButtonColoringThread();
    private boolean gameFinished;

    public TimerThread getTimer()
    {
        return timer;
    }
    @Override
    public void run() {
        fileCounterThread.start();
        pause();
        do
        {
            try {
                Thread.sleep(50);
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            }
        }while(paused);
        timer.start();
        MainFrame frame = MainFrame.getInstance();
        JLabel cardIcon = frame.getCardPanel();
        JButton[] buttons = frame.getFieldButtons();
        description.start();
        ghostFigureThread = new GhostFigureThread(new GhostFigure(startingPosition, FigureColor.GRAY));
        ghostFigureThread.start();
        buttonColoringThread.start();
        Card currentCard;
        do {
            try {
                for (Player player : players) {
                    turnCounter++;
                    do {
                        currentCard = drawCard();
                        if (currentCard instanceof SpecialCard) {
                            cardIcon.setIcon(currentCard.getImage());
                            Thread.sleep(1000);
                            createHoles();
                        } else {
                            Figure currentMovingFigure = getCurrentPlayerActiveFigure(player);
                            if (currentMovingFigure != null) {
                                    int x = currentMovingFigure.getCurrentPosition();
                                    if (currentMovingFigure instanceof SuperFastFigure)
                                        description.addDescription(new DescriptionData(player, currentMovingFigure, turnCounter, x, ((NormalCard) currentCard).getNumberOfMoves()*2, currentMovingFigure.getCollectedDiamonds()));
                                    else
                                        description.addDescription(new DescriptionData(player, currentMovingFigure, turnCounter, x, ((NormalCard) currentCard).getNumberOfMoves(), currentMovingFigure.getCollectedDiamonds()));
                                    buttons[x].setToolTipText(null);
                                    Calendar start = Calendar.getInstance();
                                    cardIcon.setIcon(currentCard.getImage());
                                    currentMovingFigure.move(((NormalCard) currentCard).getNumberOfMoves(), dimension);
                                    Calendar end = Calendar.getInstance();
                                    currentMovingFigure.setTimeOfMovement(end.getTimeInMillis() - start.getTimeInMillis());
                                    x = currentMovingFigure.getCurrentPosition();
                                    buttons[x].setToolTipText(currentMovingFigure.getClass().getSimpleName());
                                    deleteHoles();
                                }
                            }
                        while (paused) {
                            timer.pause();
                            description.pause();
                            ghostFigureThread.pause();
                            Thread.sleep(50);
                        }
                    }while(currentCard instanceof SpecialCard);
                }
                isGameFinished();
                ghostFigureThread.restart();
            }catch(InterruptedException ex)
            {
                Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            }
            } while (!gameFinished);
        new GameWriter(players);
        timer.pause();
        description.pause();
        ghostFigureThread.pause();
        for (Handler h: Logger.getLogger(Main.getLoggerName()).getHandlers())
        {
            h.close();
        }
    }

    public static GameThread getGameThread()
    {
        return gameThread;
    }
    public Field[] getMovementMatrix()
    {
        return movementMatrix;
    }

    public void addFinishedFigure()
    {
        figuresFinished++;
    }

    public void addFigureInHole()
    {
        figuresInHoles++;
    }

    public int getStartingPosition()
    {
        return startingPosition;
    }
    public int getFinish()
    {
        return finish;
    }
    public int getDimension()
    {
        return dimension;
    }
    private Card drawCard()
    {
        Card currentCard = cards.getFirst();
        cards.remove(currentCard);
        cards.addLast(currentCard);
        return currentCard;
    }

    private void deleteHoles()
    {
        JButton[] buttons = MainFrame.getInstance().getFieldButtons();
        for (int i = 0;i<movementMatrix.length;i++)
        {
            if (movementMatrix[i].isHole())
            {
                movementMatrix[i].setHole(false);
                buttons[i].setBackground(Color.white);
            }
        }
    }

    private Figure getCurrentPlayerActiveFigure(Player player)
    {
        for (Figure figure : player.getFigures())
        {
            if (!figure.isInHole() && !figure.hasFinished())
                return figure;
        }
        return null;
    }

    private void createHoles()
    {
        JButton[] buttons = MainFrame.getInstance().getFieldButtons();
        Random rand = new Random(System.currentTimeMillis());
        int random = rand.nextInt(dimension)+2;
        HoleDiggerFigure figure = new HoleDiggerFigure(startingPosition, FigureColor.BLACK);
        do {
            int spacesMoved = rand.nextInt(dimension)+2;
            figure.move(spacesMoved, dimension);
            buttons[figure.getCurrentPosition()].setBackground(figure.getColor());
            movementMatrix[figure.getCurrentPosition()].setHole(true);
            random--;
        }while(random>0);
    }

    public GameThread(int dimension, ArrayList<Player> players)
    {
        startingPosition = dimension/2;
        this.dimension = dimension;
        gameFinished = false;
        this.players = players;
        labels = new JLabel[4*players.size()];
        movementMatrix = new Field[dimension*dimension];
        if (dimension%2==0)
        {
            finish = (dimension*dimension+dimension-2)/2;
        }
        else
        {
            finish = (dimension*dimension)/2;
        }
        for (int i = 0;i<movementMatrix.length;i++)
        {
            movementMatrix[i] = new Field();
        }
        gameThread = this;
        getFigures();
        Collections.shuffle(this.players);
        addFigureLabels();
        getCards();
    }

    private void getFigures()
    {
        FigureColor[] colors = FigureColor.values();
        for(int i = 0; i<players.size();i++)
        {
            players.get(i).setFigures(colors[i], startingPosition);
        }
    }
    private void addFigureLabels()
    {
        JPanel panel = MainFrame.getInstance().getLeft();
        panel.setLayout(new GridLayout(4*players.size(), 1));
        panel.setBackground(greyGradient2);
        int labelCount = 0;
        for (Player player : players)
        {
            for (int i = 0;i<player.getFigures().length;i++)
            {
                labels[labelCount] = new JLabel(player.getName() + " Figure #" + (i+1));
                labels[labelCount].setForeground(Color.white);
                labels[labelCount].setFont(defaultFont);
                labels[labelCount].addMouseListener(new LabelListen(player, player.getFigures()[i]));
                labelCount++;
                panel.add(labels[labelCount-1]);
            }
        }
    }
    private void getCards()
    {
        char c = File.separatorChar;
        cards = new LinkedList<>();
        try {
            for (int i = 0; i < 10; i++)
                cards.add(new NormalCard(ImageIO.read(new File(Constants.imageFilePath + "NormalCard1.png")), 1));
            for (int i = 0; i<10;i++)
                cards.add(new NormalCard(ImageIO.read(new File(Constants.imageFilePath + "NormalCard2.png")), 2));
            for (int i = 0; i<10;i++)
                cards.add(new NormalCard(ImageIO.read(new File(Constants.imageFilePath + "NormalCard3.png")), 3));
            for (int i = 0; i<10;i++)
                cards.add(new NormalCard(ImageIO.read(new File(Constants.imageFilePath + "NormalCard4.png")), 4));
            for (int i = 0; i<12;i++)
                cards.add(new SpecialCard(ImageIO.read(new File(Constants.imageFilePath + "SpecialCard.png"))));
        }
        catch(IOException ex)
        {
            Logger.getLogger(Main.getLoggerName()).log(Level.WARNING, ex.fillInStackTrace().toString());
        }
        Collections.shuffle(cards);
    }

    private void isGameFinished()
    {
        if (figuresFinished + figuresInHoles == players.size()*4)
            gameFinished = true;
    }

    public void pause()
    {
        paused = true;
    }

    public void restart()
    {
        paused = false;
        timer.restart();
        description.restartTimer();
    }
}
