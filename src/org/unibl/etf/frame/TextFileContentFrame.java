package org.unibl.etf.frame;

import org.unibl.etf.Utilities;
import org.unibl.etf.io.GameReader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public final class TextFileContentFrame extends JFrame {

    private static TextFileContentFrame frame = null;

    public static TextFileContentFrame getInstance(String fileName) throws IOException
    {
        if (frame != null)
        {
            frame.dispose();
        }
        frame = new TextFileContentFrame(fileName);
        return frame;
    }

    public TextFileContentFrame(String fileName) throws IOException
    {
        setTitle("Notepad");
        setAlwaysOnTop(true);
        GameReader gr = new GameReader(fileName);
        JTextArea textArea = new JTextArea(gr.readGame());
        textArea.setFont(Utilities.defaultFont);
        textArea.setBackground(Utilities.greyGradient1);
        textArea.setForeground(Color.white);
        textArea.setEditable(false);
        setLocationRelativeTo(null);
        setSize(new Dimension(500, 250));
        getContentPane().add(new JScrollPane(textArea));
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
