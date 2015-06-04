package be.howest.bockland.joachim.commands.chatMessage;

import be.howest.bockland.joachim.commands.ChatGUI;
import be.howest.bockland.joachim.commands.CommandToClient;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ChatMessageToClient implements CommandToClient {
    private final String name;
    private final String contents;

    public ChatMessageToClient(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    @Override
    public void performOnClient(ChatGUI gui) {
        gui.addMessage(name, contents);
    }
}
