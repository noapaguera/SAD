package piece;

import main.GameView;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/w-pawn");
        } else {
            image = getImage("/images/b-pawn");
        }
    }
}
