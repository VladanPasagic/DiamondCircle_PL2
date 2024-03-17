package org.unibl.etf.listener;


import org.unibl.etf.Main;
import org.unibl.etf.figure.Figure;
import org.unibl.etf.frame.MovementFrame;
import org.unibl.etf.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public final  class LabelListen extends MouseAdapter {

        private final Figure figure;
        private final Player player;

        public LabelListen(Player player, Figure figure)
        {
                this.figure = figure;
                this.player = player;
        }
        @Override
        public void mouseClicked(MouseEvent e){
                        try {
                                MovementFrame movementFrame = MovementFrame.getInstance(player, figure);
                                movementFrame.setAlwaysOnTop(true);
                                movementFrame.setDefaultCloseOperation(MovementFrame.DISPOSE_ON_CLOSE);
                        } catch (InterruptedException ex) {
                                Logger.getLogger(Main.getLoggerName()).log(Level.SEVERE, ex.fillInStackTrace().toString());
                        }

        }
}

