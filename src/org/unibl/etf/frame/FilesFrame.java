package org.unibl.etf.frame;

import org.unibl.etf.io.FileCounter;
import org.unibl.etf.listener.SelectionListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public final class FilesFrame extends JDialog
{
    private static FilesFrame frame = null;

    public static FilesFrame getInstance()
    {
        if (frame != null)
        {
            frame.dispose();
        }
        frame = new FilesFrame();
        return frame;
    }
    public FilesFrame()
    {
        JTable table;
        setSize(new Dimension(450, 150));
        setAlwaysOnTop(true);
        setResizable(false);
        setLocationRelativeTo(null);
        ArrayList<String> files = FileCounter.countFilesInDirectory();
        String[] columnsNames = {"File Name"};
        Object[][] data = new Object[files.size()][1];
        for (int i = 0;i<files.size();i++)
        {
            data[i][0] = files.get(i);
        }
        table = new JTable(data, columnsNames);
        table.getSelectionModel().addListSelectionListener(SelectionListener.getInstance(table));
        getContentPane().add(new JScrollPane(table));
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
