import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client {
    private static String nomClient;
    private Socket soc = null;
    private InputStreamReader is = null;
    private BufferedReader bfr = null;
    private OutputStream os = null;
    private PrintWriter pw = null;

    {
        try {
            soc = new Socket("localhost", 4201);
            is = new InputStreamReader(soc.getInputStream());
            bfr = new BufferedReader(is);
            os = soc.getOutputStream();
            pw = new PrintWriter(os, true);
            pw.println("Je suis le client Numéro 1");

        } catch (Exception e) {
            JOptionPane pane = new JOptionPane("erreur de connexion !!!!",JOptionPane.ERROR_MESSAGE);
            pane.show();
        }
    }

    public void send(String sendMessage) {
        System.out.println("le message qui sera envoyé est ");
        pw.println(sendMessage);
    }

}

