package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ratoli extends MouseAdapter {

    public int x, y; // posició
    public boolean pulsado; // per saber si el ratolí està premut

    @Override
    public void mousePressed(MouseEvent e) {
        pulsado = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pulsado = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // obtenim la posició previa;
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}
