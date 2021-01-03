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

    @Override
    public void run() {
        try {
        soc = new Socket("localhost", 4201);
        is = new InputStreamReader(soc.getInputStream());
        bfr = new BufferedReader(is);
        os = soc.getOutputStream();
        pw = new PrintWriter(os, true);
        pw.println("... Hello serveur ...");
        while(true){
            String receiveMessage = bfr.readLine();
            System.out.println("le serveur dit " + receiveMessage);
        }
    } catch (Exception e) {
        JOptionPane pane = new JOptionPane("erreur de connexion !!!!",JOptionPane.ERROR_MESSAGE);
        pane.show();
    }
    }

   /*
    {
        try {
            soc = new Socket("localhost", 4201);
            is = new InputStreamReader(soc.getInputStream());
            bfr = new BufferedReader(is);
            os = soc.getOutputStream();
            pw = new PrintWriter(os, true);
            pw.println("... Hello serveur ...");
            while(true){
                Thread.sleep(1000);
                String receiveMessage = bfr.readLine();
                System.out.println("le serveur dit " + receiveMessage);
                }
        } catch (Exception e) {
            JOptionPane pane = new JOptionPane("erreur de connexion !!!!",JOptionPane.ERROR_MESSAGE);
            pane.show();
        }
    }*/

    public void send(String sendMessage) {
        System.out.println("le message qui sera envoyé est "+sendMessage);
        pw.println(sendMessage);
    }

}

