package ClientServerChat;

import java.io.IOException;
import java.net.*;

public class MyServerSocket {
    
    private ServerSocket ss;

    public MyServerSocket(int port) {
        try {
            this.ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
