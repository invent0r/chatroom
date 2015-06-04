package be.howest.bockland.joachim.commands.clientLeft;

import be.howest.bockland.joachim.commands.CommandToServer;
import be.howest.bockland.joachim.commands.updateUsers.UpdateUsersToClient;
import be.howest.bockland.joachim.server.ClientConnection;
import be.howest.bockland.joachim.server.Server;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ClientLeftToServer implements CommandToServer {
    @Override
    public void performOnServer(Server associatedServer, ClientConnection originatingConnection) throws IOException {
        associatedServer.stopConnection(originatingConnection);
        List<String> names = associatedServer.getConnections().stream().map(ClientConnection::getNickname).collect(Collectors.toList());
        associatedServer.performAction(new ClientLeftToClient(names, originatingConnection.getNickname()));

        for(ClientConnection connection : associatedServer.getConnections()){
            connection.sendMessage(new ClientLeftToClient(names, originatingConnection.getNickname()));
        }
    }
}
