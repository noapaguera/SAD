package ChessGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class GameBoard {

    private final JPanel board = new JPanel(new BorderLayout(3, 3));
    private JButton[][] squares1 = new JButton[8][8];
    private JPanel board1, board2;
    JToolBar tool = new JToolBar();
    int squares = 8;
    int space = 100;
    ImageIcon icon = new ImageIcon(new BufferedImage(space, space, BufferedImage.TYPE_INT_ARGB));

    GameBoard() {
        initializeGui();
    }

    public final void initializeGui() {
        board.setBorder(new EmptyBorder(5, 5, 5, 5));
        tool.setFloatable(false);
        board.add(tool, BorderLayout.PAGE_START);
        board1 = new JPanel(new GridLayout(0, 8));
        board1.setBorder(new LineBorder(Color.BLACK));
        board.add(board1);

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                JButton b = new JButton();
                b.setIcon(icon);

                boolean isLightSquare = (r+c) % 2 != 0;
                if (isLightSquare) {
                    b.setBackground(Color.BLACK);
                } else {
                    b.setBackground(Color.WHITE);
                }
                squares1[c][r] = b;
            }
        }

        for (int i = 0; i < squares; i++) {
            for (int j = 0; j < squares; j++) {
                board1.add(squares1[j][i]);
            }
        }
    }

    public final JComponent getGui() {
        return board1;
    }

    public final JComponent getGui2() {
        return board2;
    }
}