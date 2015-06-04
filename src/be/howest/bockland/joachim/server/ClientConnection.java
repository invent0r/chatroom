package be.howest.bockland.joachim.server;

import be.howest.bockland.joachim.commands.CommandToClient;
import be.howest.bockland.joachim.commands.CommandToServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Joachim on 04/06/2015.
 */
public class ClientConnection extends Thread {
    private static int clientCounter = 0;

    private Socket socket;
    private Server associatedServer;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    private String nickname;

    public String getNickname(){
        return nickname;
    }


    public ClientConnection(Socket clientSocket, Server server) throws IOException {
        //Runs on main thread
        this.socket = clientSocket;
        this.associatedServer = server;
        nickname = "Anonymous " + (++clientCounter);
    }

    public void sendMessage(CommandToClient command) throws IOException{
        writer.writeObject(command);
        writer.flush();
    }

    @Override
    public void run()  {
        //This runs on the connection thread
        try{
            System.out.println("Accepted a connection.");

            writer = new ObjectOutputStream(socket.getOutputStream());
            writer.flush();
            reader = new ObjectInputStream(socket.getInputStream());

            Object msg;
            try {

                while((msg = reader.readObject()) != null){
                    CommandToServer cmdToServer = (CommandToServer) msg;
                    cmdToServer.performOnServer(associatedServer, this);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(IOException ioe){
            Logger.getLogger("ClientConnection").log(Level.SEVERE, ioe.getMessage());
        }
    }

}
