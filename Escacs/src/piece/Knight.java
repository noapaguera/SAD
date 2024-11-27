package piece;

import main.GameView;

public class Knight extends Piece{
    public Knight(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/w-knight");
        } else {
            image = getImage("/images/b-knight");
        }
    }
}
