package ChatGrafic;

import java.io.IOException;
import java.net.*;

public class MyServerSocket {

    private ServerSocket ss;

    public MyServerSocket(int port) throws IOException {
        this.ss = new ServerSocket(port);
    }

    public MySocket accept() {
        try {
            System.out.println("Socket acceptat");
            return new MySocket(ss.accept());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            ss.close();
            System.out.println("S'ha tancat el servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
