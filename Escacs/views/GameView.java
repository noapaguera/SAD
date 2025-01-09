package views;

import controller.GameController;
import controller.Ratoli;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import models.GameBoard;
import models.piece.*;

public class GameView extends JPanel {
    public static final int AMPLE = 800;
    public static final int ALT = 800;
    Ratoli ratoli = new Ratoli();
    public GameController gc;

    public GameView() {
        // com volem que aparegui
        addMouseListener(ratoli);
        addMouseMotionListener(ratoli);
        setPreferredSize(new Dimension(AMPLE, ALT));
        setBackground(Color.black);
        this.gc = new GameController(this, ratoli);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gD = (Graphics2D) g;
        gc.board.draw(gD);
        for (Piece p : gc.simPieces) {
            p.draw(gD);
        }
        if (gc.peca_escollida != null) {
            gD.setColor(Color.white);
            gD.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            gD.fillRect(gc.peca_escollida.col* GameBoard.SQUARE_SIZE, gc.peca_escollida.row*GameBoard.SQUARE_SIZE, GameBoard.SQUARE_SIZE, GameBoard.SQUARE_SIZE);
            gD.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            if (gc.potMoure) {
                gc.peca_escollida.draw(gD);
            }
        }

        if(gc.missatgeCanviTron){
            gD.setColor(new Color(0, 0, 0, 200)); // Fondo semi-transparent
            gD.fillRoundRect(AMPLE / 2 - 150, ALT / 2 - 50, 300, 100, 20, 20);

            gD.setColor(Color.white);
            gD.setFont(gD.getFont().deriveFont(24f));
            FontMetrics metrics = gD.getFontMetrics();
            int x = AMPLE / 2 - metrics.stringWidth(gc.tornMsg) / 2;
            int y = ALT / 2 + metrics.getHeight() / 4;
            gD.drawString(gc.tornMsg, x, y);
        }
    }

}
