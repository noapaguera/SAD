package piece;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GameBoard;
import main.GameView;

public class Piece {
    public BufferedImage image;
    public int x,y;
    public int col, row, preCol, preRow; //fila i columna previa
    public int color;
    public Piece hitPiece;

    public Piece(int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    public BufferedImage getImage(String imgPath) {
        BufferedImage i = null;
        try {
            i = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }
    //passant columna i fila podem saber on està la peça multipliquem el quadradet per n fila/col
    public int getX(int col){
        return col*GameBoard.SQUARE_SIZE;
    }
    public int getY(int row){
        return row*GameBoard.SQUARE_SIZE;
    }
    public int getCol(int x){
        //java posa les posicions adalt esquerra, per obtenir la posició restem mitja quadrat i tindrem el centre
        return (x + GameBoard.MITJ)/GameBoard.SQUARE_SIZE;
    }
    public int getRow(int y){
        return (y + GameBoard.MITJ)/GameBoard.SQUARE_SIZE;
    }
    public int getIndex() {
        for (int index = 0; index < GameView.simPieces.size(); ++index) {
            if (GameView.simPieces.get(index) == this) {
                return index;
            } 
        }
        return 0;
    }
    public void update_posicio(){
        x = getX(col);
        y = getY(row);
        // només deixem que la peça es pugui posar al centre d'un requadre
        preCol = getCol(x);
        preRow = getRow(y);
    }

    public void resetPosicio() {
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    public boolean potMoure(int targetCol, int targetRow) {
        return false;
    }

    public boolean estaAlTauler(int targetCol, int targetRow) {
        if (targetCol >= 0 && targetCol <=7 && targetRow >= 0 && targetRow <=7) {
            return true;
        }
        return false;
    }

    public boolean isSameSquare(int targetCol, int targetRow) {
        if (targetCol == preCol && targetRow == preRow) return true;
        return false;
    }

    public Piece getHitPiece(int targetCol, int targetRow) {
        for (Piece piece : GameView.simPieces) {
            if (piece.col == targetCol && piece.row == targetRow && piece != this) {
                return piece;
            }
        }
        return null;
    }

    public boolean casellaValida(int targetCol, int targetRow) {
        hitPiece = getHitPiece(targetCol, targetRow);
        // si la casella no esta ocupada
        if (hitPiece == null) {
            return true;
        } else { // casella ocupada
            if (hitPiece.color != this.color) { // peça enemiga
                return true;
            } else {
                hitPiece = null;
            }
        }
        return false;
    }

    public void draw(Graphics2D gD) {
        gD.drawImage(image, x, y, GameBoard.SQUARE_SIZE, GameBoard.SQUARE_SIZE, null);
    }
}
