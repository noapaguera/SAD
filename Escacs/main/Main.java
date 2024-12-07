package main;
import javax.swing.JFrame;
import views.GameView;


public class Main {
    public static void main(String[] args) {
        
        JFrame window = new JFrame("Joc Escacs");
        //tanquem el programa si tanquem la finestra
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //no permetre canviar de mida la finestra
        window.setResizable(false);
        //afegim el View
        GameView v =  new GameView();
        window.add(v);
        //ajusta la mida al Game view
        window.pack();
        //mostrem la finestra al centre
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        v.gc.launchGame();
    }
}
