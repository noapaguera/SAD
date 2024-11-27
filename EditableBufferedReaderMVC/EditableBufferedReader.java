package EditableBufferedReaderMVC;

import java.io.*;

public class EditableBufferedReader extends BufferedReader{

    static final int CR = 13;
    static final int ESC = 27;
    static final int BRACKET = 91;
    static final int RIGHT = 67; // C
    static final int LEFT = 68; // D
    static final int FIN = 70; // F
    static final int HOME = 72; // H
    static final int BACKSPACE = 127;
    static final int INSERT = 50;
    static final int DELETE = 51;
 
    public EditableBufferedReader(InputStreamReader in) {
        super(in);
    }
    
    public void setRaw() {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", "stty -echo raw < /dev/tty");
            pb.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unsetRaw() {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", "stty echo cooked < /dev/tty");
            pb.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Llegeix la línia amb possibilitat d'editar-la
    public String readLine() throws IOException {
        Line line = new Line();
        this.setRaw();
        int ch = this.read();
        while (ch != CR) {
            switch (ch) {
                case RIGHT:
                    line.moveRight();
                    break;
                case LEFT:
                    line.moveLeft();
                    break;
                case HOME:
                    line.home();
                    break;
                case FIN:
                    line.fin();
                    break;
                case BACKSPACE:
                    line.backspace();
                    break;
                case INSERT:
                    line.insert();
                    break;
                case DELETE:
                    ch = super.read();
                    line.delete();
                    break;
                default:
                    line.addChar((char) ch);
                    System.out.print((char) ch);
            }
            ch = this.read();
        }
        this.unsetRaw();
        line.home();
        return line.toString();
    }

}
