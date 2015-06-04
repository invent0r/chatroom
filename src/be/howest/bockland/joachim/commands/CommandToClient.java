/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.bockland.joachim.commands;

import be.howest.bockland.joachim.commands.ChatGUI;

import java.io.Serializable;

/**
 *
 * @author Laurens
 */
public interface CommandToClient extends Serializable {
    public void performOnClient(ChatGUI gui);
}
