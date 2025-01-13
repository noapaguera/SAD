package ChatGrafic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) {
        MySocket socket = new MySocket(args[0], Integer.parseInt(args[1])); // host, port

        // Iniciar interfície gràfica
        ChatGUI chatGUI = new ChatGUI(socket);
        String clientName = chatGUI.getClientName();

        // Keyboard Thread
        new Thread() {
            public void run() {
                String line;
                BufferedReader kbd = new BufferedReader(new InputStreamReader(System.in));
                try {
                    while ((line = kbd.readLine()) != null) {
                        String message = clientName + ": " + line;
                        socket.println(line); // Enviar el missatge al servidor
                        chatGUI.addMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket.close();
            }
        }.start();

        // Console Thread (ara amb interfície)
        new Thread(() -> {
            String line;
            while ((line = socket.readLine()) != null) {
                chatGUI.addMessage(line);
            }
            socket.close();
        }).start();
    }
}

// java Client args[0] args[1]
// java ChatGrafic.Client localhost 5000