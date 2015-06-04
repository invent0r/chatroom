package be.howest.bockland.joachim.commands.chatMessage;

import be.howest.bockland.joachim.commands.CommandToServer;
import be.howest.bockland.joachim.server.ClientConnection;
import be.howest.bockland.joachim.server.Server;

import java.io.IOException;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ChatMessageToServer implements CommandToServer {
    private final String contents;

    public ChatMessageToServer(String contents){
        this.contents = contents;
    }

    @Override
    public void performOnServer(Server associatedServer, ClientConnection originatingConnection) throws IOException {
        associatedServer.performAction(new ChatMessageToClient(originatingConnection.getNickname(), contents));
        for(ClientConnection connection : associatedServer.getConnections()){
            connection.sendMessage(new ChatMessageToClient(originatingConnection.getNickname(),contents));
        }
    }
}
