package org.unibl.etf.frame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import org.unibl.etf.Utilities;
import org.unibl.etf.Player;
import org.unibl.etf.figure.FigureColor;


public final class MainFrame extends JFrame{

    private final int matrixDimension;
    private static MainFrame singleInstance = null;
    private final JLabel lGamesPlayed = new JLabel();
    private final JButton bShowResultList = new JButton(Utilities.wordWrap("Show list with results"));
    private final JButton bStartStop = new JButton(Utilities.wordWrap("Start/Stop"));
    private JTextField timer = new JTextField();
    private final JPanel pLeft = new JPanel();
    private final JTextArea descriptionArea = new JTextArea();
    private final static int gamesPlayed = 0;

    private final JLabel pCard = new JLabel();

    private JButton[] fieldButtons;

    private MainFrame(int dimension, ArrayList<Player> players) {
        matrixDimension = dimension;
        BuildFrame(players);
    }

    public static MainFrame getInstance()
    {
        return singleInstance;
    }
    
    public static MainFrame getInstance(int dimension, ArrayList<Player> players)
    {
        MainFrame frame = new MainFrame(dimension, players);
        singleInstance = frame;
        return frame;
    }

    public JPanel getLeft() {
        return pLeft;
    }

    public JButton[] getFieldButtons()
    {
        return fieldButtons;
    }
    public JTextArea getDescriptionArea()
    {
        return descriptionArea;
    }

    public JTextField getTimer()
    {
        return timer;
    }

    public JLabel getlGamesPlayed()
    {
        return lGamesPlayed;
    }
    public JLabel getCardPanel()
    {
        return pCard;
    }

