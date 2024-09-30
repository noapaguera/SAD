import java.io.*;

public class EditableBufferedReader extends BufferedReader {

    public EditableBufferedReader(InputStreamReader in) {
        super(in);
    }
    
    public void setRaw() {
        try {
            ProcessBuilder pb = new ProcessBuilder("stty -echo raw < /dev/tty");
            pb.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unsetRaw() {
        try {
            ProcessBuilder pb = new ProcessBuilder("stty -echo cooked < /dev/tty");
            pb.inheritIO().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
