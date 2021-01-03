import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private static String nomClient;
    private Socket soc = null;
    private InputStreamReader is = null;
    private BufferedReader bfr = null;
    private OutputStream os = null;
    private PrintWriter pw = null;

    public static String getNomClient() {
        return nomClient;
    }

    public static void setNomClient(String nomClient) {
        Client.nomClient = nomClient;
    }

    @Override
    public void run() {
        try {
        soc = new Socket("localhost", 4201);
        is = new InputStreamReader(soc.getInputStream());
        bfr = new BufferedReader(is);
        os = soc.getOutputStream();
        pw = new PrintWriter(os, true);
        pw.println("***... Hello serveur ...***");
        while(true){
            String receiveMessage = bfr.readLine();
            System.out.println("le serveur dit " + receiveMessage);
        }
    } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur de connexion !!!! ");

    }
    }

    public void send(String sendMessage) {
        System.out.println("le message qui sera envoyé est "+sendMessage);
        pw.println(sendMessage);
    }

}

