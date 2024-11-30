package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public class GameView extends JPanel implements Runnable{
    public static final int AMPLE = 1100;
    public static final int ALT = 800;
    final int FPS = 60;
    Thread gameThread;
    GameBoard board = new GameBoard();
    Ratoli ratoli = new Ratoli();

    // Peces
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    Piece peca_escollida;

    // Color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColor = WHITE;

    public GameView() {
        //com volem que aparegui
        setPreferredSize(new Dimension(AMPLE,ALT));
        setBackground(Color.black);
        addMouseListener(ratoli);
        addMouseMotionListener(ratoli);
        setPieces();
        copyPieces(pieces, simPieces);
    }
    public void launchGame(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPieces() {
        // Peces blanques
        pieces.add(new Pawn(WHITE, 0, 6));
        pieces.add(new Pawn(WHITE, 1, 6));
        pieces.add(new Pawn(WHITE, 2, 6));
        pieces.add(new Pawn(WHITE, 3, 6));
        pieces.add(new Pawn(WHITE, 4, 6));
        pieces.add(new Pawn(WHITE, 5, 6));
        pieces.add(new Pawn(WHITE, 6, 6));
        pieces.add(new Pawn(WHITE, 7, 6));
        pieces.add(new Rook(WHITE, 0, 7));
        pieces.add(new Knight(WHITE, 1, 7));
        pieces.add(new Bishop(WHITE, 2, 7));
        pieces.add(new Queen(WHITE, 3, 7));
        pieces.add(new King(WHITE, 4, 7));
        pieces.add(new Bishop(WHITE, 5, 7));
        pieces.add(new Knight(WHITE, 6, 7));
        pieces.add(new Rook(WHITE, 7, 7));
        
        // Peces negres
        pieces.add(new Pawn(BLACK, 0, 1));
        pieces.add(new Pawn(BLACK, 1, 1));
        pieces.add(new Pawn(BLACK, 2, 1));
        pieces.add(new Pawn(BLACK, 3, 1));
        pieces.add(new Pawn(BLACK, 4, 1));
        pieces.add(new Pawn(BLACK, 5, 1));
        pieces.add(new Pawn(BLACK, 6, 1));
        pieces.add(new Pawn(BLACK, 7, 1));
        pieces.add(new Rook(BLACK, 0, 0));
        pieces.add(new Knight(BLACK, 1, 0));
        pieces.add(new Bishop(BLACK, 2, 0));
        pieces.add(new Queen(BLACK, 3, 0));
        pieces.add(new King(BLACK, 4, 0));
        pieces.add(new Bishop(BLACK, 5, 0));
        pieces.add(new Knight(BLACK, 6, 0));
        pieces.add(new Rook(BLACK, 7, 0));
    }

    private void copyPieces(ArrayList<Piece> src, ArrayList<Piece> dst){
        dst.clear();
        for(int i = 0; i< src.size(); i++){
            dst.add(src.get(i));
        }
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
        //logica del ratolí
        if(ratoli.pulsado){
            //revisem si el jugador està o no agafant una peça
            if(peca_escollida==null){
                for(Piece piece : simPieces){
                    //saber quina peça estem escollint dividim la posició del ratolí entre els quadrats per obtenir la posició de la peça
                    if(piece.color == currentColor && piece.col==ratoli.x/GameBoard.SQUARE_SIZE && piece.row==ratoli.y/GameBoard.SQUARE_SIZE){

                        peca_escollida = piece;
                    }
                }

            }
            else{

                //que la peça segueixi la trajectoria
                movimentMouse();
            }

        }
         if (ratoli.pulsado == false){

            if(peca_escollida != null){
                  // Ajustar la posición de la pieza a la cuadrícula del tablero
        peca_escollida.col = ratoli.x / GameBoard.SQUARE_SIZE;
        peca_escollida.row = ratoli.y / GameBoard.SQUARE_SIZE;

        // Actualizar la posición visual de la pieza según la cuadrícula
       
                peca_escollida.update_poscio();
                peca_escollida = null;
            } 
        }
    }
    private void movimentMouse(){
        peca_escollida.x = ratoli.x- GameBoard.MITJ;
        peca_escollida.y = ratoli.y - GameBoard.MITJ;
        peca_escollida.col = peca_escollida.getCol(peca_escollida.x);
        peca_escollida.row = peca_escollida.getRow(peca_escollida.y);

    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gD = (Graphics2D)g;
        board.draw(gD);
        for (Piece p : simPieces) {
            p.draw(gD);
        }
      
    }
}
