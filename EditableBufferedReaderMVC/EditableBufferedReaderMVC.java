import java.io.*;

public class EditableBufferedReaderMVC extends EditableBufferedReader {

    public EditableBufferedReaderMVC(InputStreamReader in) {
        super(in);
    }
    
    // Llegeix la línia amb possibilitat d'editar-la
    public String readLine() throws IOException {
        LineMVC line = new LineMVC();
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