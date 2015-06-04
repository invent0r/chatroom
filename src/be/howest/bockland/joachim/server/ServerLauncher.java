package be.howest.bockland.joachim.server;

import be.howest.bockland.joachim.server.gui.ConsoleFrame;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ServerLauncher {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server(1200);

        JFrame console = new ConsoleFrame(server);
        console.setSize(720,480);
        server.setConsole(console);
        server.startAccepting();

        System.out.println("Server launched!");
    }
}