    public JButton getStartStopButton()
    {
        return bStartStop;
    }
    public JButton getbShowResultList()
    {
        return bShowResultList;
    }
    private void BuildFrame(ArrayList<Player> players)
    {
        setSize(1200, 800);
        setTitle("DiamondCircle");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextField tfTitle = new JTextField();
        JPanel pTitle = new JPanel(new BorderLayout());
        tfTitle.setText("Diamond Circle");
        tfTitle.setFont(Utilities.defaultFont);
        tfTitle.setBackground(Utilities.greyGradient2);
        tfTitle.setForeground(Utilities.discordBlue);
        tfTitle.setEditable(false);
        pTitle.setSize(new Dimension(300, 150));
        pTitle.add(tfTitle, BorderLayout.CENTER);
        pTitle.add(Utilities.makeEmptyPanel(70, 10), BorderLayout.WEST);
        pTitle.add(Utilities.makeEmptyPanel(70, 10), BorderLayout.EAST);
        pTitle.add(Utilities.makeEmptyPanel(10, 50), BorderLayout.SOUTH);
        pTitle.add(Utilities.makeEmptyPanel(10, 50), BorderLayout.NORTH);

        getContentPane().setLayout(new BorderLayout());
        JPanel pTop = new JPanel(new BorderLayout());
        pTop.setBackground(Utilities.greyGradient1);
        pTop.setPreferredSize(new Dimension(1200, 150));

        lGamesPlayed.setText(Utilities.wordWrap("Games played in total: " + gamesPlayed));
        lGamesPlayed.setForeground(Color.white);
        lGamesPlayed.setFont(Utilities.defaultFont);
        lGamesPlayed.setPreferredSize(new Dimension(450, 150));
        pTop.add(lGamesPlayed, BorderLayout.WEST);
        pTop.add(pTitle, BorderLayout.CENTER);
        JPanel pTopRight = new JPanel();
        pTopRight.setPreferredSize(new Dimension(450, 150));
        pTopRight.setLayout(new BorderLayout());
        bStartStop.setFont(Utilities.defaultFont);
        pTopRight.add(bStartStop, BorderLayout.CENTER);
        pTopRight.add(Utilities.makeEmptyPanel(180, 10), BorderLayout.WEST);
        pTopRight.add(Utilities.makeEmptyPanel(80, 10), BorderLayout.EAST);
        pTopRight.add(Utilities.makeEmptyPanel(10, 50), BorderLayout.SOUTH);
        pTopRight.add(Utilities.makeEmptyPanel(10, 50), BorderLayout.NORTH);
        pTop.add(pTopRight, BorderLayout.EAST);

        JPanel subBottomTop = new JPanel(new BorderLayout());
        subBottomTop.setPreferredSize(new Dimension(1200, 100));
        subBottomTop.add(Utilities.makeEmptyPanel(350, 100), BorderLayout.WEST);
        subBottomTop.add(Utilities.makeEmptyPanel(350, 100), BorderLayout.EAST);
        JPanel namesPanel = new JPanel();
        namesPanel.setLayout(new GridLayout(1, players.size()));
        namesPanel.setBackground(Utilities.greyGradient1);

        FigureColor[] colors = FigureColor.values();
        for (int i = 0; i<players.size();i++)
        {
            JLabel label = new JLabel(players.get(i).getName());
            label.setForeground(FigureColor.getColor(colors[i]));
            namesPanel.add(label);
        }

        subBottomTop.add(namesPanel, BorderLayout.CENTER);
        JPanel pBottom = new JPanel(new BorderLayout());
        pBottom.setPreferredSize(new Dimension(1200, 650));

        pLeft.setBackground(Utilities.greyGradient1);
        pLeft.setPreferredSize(new Dimension(300, 350));

        JPanel pRight = new JPanel(new BorderLayout());
        pRight.setBackground(Utilities.greyGradient1);
        pRight.setPreferredSize(new Dimension(300, 350));

        JPanel pBottomRight = new JPanel(new BorderLayout());
        pBottomRight.setPreferredSize(new Dimension(300, 200));
        bShowResultList.setFont(Utilities.defaultFont);
        pBottomRight.add(bShowResultList, BorderLayout.CENTER);
        pBottomRight.add(Utilities.makeEmptyPanel(60, 10), BorderLayout.WEST);
        pBottomRight.add(Utilities.makeEmptyPanel(60, 10), BorderLayout.EAST);
        pBottomRight.add(Utilities.makeEmptyPanel(10, 25), BorderLayout.SOUTH);
        pBottomRight.add(Utilities.makeEmptyPanel(10, 25), BorderLayout.NORTH);

        JPanel rightMiddle = new JPanel(new BorderLayout());
        rightMiddle.setPreferredSize(new Dimension(300, 350));
        rightMiddle.setBackground(Utilities.greyGradient1);

        JTextField currentCard = new JTextField("Current card");
        currentCard.setPreferredSize(new Dimension(250, 50));
        currentCard.setForeground(Color.white);
        currentCard.setBackground(Utilities.greyGradient2);
        currentCard.setFont(Utilities.defaultFont);

        timer = new JTextField("Game duration: 0 s");
        timer.setPreferredSize(new Dimension(250, 50));
        timer.setForeground(Color.white);
        timer.setBackground(Utilities.greyGradient2);
        timer.setFont(Utilities.defaultFont);

        JPanel bottomMiddle = new JPanel(new BorderLayout());
        bottomMiddle.setBackground(Utilities.greyGradient1);
        bottomMiddle.setPreferredSize(new Dimension(900,200));

        descriptionArea.setFont(Utilities.defaultFont);
        descriptionArea.setBackground(Utilities.greyGradient2);
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setText("");
        descriptionArea.setSize(new Dimension(400, 200));
        bottomMiddle.add(descriptionArea, BorderLayout.CENTER);
        bottomMiddle.add(Utilities.makeEmptyPanel(10, 50), BorderLayout.NORTH);
        bottomMiddle.add(Utilities.makeEmptyPanel(10, 50), BorderLayout.SOUTH);
        bottomMiddle.add(Utilities.makeEmptyPanel(300, 10), BorderLayout.WEST);
        bottomMiddle.add(pBottomRight, BorderLayout.EAST);

        rightMiddle.add(timer, BorderLayout.SOUTH);
        rightMiddle.add(currentCard, BorderLayout.NORTH);
        rightMiddle.add(Utilities.makeEmptyPanel(25, 350), BorderLayout.WEST);
        rightMiddle.add(Utilities.makeEmptyPanel(25, 350), BorderLayout.EAST);

        pCard.setSize(new Dimension(300, 300));
        rightMiddle.add(pCard, BorderLayout.CENTER);

        JPanel pCenter = new JPanel(new GridLayout(matrixDimension, matrixDimension));
        fieldButtons = new JButton[matrixDimension*matrixDimension];

        for (int i = 0;i<matrixDimension*matrixDimension;i++)
        {
            fieldButtons[i] = new JButton(""+ (i+1));
            fieldButtons[i].setEnabled(false);
            fieldButtons[i].setBackground(Color.white);
            pCenter.add(fieldButtons[i]);
        }

        pCenter.setBackground(Utilities.greyGradient1);
        pCenter.setPreferredSize(new Dimension(600, 450));

        pRight.add(rightMiddle, BorderLayout.CENTER);

        getContentPane().add(pTop, BorderLayout.NORTH);
        getContentPane().add(pBottom, BorderLayout.SOUTH);

        pBottom.add(subBottomTop, BorderLayout.NORTH);
        pBottom.add(pLeft, BorderLayout.WEST);
        pBottom.add(bottomMiddle, BorderLayout.SOUTH);
        pBottom.add(pRight, BorderLayout.EAST);
        pBottom.add(pCenter, BorderLayout.CENTER);

        setVisible(true);
    }
}
