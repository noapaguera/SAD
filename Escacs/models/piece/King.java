package models.piece;

import controller.GameController;

public class King extends Piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameController.WHITE) {
            image = getImage("/images/w-king.png");
        } else {
            image = getImage("/images/b-king.png");
        }
    }

    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 || // moviment en horitzontal i vertical
                Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) { // moviment en diagonal
                if (casellaValida(targetCol, targetRow)) return true;
            }
        }
        return false;
    }

    @Override
    public String getTypePiece(){
        return "King";
    }
}
