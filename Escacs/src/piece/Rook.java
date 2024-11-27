package piece;

import main.GameView;

public class Rook extends Piece{
    public Rook(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/w-rook");
        } else {
            image = getImage("/images/b-rook");
        }
    }
}
