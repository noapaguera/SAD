package ChessGame;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class Main {

    private static void createAndShowGUI() {
        GameBoard gb = new GameBoard();

        //Create and set up the window. Exit on close
        JFrame frame = new JFrame("Chess - Player 1");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //Add board
        frame.add(gb.getGui());
        
        //Display the window. set size and location
        frame.setSize(800, 800);
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setLocation(SwingConstants.RIGHT, SwingConstants.TOP);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}