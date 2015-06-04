package be.howest.bockland.joachim.client.gui;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.client.Client;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ChatFrame extends JFrame implements ChatGUI {
    private JButton sendMessage;
    private JTextField messageInput;
    private JPanel chatPanel;
    private JTextArea chatLog;
    private JTextArea chatList;
    private Client client;

    public ChatFrame(Client client) {
        super("Chatroom");
        setContentPane(chatPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.client = client;

        sendMessage.addActionListener(actionEvent -> {
            try {
                client.sendMessage(messageInput.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageInput.setText("");
        });
    }

    @Override
    public void addParticipant(String name) {
            chatList.append(name + "\r\n");
    }

    @Override
    public void updateParticipants(List<String> names) {
        chatList.setText("");
        for(String name : names) {
            chatList.append(name + "\r\n");
        }
    }

    @Override
    public void addMessage(String sender, String contents) {
        chatLog.append(sender + ": " + contents + "\r\n");
    }
}
