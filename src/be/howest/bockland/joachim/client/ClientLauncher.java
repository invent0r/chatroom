package be.howest.bockland.joachim.client;

import be.howest.bockland.joachim.client.gui.ChatFrame;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ClientLauncher {
    public static void main(String[] args) {
        Client client = new Client();
        (new Thread(client)).start();

        JFrame chatFrame = new ChatFrame(client);
        chatFrame.setSize(720,480);
        client.addObserver(chatFrame);

        chatFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {}

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    client.stopChat();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                chatFrame.dispose();
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {}

            @Override
            public void windowIconified(WindowEvent windowEvent) {}

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {}

            @Override
            public void windowActivated(WindowEvent windowEvent) {}

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {}
        });

        System.out.println("Client launched!");
    }
}
