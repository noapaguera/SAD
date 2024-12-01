package piece;

import main.GameView;

public class Queen extends Piece{
    public Queen(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/piece/w-queen.png");
        } else {
            image = getImage("/images/piece/b-queen.png");
        }
    }
    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol,targetRow) && isSameSquare(targetCol, targetRow) == false) {
            // vertical i horitzontal
            if (targetCol == preCol || targetRow == preRow) {
                if (casellaValida(targetCol, targetRow) && pecaEnLiniaRecta(targetCol, targetRow) == false) {
                    return true;
                }
            }
            //diagonal 
            if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if (casellaValida(targetCol, targetRow) && pecaEnLiniaDiagonal(targetCol, targetRow) == false) {
                    return true;
                }
            }
        }
        return false;
    }
}
