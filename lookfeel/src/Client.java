import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client extends Thread {
    private static String nomClient;
    private Socket soc = null;
    private InputStreamReader is = null;
    private BufferedReader bfr = null;
    private OutputStream os = null;
    private PrintWriter pw = null;
    private  String receiveMessage ; 
    private  String[] listClient = new String[100];
    
    
    public String[] getListClient() {
		return listClient;
	}

	public String getReceiveMessage() {
		return receiveMessage;
	}

	public static String getNomClient() {
        return nomClient;
    }

    public static void setNomClient(String nomClient) {
        Client.nomClient = nomClient;
    }
    
    

    @Override
    public void run() {
        try {
        soc = new Socket("localhost", 4204);
        is = new InputStreamReader(soc.getInputStream());
        bfr = new BufferedReader(is);
        os = soc.getOutputStream();
        pw = new PrintWriter(os, true);
        pw.println(this.getNomClient());
        receiveMessage = bfr.readLine();
        System.out.println("le serveur dit " + receiveMessage);
        /****************************/
        //Le second message envoyé par le serveur est la liste des clients 
        receiveMessage = bfr.readLine();
        System.out.println("MESSAGE CONTENANT LES NOMS" + receiveMessage);
        this.listClientConnecte(receiveMessage);
        /****************************/
        
        while(true){
            receiveMessage = bfr.readLine();
            System.out.println("le serveur dit " + receiveMessage);
        }
    } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur de connexion !!!! ");

    }
    }

    public void send(String sendMessage) {
        //System.out.println("le message qui sera envoyÃ© est "+sendMessage);
        pw.println(sendMessage);
    }
    
    private void listClientConnecte(String msg){
    	this.listClient = msg.split("##");
    	for(int i=0;i<100;i++)
    	System.out.println(this.listClient[i]);
    }

}

