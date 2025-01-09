package ChatGrafic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGUI {
    private JFrame frame;
    private JTextArea messageArea;
    private JTextField inputField;
    private MySocket socket;
    private String nickname;

    public ChatGUI(MySocket socket) {
        this.socket = socket;
        initialize();
        startListening();
    }

    private void initialize() {
        // Configurar la ventana principal
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Área de mensajes
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        frame.add(messageScrollPane, BorderLayout.CENTER);

        // Campo de entrada de texto
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        frame.add(inputField, BorderLayout.SOUTH);

        // Solicitar el apodo del usuario
        nickname = JOptionPane.showInputDialog(frame, "Introduce tu apodo:");
        if (nickname != null && !nickname.trim().isEmpty()) {
            socket.println(nickname);
        } else {
            JOptionPane.showMessageDialog(frame, "El apodo no puede estar vacío. Cerrando la aplicación.");
            System.exit(0);
        }

        // Hacer visible la ventana
        frame.setVisible(true);
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.trim().isEmpty()) {
            socket.println(nickname + ": " + message);
            inputField.setText("");
        }
    }

    private void startListening() {
        new Thread(() -> {
            String line;
            while ((line = socket.readLine()) != null) {
                messageArea.append(line + "\n");
            }
        }).start();
    }
}
