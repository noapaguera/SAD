package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameBoard {
    final int MAX_COL = 8;
    final int MAX_ROW = 8;
    public static final int SQUARE_SIZE = 100;
    public static final int MITJ = SQUARE_SIZE/2;

    public void draw(Graphics2D gD){
        boolean isblack = false;
        for(int row =0; row < MAX_ROW; row++){

            for(int col = 0; col < MAX_COL; col++){
                if(isblack==false){
                    gD.setColor(new Color(210,165,125));
                    isblack = true;
                } else{
                    gD.setColor(new Color(175,115,70));
                    isblack = false;
                }
                //agafem les mides multiplicant les files i columnes per quadrat
                gD.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE,SQUARE_SIZE);
            }
            if(isblack==false){
                isblack = true;
            }else{
                isblack=false;
            }
        }
    }
}
