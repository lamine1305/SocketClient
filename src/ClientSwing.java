import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ClientSwing extends JFrame {

    JButton btn;
    TextArea ta;
    TextArea taSisir;

    String demandeur;
    String recepteur;
    String textEnvoye="";
    Boolean send= false;
    Client client = new Client();
    public ClientSwing(String userName,String nomInterlocuteur) {
        super("Messenger " +userName +" discution avec " +nomInterlocuteur);
        this.demandeur = userName;
        this.recepteur = nomInterlocuteur;
        this.setSize(550,480);
        client.start();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ta = new TextArea();
        ta.setForeground(Color.BLUE);
        taSisir = new TextArea();
        taSisir.setPreferredSize(new Dimension(0,60));
        btn = new JButton("Envoyer");
        btn.setSize(20,30);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-550)/2,(screenSize.height-480)/2);
        JPanel panelHaut = new JPanel(new BorderLayout());
        panelHaut.add(ta, BorderLayout.CENTER);
        panelHaut.setBackground(Color.orange);
        JPanel panelBas = new JPanel(new BorderLayout());
        panelBas.setBackground(Color.red);
        panelBas.add(taSisir, BorderLayout.CENTER);
        panelBas.add(btn, BorderLayout.EAST);
        this.setLayout(new BorderLayout());
        this.add(panelHaut, BorderLayout.CENTER);
        this.add(panelBas, BorderLayout.SOUTH);
        this.setVisible(true);
        this.btn.addActionListener(x->{
            if(!this.taSisir.getText().isEmpty()) {
                this.send = false;
                this.ta.setText(this.ta.getText() + '\n' +this.demandeur +" : " +this.taSisir.getText());
                textEnvoye ="<<<"+this.demandeur +">>> , "+"<<<"+this.recepteur+">>>" +this.taSisir.getText();

                client.send(textEnvoye);
                this.taSisir.setText("");
            }
        });
    }
/*
    public static void main(String[] args) {
        ClientSwing cs = new ClientSwing();
    }

 */
}
