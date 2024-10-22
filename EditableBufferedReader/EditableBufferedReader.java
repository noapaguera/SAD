package EditableBufferedReader;

import java.io.IOException;
import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    // posar constants negatives
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
    
    public final Line linia;

    public EditableBufferedReader(InputStreamReader in) {
        super(in);
        linia = new Line();
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

    // Llegeix el següent caràcter o tecla especial
    /*  @Override
    public int read() throws IOException {
        int ch = 0;
        if (match("\033[H"))
            return Keys.HOME;
        if (match("\033[F"))
            return Keys.FIN;
        if (match("\033[C"))
            return Keys.RIGHT;
        if (match("\033[D"))
            return Keys.LEFT;
        if (match("\033[2~"))
            return Keys.INSERT;
        if (match("\033[3~"))
            return Keys.DELETE;
        if (match("^?"))
            return Keys.BACKSPACE;
        ch = super.read();
        return ch;
    }
    
    // Llegeix la línia amb possibilitat d'editar-la
    public String readLine() throws IOException {
        this.setRaw();
        int ch;
        while ((ch = this.read()) != Keys.CR_ASCII) {
            switch (ch) {
                case Keys.RIGHT:
                    linia.moveRight();
                    break;
                case Keys.LEFT:
                    linia.moveLeft();
                    break;
                case Keys.HOME:
                    linia.home();
                    break;
                case Keys.FIN:
                    linia.fin();
                    break;
                case Keys.BACKSPACE:
                    linia.backspace();
                    break;
                case Keys.INSERT:
                    linia.insert();
                    break;
                case Keys.DELETE:
                    linia.delete();
                    break;
                default:
                    linia.addChar((char) ch);
                    System.out.print((char) ch);
            }
        }
        this.unsetRaw();
        linia.home();
        return linia.toString();
    } */

    /* match (mètode de parsing (lèxic)) 
        retorna true i avança el cursor de lectura si reconeix el substring
        retorna false i NO avança
        1.- usar metodes de Reader
        2.- fer servir un prefix
    */
    /* private boolean match(String str) throws IOException {
        mark(str.length());
        for (char c : str.toCharArray()) {
            if ((char)super.read() != c) {
                reset();
                return false;
            }
        }
        return true;
    } */
    // Llegeix el següent caràcter o tecla especial
    public int read() throws IOException {
        int ch = 0;
        if ((ch = super.read()) == ESC) {
            if ((ch = super.read()) == BRACKET) {
                switch (ch = super.read()) {
                    case 'C':
                        return RIGHT; // Fletxa dreta ^[[C
                    case 'D':
                        return LEFT; // Fletxa esquerra ^[[D
                    case 'H':
                        return HOME; // Home ^[[H
                    case 'F':
                        return FIN; // End ^[[F
                    case '2':
                        ch = super.read();
                        return INSERT; // ^[[2~
                    case '3':
                        ch = super.read();
                        return DELETE; // ^[[3~
                    default:
                        break;
                }
            }
        } else if (ch == BACKSPACE) { // ^? Backspace
            return BACKSPACE;
        }
        return ch;
    }

    // Llegeix la línia amb possibilitat d'editar-la
    public String readLine() throws IOException {
        this.setRaw();
        int ch = this.read();
        while (ch != CR) {
            switch (ch) {
                case RIGHT:
                    linia.moveRight();
                    break;
                case LEFT:
                    linia.moveLeft();
                    break;
                case HOME:
                    linia.home();
                    break;
                case FIN:
                    linia.fin();
                    break;
                case BACKSPACE:
                    linia.backspace();
                    break;
                case INSERT:
                    linia.insert();
                    break;
                case DELETE:
                    linia.delete();
                    break;
                default:
                    linia.addChar((char) ch);
                    System.out.print((char) ch);
            }
            ch = this.read();
        }
        unsetRaw();
        linia.home();
        return linia.toString();
    } 
}