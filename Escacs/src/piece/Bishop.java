package piece;

import main.GameView;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/piece/w-bishop");
        } else {
            image = getImage("/piece/b-bishop");
        }
    }
}
