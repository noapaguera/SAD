package models.piece;

import controller.GameController;

public class Knight extends Piece{
    public Knight(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameController.WHITE) {
            image = getImage("/images/w-knight.png");
        } else {
            image = getImage("/images/b-knight.png");
        }
    }
    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol, targetRow)) {
            if (Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 2) {
                if (casellaValida(targetCol, targetRow)) {
                    return true;
                }
            }
        }
        return false;
    }
}
