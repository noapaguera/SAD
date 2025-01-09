package ChatGrafic;

import java.io.*;
import java.net.*;

public class MySocket {
    private Socket s;
    private BufferedReader r;
    private PrintWriter w;

    public MySocket(String host, int port) {
        try {
            this.s = new Socket(host, port);
            this.r = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.w = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySocket(Socket socket) {
        try {
            this.s = socket;
            this.r = new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.w = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        try {
            return r.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void println(String msg) {
        w.println(msg);
    }

    public void close() {
        try {
            if (r != null) r.close();
            if (w != null) w.close();
            s.close();
            System.out.println("Socket tancat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
