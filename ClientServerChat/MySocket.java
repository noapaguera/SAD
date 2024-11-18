package ClientServerChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class MySocket {
    private Socket s;
    private BufferedReader r;
    private PrintWriter w;

    public MySocket(String host, int port) throws IOException {
        this.s = new Socket(host, port);
        this.r = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.w = new PrintWriter(s.getOutputStream(), true);    
    }

    public MySocket(Socket socket) throws IOException {
        this.s = socket;
        this.r = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.w = new PrintWriter(s.getOutputStream(), true);    
    }

    public String readLine() throws IOException {
        return r.readLine();
    }

    public void println(String msg) {
        w.println(msg);
    }

    public void close() throws IOException {
        r.close();
        w.close();
        s.close();
        System.out.println("Socket tancat");
    } 
}
