package piece;

import main.GameView;

public class Rook extends Piece{
    public Rook(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/piece/w-rook.png");
        } else {
            image = getImage("/images/piece/b-rook.png");
        }
    }
    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol,targetRow) && isSameSquare(targetCol, targetRow) == false) {
            // la torre es pot moure en vertical o horitzontal
            if (targetCol == preCol || targetRow == preRow) {
                if (casellaValida(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
