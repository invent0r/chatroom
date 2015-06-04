package be.howest.bockland.joachim.commands;

import java.util.List;

/**
 * Created by Joachim on 04/06/2015.
 */
public interface ChatGUI {
    void addParticipant(String name);
    void updateParticipants(List<String> names);
    void addMessage(String sender, String contents);
}
