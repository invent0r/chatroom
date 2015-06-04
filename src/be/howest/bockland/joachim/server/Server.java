package be.howest.bockland.joachim.server;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.commands.chatMessage.ChatMessageToClient;
import be.howest.bockland.joachim.commands.CommandToClient;
import be.howest.bockland.joachim.commands.updateUsers.UpdateUsersToClient;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joachim on 04/06/2015.
 */
public class Server {
    private ServerSocket chatSocket;
    private int port;

    private ChatGUI console;
    private List<ClientConnection> connections;

    public Server(int port) throws IOException {
        connections = new ArrayList<>();

        this.port = port;
        chatSocket = new ServerSocket(port);
    }

    public void startAccepting() throws IOException, InterruptedException {
        while(true){
            Socket clientSocket = chatSocket.accept();

            ClientConnection connection = (new ClientConnection(clientSocket, this));
            connections.add(connection);
            connection.start();
            Thread.sleep(1000);
            connection.sendMessage(new UpdateUsersToClient(connections.stream().map(ClientConnection::getNickname).collect(Collectors.toList())));
        }
    }

    public void sendMessage(String message) throws IOException {
        performAction(new ChatMessageToClient("Server",message));
        for(ClientConnection connection : connections){
            connection.sendMessage(new ChatMessageToClient("Server",message));
        }
    }

    public void setConsole(JFrame console) {
        this.console = (ChatGUI) console;
    }

    public List<ClientConnection> getConnections() {
        return connections;
    }

    public void performAction(CommandToClient command) {
        command.performOnClient(console);
    }

    public void stopConnection(ClientConnection connection) {
        connections.remove(connection);
        connection.interrupt();
    }
}
