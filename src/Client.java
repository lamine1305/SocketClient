import javax.swing.*;

import java.beans.PropertyChangeEvent;
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
    private  String[] listClient = {};
    private String messageDiscution;
    
    public String getMessageDiscution() {
		return messageDiscution;
	}

	public void setMessageDiscution(String messageDiscution) {
		this.messageDiscution = messageDiscution;
	}

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
        soc = new Socket("192.168.1.32", 4204);
        is = new InputStreamReader(soc.getInputStream());
        bfr = new BufferedReader(is);
        os = soc.getOutputStream();
        pw = new PrintWriter(os, true);
        //Envoi du nom du client vers le serveur
        pw.println("msg_id" + nomClient);

        while(true){
            receiveMessage = bfr.readLine();

            //Le message reçu est un message de liste de client
            if(receiveMessage.contains("##?##?##")) {
            this.getListClientConnecte(receiveMessage);
            } else {
            	//Le message est un message de discution envoyé par le serveur-->Il faut l'afficher dans la zone de texte
              messageDiscution = receiveMessage;
              
            }
        }
    } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur de connexion !!!! "+e.getMessage());

    }
    }

    public void send(String sendMessage) {
        //System.out.println("le message qui sera envoyÃ© est "+sendMessage);
        pw.println(sendMessage);
    }
    
    private void getListClientConnecte(String msg){
    	//Mise à jour de la liste des clients connectés -->Le modèle est mis à jour automatiquement pour rafraichir la liste de la vue EntreeSalonSwing
    	this.listClient = msg.split("##\\?##\\?##");
    
    }
    

}

