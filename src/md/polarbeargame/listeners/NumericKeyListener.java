package md.polarbeargame.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import md.polarbeargame.exceptions.ExceptionCodes;
import md.shared.Utility;

public class NumericKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        try {
            char key = e.getKeyChar();
            if (key < '0' || key > '9') {
                e.consume();
            }
        } catch (Exception ex) {
            Utility.handleUnexpectedException(ExceptionCodes.NKR1, ex, true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //

    }

}
