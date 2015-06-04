package be.howest.bockland.joachim.commands.clientLeft;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.commands.CommandToClient;

import java.util.List;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ClientLeftToClient implements CommandToClient {
    private List<String> names;
    private String name;

    public ClientLeftToClient(List<String> names, String name) {
        this.names = names;
        this.name = name;
    }

    @Override
    public void performOnClient(ChatGUI gui) {
        gui.updateParticipants(names);
        gui.addMessage("Server", name + " has left the room!");
    }
}
