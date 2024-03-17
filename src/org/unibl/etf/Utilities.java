package org.unibl.etf;

import java.awt.*;
import java.io.File;
import java.util.Calendar;
import javax.swing.*;

public final class Utilities {


    public static class Constants
    {
        private static char c = File.separatorChar;
        public static String imageFilePath = "src" + c + "org" + c + "unibl" + c + "etf" + c +"Resources"+c;
        public static String gamesPlayedFilePath = "GamesPlayed";

        public static String logFilesFilePath = "LogFiles";
    }
    public static Font defaultFont = new Font(Font.MONOSPACED, Font.BOLD, 16);
    public static Color greyGradient1 = new Color(54, 57, 62);
    public static Color discordBlue = new Color(114, 137, 218);
    public static Color greyGradient2 = new Color(66, 69, 73);

    public static JPanel makeEmptyPanel(int width, int height)
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBackground(greyGradient1);
        return panel;
    }

    public static String wordWrap(String str)
    {
        return "<html><p>" + str + "</p></html>";
    }

    public static void paintPanelAndLabel(JPanel panel, JLabel label, JTextField textField)
    {
        panel.setBackground(greyGradient1);
        panel.setLayout(new BorderLayout());
        label.setFont(defaultFont);
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.NORTH);
        textField.setBackground(greyGradient2);
        textField.setForeground(Color.WHITE);
        textField.setFont(defaultFont);
        panel.add(textField, BorderLayout.CENTER);
    }

    public static String getLogName()
    {
        Calendar date = Calendar.getInstance();
        return "log_" + date.get(Calendar.DAY_OF_MONTH) + "_" + date.get(Calendar.MONTH)
        + "_" +date.get(Calendar.YEAR) + "_" + date.get(Calendar.HOUR_OF_DAY) + "_"
                + date.get(Calendar.MINUTE) + ".log";
    }

}
