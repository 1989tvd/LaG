package Client;

import RSA.rsaAlgorithm;
import inter.ClientInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private ArrayList<ClientHandler> clientHandler;
    private PrintWriter pw;
    private InputStreamReader inputClient;
    private BufferedReader bufferClient;
    String clientRequest;
    public String user;
    public String publicKey;
    private BigInteger privateKey;
    boolean mandar = false;
    
    public ClientHandler(Socket socketClient, ArrayList<ClientHandler> clientHandler) throws IOException{
        this.clientHandler = clientHandler;
        pw = new PrintWriter(socketClient.getOutputStream());
        inputClient = new InputStreamReader(socketClient.getInputStream());
        bufferClient = new BufferedReader(inputClient);
    }
    
    @Override
    public void run(){
        try{
            user = bufferClient.readLine(); //agarra el usuario (username)
            publicKey = bufferClient.readLine();//agarra la llave
            
            do{TuPublicKey();}while(!mandar);
            while(true){
                //DESENCRIPTADO
                clientRequest = bufferClient.readLine(); //lo que mandó el usuario, que está encriptado
                if(clientRequest.equals("exit")) {break;}
                EveryoneWillKnow(clientRequest);
            }
        }catch(NullPointerException ei){
            pw.close();
            try {
                bufferClient.close();
                bufferClient.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(IOException e){
            pw.close();
            try {
                bufferClient.close();
                bufferClient.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //método para que se envíe el mensaje a todos
    public void EveryoneWillKnow(String message){
        for(ClientHandler a : clientHandler){
            if(user != a.user){
                a.pw.println(user + ":" + message);
                a.pw.flush();
            }else{
                a.pw.println("");
                a.pw.flush();
            }
        }
    }
    
    public void TuPublicKey() throws InterruptedException{
        for(ClientHandler a : clientHandler){
            if(user != a.user && a.publicKey!=null){
                pw.println(a.publicKey);
                pw.flush();
                mandar = true;
                break;
            }
        }
        
    }
   

}
