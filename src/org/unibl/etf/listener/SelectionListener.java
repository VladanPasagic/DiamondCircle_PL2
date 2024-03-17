package org.unibl.etf.listener;

import org.unibl.etf.Main;
import org.unibl.etf.frame.TextFileContentFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SelectionListener implements ListSelectionListener {

    private final JTable table;

    private final static SelectionListener instance = null;
    private SelectionListener(JTable table)
    {
        this.table = table;
    }

    public static SelectionListener getInstance(JTable table)
    {
        return Objects.requireNonNullElseGet(instance, () -> new SelectionListener(table));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String fileName = table.getValueAt(table.getSelectedRow(), 0).toString();
        if (e.getValueIsAdjusting()) {
            try {
                new TextFileContentFrame(fileName);
            } catch (IOException ex) {
                Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
            }

        }
    }
}
