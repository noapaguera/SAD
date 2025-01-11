package ChatGrafic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

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
        // Finestra
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Área de missatges
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        frame.add(messageScrollPane, BorderLayout.CENTER);

        //Entrada de text
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        frame.add(inputField, BorderLayout.SOUTH);

        // nickname
        nickname = JOptionPane.showInputDialog(frame, "Introdueix el nom:");
        if (nickname != null && !nickname.trim().isEmpty()) {
            socket.println(nickname);
        } else {
            JOptionPane.showMessageDialog(frame, "El nom no pot estar buit! Tancant.");
            System.exit(0);
        }

        
        frame.setVisible(true);
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.trim().isEmpty()) {
            socket.println(nickname + ": " + message);
            inputField.setText("");
        }
    }
    // fer que el missatge es vegui en la interfície dels dos usuaris en el mateix servidor
    public void addMessage(String message) {
        messageArea.append(message + "\n");
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
