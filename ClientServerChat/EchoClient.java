package ClientServerChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EchoClient {
    public static void main(String[] args) {
        MySocket s = new MySocket(args[0], Integer.parseInt(args[1])); // host, port
        
        // Keyboard thread
        new Thread() {
            public void run() {
                String line;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while ((line = kbd.readLine()) != null) {
                        s.println(line);
                    }
                    // close s for writing
                } catch (IOException e) {
                    e.printStackTrace();
                }
                s.close();
            }
        }.start();

        // Console thread
        new Thread() {
            public void run() {
                String line;
                while ((line = s.readLine()) != null) {
                        System.out.println(line);
                }
            }
        }.start();
    }
}

// java EchoClient args[0] args[1]
// java ClientServerChat.EchoClient localhost 5000