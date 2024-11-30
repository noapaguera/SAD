package piece;

import main.GameView;

public class Queen extends Piece{
    public Queen(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/piece/w-queen");
        } else {
            image = getImage("/piece/b-queen");
        }
    }
}
