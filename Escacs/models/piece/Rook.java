package models.piece;

import controller.GameController;

public class Rook extends Piece{
    public Rook(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameController.WHITE) {
            image = getImage("/images/w-rook.png");
        } else {
            image = getImage("/images/b-rook.png");
        }
    }
    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol,targetRow) && isSameSquare(targetCol, targetRow) == false) {
            // la torre es pot moure en vertical o horitzontal
            if (targetCol == preCol || targetRow == preRow) {
                if (casellaValida(targetCol, targetRow) && pecaEnLiniaRecta(targetCol, targetRow) == false) {
                    return true;
                }
            }
        }
        return false;
    }
}
