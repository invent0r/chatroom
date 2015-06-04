package be.howest.bockland.joachim.commands.updateUsers;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.commands.CommandToClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joachim on 04/06/2015.
 */
public class UpdateUsersToClient implements CommandToClient {
    private List<String> names = new ArrayList<>();

    public UpdateUsersToClient(List<String> names) {
        this.names = names;
    }

    @Override
    public void performOnClient(ChatGUI gui) {
        gui.updateParticipants(names);
    }
}
