package piece;

import main.GameView;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/piece/w-king.png");
        } else {
            image = getImage("/images/piece/b-king.png");
        }
    }
}
