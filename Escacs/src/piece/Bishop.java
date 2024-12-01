package piece;

import main.GameView;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/piece/w-bishop.png");
        } else {
            image = getImage("/images/piece/b-bishop.png");
        }
    }
    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol,targetRow)) {
            
        }
        return false;
    }
}
