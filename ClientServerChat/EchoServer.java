package ClientServerChat;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EchoServer {
    private static ConcurrentHashMap<String, MySocket> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            MyServerSocket ss = new MyServerSocket(Integer.parseInt(args[0]));
            System.out.println("Servidor iniciat ");
            while (true) {
                MySocket s = ss.accept();
                new Thread() {
                    public void run() {
                        s.println("Introdueix el teu nick: ");
                        String nick = s.readLine();
                        clients.put(nick, s);
                        broadcast(nick, " ha entrat al xat");

                        String line;
                        while ((line = s.readLine()) != null) {
                            broadcast(nick, ": " + line);
//                            s.println(line);
                        }
                        clients.remove(nick);
                        broadcast(nick, " ha sortit del xat");
                        s.close();
                    }
                }.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcast(String nick, String message) {
    //    MySocket client= clients.get(nick);
        for (Map.Entry<String, MySocket> entry : clients.entrySet()) {
            String currentUser = entry.getKey();
            MySocket currentSocket = entry.getValue();
            if (currentUser != nick) {
                currentSocket.println(nick + message);
            }
        }
    }
}

// java ClientServerChat.EchoServer 5000
