package ClientServerChat;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class EchoServer {
    private static ConcurrentMap<String, MySocket> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
            while (true) {
                MySocket s = ss.accept();
                new Thread(){
                    public void run() {
                        String nick = s.readLine();
                        clients.put(nick, s);
                        broadcast(nick + " ha entrat al xat");

                        String line;
                        while ((line = s.readLine()) != null) {
                            broadcast(nick + ": " + line);
                            s.println(line);
                        }

                        clients.remove(nick);
                        broadcast(nick + " ha sortit del xat");
                        s.close();
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcast(String message) {
        for (MySocket client : clients.values()) {
            client.println(message);
        }
    }
}

// java ClientServerChat.EchoServer 5000
