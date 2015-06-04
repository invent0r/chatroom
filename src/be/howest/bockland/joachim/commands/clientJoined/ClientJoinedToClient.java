package be.howest.bockland.joachim.commands.clientJoined;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.commands.CommandToClient;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ClientJoinedToClient implements CommandToClient {
    private final String name;

    public ClientJoinedToClient(String name) {
        this.name = name;
    }

    @Override
    public void performOnClient(ChatGUI gui) {
        gui.addMessage("Server", name + " has joined the room!");
        gui.addParticipant(name);
    }
}
