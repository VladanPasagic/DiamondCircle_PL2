package org.unibl.etf.frame;

import javax.swing.*;
import java.awt.*;

import static org.unibl.etf.Utilities.*;


public final class NamesFrame extends JFrame {

    private final int numberOfPlayers;
    private final JTextField[] textFields;
    private final JButton button = new JButton("Confirm your choice");

    private NamesFrame(int numberOfPlayers)
    {
        textFields = new JTextField[numberOfPlayers];
        for (int i = 0;i<numberOfPlayers;i++)
        {
            textFields[i] = new JTextField();
        }
        this.numberOfPlayers = numberOfPlayers;
        BuildFrame();

    }
    public JButton getButton()
    {
        return button;
    }

    public JTextField[] getTextFields()
    {
        return textFields;
    }

    public static NamesFrame getInstance(int numberOfPlayers)
    {
        return new NamesFrame(numberOfPlayers);
    }

    private void BuildFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Set names");
        setSize(400, (numberOfPlayers+1)*60);
        setLayout(new GridLayout(numberOfPlayers+1, 1));
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel[] panels = new JPanel[numberOfPlayers];

        for (int i = 0;i<numberOfPlayers;i++)
        {
            panels[i] = new JPanel();
            JLabel labelPlayer1 = new JLabel(wordWrap("Enter the name of the " + (i+1) + ". player: "));
            paintPanelAndLabel(panels[i], labelPlayer1, textFields[i]);
            getContentPane().add(panels[i]);
        }

        button.setBackground(greyGradient1);
        button.setForeground(Color.white);
        getContentPane().add(button);
        setVisible(true);

    }


}
