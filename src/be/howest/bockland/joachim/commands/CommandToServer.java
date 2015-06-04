/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.bockland.joachim.commands;

import be.howest.bockland.joachim.server.ClientConnection;
import be.howest.bockland.joachim.server.Server;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Laurens
 */
public interface CommandToServer extends Serializable {
    public void performOnServer(Server associatedServer, ClientConnection originatingConnection) throws IOException;
}
