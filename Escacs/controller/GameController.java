package controller;

import models.GameBoard;
import models.piece.*;
import views.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class GameController implements Runnable{
    final int FPS = 60;
    Thread gameThread;
    Ratoli ratoli;
    public GameBoard board = new GameBoard();
    GameView gw;


    // Color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int END_GAME = 2;
    public int currentColor = WHITE;

    public boolean potMoure;
    public boolean casellaValida;

    //missatge canvi de torn
    public String tornMsg = "";
    public boolean missatgeCanviTron = false;

    // Peces
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simPieces = new ArrayList<>();
    public Piece peca_escollida;

    public GameController(GameView gw, Ratoli ratoli) {
        this.ratoli = ratoli;
        this.gw = gw;
        setPieces();
        copyPieces(pieces, simPieces);
    }

    public void copyPieces(ArrayList<Piece> src, ArrayList<Piece> dst) {
        dst.clear();
        for (int i = 0; i < src.size(); i++) {
            dst.add(src.get(i));
        }
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

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // Game loop
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                gw.repaint();
                delta--;
            }
        }
    }

    // fer updates de posició i peces que queden
    public void update() {
        // logica del ratolí
        if (ratoli.pulsado) {
            // revisem si el jugador està o no agafant una peça
            if (peca_escollida == null) {
                for (Piece piece : simPieces) {
                    // saber quina peça estem escollint dividim la posició del ratolí entre els
                    // quadrats per obtenir la posició de la peça
                    if (piece.color == currentColor && piece.col == ratoli.x / GameBoard.SQUARE_SIZE
                            && piece.row == ratoli.y / GameBoard.SQUARE_SIZE) {
                        peca_escollida = piece;
                    }
                }
            } else {
                // que la peça segueixi la trajectoria
                movimentMouse();
            }
        }
        if (!ratoli.pulsado) {
            if (peca_escollida != null) {
                if (casellaValida) {
                    // Moviment confirmat
                    // Actualizar la posició visual de la peça segons la quadrícula
                    // i la llista de peces en cas que s'hagi capturat
                    copyPieces(simPieces, pieces);
                    peca_escollida.update_posicio();
                    changeTurn();
                } else {
                    // moviment no valid, fem reset
                    copyPieces(pieces, simPieces);
                    peca_escollida.resetPosicio();
                    peca_escollida = null;
                }
                // Ajustar la posició de la peça a la quadrícula del tauler
                /* peca_escollida.col = ratoli.x / GameBoard.SQUARE_SIZE;
                peca_escollida.row = ratoli.y / GameBoard.SQUARE_SIZE; */
            }
        }
    }

    private void movimentMouse() {
        potMoure = false;
        casellaValida = false;

        // Reset llista de peces en cada iteració
        copyPieces(pieces, simPieces);
        peca_escollida.x = ratoli.x - GameBoard.MITJ;
        peca_escollida.y = ratoli.y - GameBoard.MITJ;
        peca_escollida.col = peca_escollida.getCol(peca_escollida.x);
        peca_escollida.row = peca_escollida.getRow(peca_escollida.y);

        // comprovar si la peça es pot moure a una casella valida
        if (peca_escollida.potMoure(peca_escollida.col, peca_escollida.row)) {
            potMoure = true;
            if (peca_escollida.hitPiece != null) {
                simPieces.remove(peca_escollida.hitPiece.getIndex());
            }
            casellaValida = true;
        }
    }

    public void changeTurn() {
        int state = checkEndGame();
        int timeMsg = 2000;
        if (state == END_GAME){
            currentColor = END_GAME;
            tornMsg = "PARTIDA ACABADA";
            timeMsg = 9999999;
        } else if (currentColor == WHITE) {
            currentColor = BLACK;
            tornMsg = "Torn negres";
        } else if(currentColor == BLACK) {
            currentColor = WHITE;
            tornMsg = "Torn Blanques";
        }
        peca_escollida = null;
        missatgeCanviTron = true;
        Timer timer = new Timer(timeMsg, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                missatgeCanviTron = false;
                gw.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();

        gw.repaint();

    }

    public int checkEndGame(){
        int countKing = 0;
        for (Piece piece  : pieces) {
            if ("King".equals(piece.getTypePiece())) {
                if(piece.color == WHITE) countKing = countKing + 1;
                else countKing = countKing - 1 ;
            }
        }
        if(countKing != 0){
            return END_GAME;
        }
        return 0;
    }

}
