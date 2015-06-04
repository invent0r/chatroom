package be.howest.bockland.joachim.commands.clientJoined;

import be.howest.bockland.joachim.commands.CommandToServer;
import be.howest.bockland.joachim.commands.chatMessage.ChatMessageToClient;
import be.howest.bockland.joachim.server.ClientConnection;
import be.howest.bockland.joachim.server.Server;

import java.io.IOException;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ClientJoinedToServer implements CommandToServer {

    @Override
    public void performOnServer(Server associatedServer, ClientConnection originatingConnection) throws IOException {
        associatedServer.performAction(new ClientJoinedToClient(originatingConnection.getNickname()));
        for(ClientConnection connection : associatedServer.getConnections()){
            connection.sendMessage(new ClientJoinedToClient(originatingConnection.getNickname()));
        }
    }
}
