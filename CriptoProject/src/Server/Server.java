package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import Client.ClientHandler;
import RSA.rsaAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.newFixedThreadPool;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static final int PORTNUMBER = 4999;
    private static ArrayList<ClientHandler> clientHandler = new ArrayList<>();
    private static ExecutorService ex = newFixedThreadPool(5); //conexiones que serán respondidas(1)
            
    public static void main(String[] args) throws IOException {
        String inputKeyboard; 
        PrintWriter pw = null;
        InputStreamReader inputKey;
        BufferedReader buffKeyboard = null;
        ServerSocket serverListenner = new ServerSocket(PORTNUMBER); //Establishing the port of the server
        
        while(!ex.isTerminated()){
            System.out.println("Esperando...");
            Socket socketClient = serverListenner.accept(); //Accepting client
            ClientHandler ch = new ClientHandler(socketClient, clientHandler); 
            clientHandler.add(ch);
            ex.execute(ch);
        }
       
        serverListenner.close();
    }
}
