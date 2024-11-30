package piece;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GameBoard;

public class Piece {
    public BufferedImage image;
    public int x,y;
    public int col, row, preCol, preRow; //fila i columna previa
    public int color;

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
        return col* GameBoard.SQUARE_SIZE;
    }
    public int getY(int row){
        return row* GameBoard.SQUARE_SIZE;
    }
    public int getCol(int x){
        //java posa les posicions adalt esquerra, per obtenir la posició restem mitja quadrat i tindrem el centre
        return (x+GameBoard.MITJ);
    }
    public int getRow(int y){

        return (y+GameBoard.MITJ);
    }
    public void update_poscio(){
        x = getX(col);
        y = getY(row);
        // només deixem que la peça es pugui posar al centra d'un requadre
        preCol = getCol(x);
        preRow = getRow(y);

    }
    public void draw(Graphics2D gD) {
        gD.drawImage(image, x, y, GameBoard.SQUARE_SIZE, GameBoard.SQUARE_SIZE, null);
    }
}
