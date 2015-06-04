package be.howest.bockland.joachim.client;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.commands.chatMessage.ChatMessageToServer;
import be.howest.bockland.joachim.commands.CommandToClient;
import be.howest.bockland.joachim.commands.clientJoined.ClientJoinedToServer;
import be.howest.bockland.joachim.commands.clientLeft.ClientLeftToServer;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.interrupted;

/**
 * Created by Joachim on 04/06/2015.
 */
public class Client implements Runnable {
    private List<ChatGUI> observers;
    private ObjectInputStream inputReader;
    private ObjectOutputStream outputWriter;

    public Client() {
        observers = new ArrayList<>();
    }

    public void addObserver(JFrame chatFrame) {
        observers.add((ChatGUI) chatFrame);
    }

    public void waitForAction() throws IOException {
        CommandToClient command = null;
        try {
            command = (CommandToClient) inputReader.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(ChatGUI gui : observers){
            command.performOnClient(gui);
        }
    }

    public void sendMessage(String message) throws IOException {
        outputWriter.writeObject(new ChatMessageToServer(message));
        outputWriter.flush();
    }

    @Override
    public void run() {
        init();
    }

    public void init() {
        try {
            Socket clientSocket = new Socket("127.0.0.1", 1200);

            outputWriter = new ObjectOutputStream(clientSocket.getOutputStream());
            outputWriter.flush();

            inputReader = new ObjectInputStream(clientSocket.getInputStream());

            outputWriter.writeObject(new ClientJoinedToServer());
            while (!interrupted()) {
                waitForAction();
            }
        } catch (IOException ioex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ioex.getMessage());
        }
    }

    public void stopChat() throws IOException {
        outputWriter.writeObject(new ClientLeftToServer());
    }
}
