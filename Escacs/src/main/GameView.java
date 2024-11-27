package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GameView extends JPanel implements Runnable{
    public static final int AMPLE = 1100;
    public static final int ALT = 800;
    final int FPS = 60;
    Thread gameThread;
    GameBoard board = new GameBoard();

    public GameView() {

        //com volem que aparegui
        setPreferredSize(new Dimension(AMPLE,ALT));
        setBackground(Color.black);
        

    }
    public void launchGame(){

        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        //Game loop
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime -lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
        }


    }
    //fer updates de posició i peces que queden
    private void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gD = (Graphics2D)g;
        board.draw(gD);

    }
    
}
