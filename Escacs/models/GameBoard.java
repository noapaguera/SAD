package models;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameBoard {
    final int MAX_COL = 8;
    final int MAX_ROW = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int MITJ = SQUARE_SIZE/2;

    public void draw(Graphics2D gD){
        for(int row =0; row < MAX_ROW; row++){
            for(int col = 0; col < MAX_COL; col++){
                boolean isLightSquare = (row+col) % 2 != 0;
                if (isLightSquare) {
                    gD.setColor(new Color(175,115,70));
                } else {
                    gD.setColor(new Color(210,165,125));
                }
                //agafem les mides multiplicant les files i columnes per quadrat
                gD.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE,SQUARE_SIZE);
            }
        }
    }
}
