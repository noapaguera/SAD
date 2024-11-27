package piece;

import main.GameView;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/w-king");
        } else {
            image = getImage("/images/b-king");
        }
    }
}
