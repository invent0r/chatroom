package be.howest.bockland.joachim.server.gui;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.server.Server;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ConsoleFrame extends JFrame implements ChatGUI {
    private JButton sendMessage;
    private JTextField messageInput;
    private JPanel chatPanel;
    private JTextArea chatLog;
    private JTextArea chatList;
    private Server server;

    public ConsoleFrame(Server server) {
        super("server - Chatroom");
        setContentPane(chatPanel);
        pack();
        setVisible(true);

        this.server = server;
        sendMessage.addActionListener(actionEvent -> {
            try {
                server.sendMessage(messageInput.getText());
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
