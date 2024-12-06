package piece;

import main.GameView;

public class Pawn extends Piece{
    public Pawn(int color, int col, int row) {
        super(color, col, row);
        
        if (color == GameView.WHITE) {
            image = getImage("/images/w-pawn.png");
        } else {
            image = getImage("/images/b-pawn.png");
        }
    }

    public boolean potMoure(int targetCol, int targetRow) {
        if (estaAlTauler(targetCol,targetRow) && isSameSquare(targetCol, targetRow) == false) {
            // definim el valor del moviment depenent del color
            int moveValue;
            if (color == GameView.WHITE) moveValue = -1;
            else moveValue = 1;

            // comprovem la peça amb la que xoquem
            hitPiece = getHitPiece(targetCol, targetRow);

            // moviment 1 casella
            if (targetCol == preCol && targetRow == preRow + moveValue && hitPiece == null) {
                return true;
            }

            // moviment diagonal i capturar si la peça esta en una casella diagonal davant seu
            if (Math.abs(targetCol - preCol) == 1 && targetRow == preRow + moveValue && hitPiece != null && hitPiece.color != color) {
                return true;
            }
        }
        return false;
    }
}
