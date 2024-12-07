package models.piece;

import controller.GameController;

public class Bishop extends Piece{
    public Bishop(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameController.WHITE) {
            image = getImage("/images/w-bishop.png");
        } else {
            image = getImage("/images/b-bishop.png");
        }
    }
    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol,targetRow) && isSameSquare(targetCol, targetRow) == false) {
            if (Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
                if (casellaValida(targetCol, targetRow) && pecaEnLiniaDiagonal(targetCol, targetRow) == false) {
                    return true;
                }
            }
        }
        return false;
    }
}
