package org.unibl.etf.frame;


import javax.swing.*;
import java.awt.*;

import static org.unibl.etf.Utilities.*;
public final class OptionFrame extends JFrame {

    private final JButton button = new JButton("Confirm choice");

    private final JTextField tfNumberOfPlayers = new JTextField();

    private final JTextField tfDimension = new JTextField();

    public JButton getButton()
    {
        return button;
    }

    public JTextField getTfNumberOfPlayers()
    {
        return tfNumberOfPlayers;
    }

    public JTextField getTfDimension()
    {
        return tfDimension;
    }

    private OptionFrame()
    {
        setTitle("Option");
        setSize(new Dimension(400, 180));
        setLayout(new GridLayout(3, 1));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel pTop = new JPanel(new BorderLayout());
        JLabel lNumberOfPlayers = new JLabel("Enter number of players");
        paintPanelAndLabel(pTop, lNumberOfPlayers, tfNumberOfPlayers);

        JPanel pMiddle = new JPanel(new BorderLayout());
        JLabel lDimension = new JLabel("Enter dimension of matrix");
        paintPanelAndLabel(pMiddle, lDimension, tfDimension);

        JPanel pBottom = new JPanel(new BorderLayout());
        button.setBackground(greyGradient1);
        button.setForeground(Color.white);
        button.setFont(defaultFont);
        pBottom.add(button);

        getContentPane().add(pTop);
        getContentPane().add(pMiddle);
        getContentPane().add(pBottom);
        setVisible(true);

    }

    public static OptionFrame getInstance()
    {
        return new OptionFrame();
    }
}
