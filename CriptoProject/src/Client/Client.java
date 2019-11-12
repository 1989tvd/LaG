/*Esta clase estable la conexión entre el cliente y el servidor*/
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import Client.ClientServer;

public class Client {
    private static final int SERVERPORT = 4999;
    private static final String SERVER_IP = "localhost";
    
    public void getClient() throws IOException{
        //Declaración de variables.
        Socket socketClient;
        ClientServer clientServer; 
        Scanner sc;
        String inputKeyboard;
        PrintWriter pw;
        InputStreamReader inputKey;
        BufferedReader buffKeyboard;
        String nombreUsuario=null;
        
        //Crea el socket para establecer la comunicación
        socketClient = new Socket(SERVER_IP, SERVERPORT);
        
        //establece la clase, en donde se lee lo que manda el servidor
        //clientServer = new ClientServer(socketClient);      

        //Por parte del cliente
        inputKey = new InputStreamReader(System.in);
        buffKeyboard = new BufferedReader(inputKey);

        //para enviar el mensaje
        pw = new PrintWriter(socketClient.getOutputStream());
               
        //envía el nombre de usuario al servidor
        pw.println(nombreUsuario);
        pw.flush();
        
        //se comienza el thread para el servidor envíe los mensajes al mismo tiempo
        //new Thread(clientServer).start();
        
        while(true){
            inputKeyboard = buffKeyboard.readLine(); //lo que escribe el cliente
            
            if(inputKeyboard.equals("exit")) break;//si quiere salir
            
            //LO QUE SE TIENE QUE ENCRIPTAR, LLAVE PRIVADA DEL CLIENTE
            pw.println(inputKeyboard);
            pw.flush();
        }
        socketClient.close();
    }
    
    public static void main(String[] args) throws IOException{
        //Declaración de variables.
        Socket socketClient;
        ClientServer clientServer; 
        Scanner sc;
        String inputKeyboard;
        PrintWriter pw;
        InputStreamReader inputKey;
        BufferedReader buffKeyboard;
        String nombreUsuario=null;
        
        //Crea el socket para establecer la comunicación
        socketClient = new Socket(SERVER_IP, SERVERPORT);
        
        System.out.println("Ingresa nombre de usuario");
        sc = new Scanner(System.in);
        nombreUsuario = sc.next();
 
        //establece la clase, en donde se lee lo que manda el servidor
        //clientServer = new ClientServer(socketClient);      

        //Por parte del cliente
        inputKey = new InputStreamReader(System.in);
        buffKeyboard = new BufferedReader(inputKey);

        //para enviar el mensaje
        pw = new PrintWriter(socketClient.getOutputStream());
               
        //envía el nombre de usuario al servidor
        pw.println(nombreUsuario);
        pw.flush();
        
        //se comienza el thread para el servidor envíe los mensajes al mismo tiempo
        //new Thread(clientServer).start();
        
        while(true){
            inputKeyboard = buffKeyboard.readLine(); //lo que escribe el cliente
            
            if(inputKeyboard.equals("exit")) break;//si quiere salir
            
            //LO QUE SE TIENE QUE ENCRIPTAR, LLAVE PRIVADA DEL CLIENTE
            pw.println(inputKeyboard);
            pw.flush();
        }
        socketClient.close();
    }   
}
