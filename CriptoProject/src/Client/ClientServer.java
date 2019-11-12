package Client;

import RSA.rsaAlgorithm;
import inter.ClientInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientServer implements Runnable{
    private InputStreamReader inputSR;
    private BufferedReader bufferR;
    private String ServerResponse;
    private String des,mes;
    private rsaAlgorithm rsa;

    
    public ClientServer(Socket server, rsaAlgorithm rsa) throws IOException{
        inputSR = new InputStreamReader(server.getInputStream());
        bufferR = new BufferedReader(inputSR);
        this.rsa = rsa;
    }
    
    @Override
    public void run() {
        int st = 0;
        try{
            while(true){
            //imprime lo que los clientes envíen al servidor
            ServerResponse = bufferR.readLine();
            st = ServerResponse.indexOf(":")+1;
            if(st > 0){
                st = ServerResponse.indexOf(":")+1;
                des = ServerResponse.substring(st);//quita lo del user
                //DESENCRIPTADO
                ClientInterface.SetTextArea(rsa.Desencriptar(des));
                System.out.println(rsa.Desencriptar(des));
            }else{
                ClientInterface.SetTextArea(null);
                
            }
            }
        }catch(IOException r){
            try {
                bufferR.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
