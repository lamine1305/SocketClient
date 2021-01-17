import javax.swing.*;
import java.awt.*;

public class AuthentificationSwing extends JFrame {
    JButton btnEntree;
    JButton btnSortie;
    JTextField textIdentifiant;
    JLabel labelIdentifiant;
    Client client = new Client();

    public AuthentificationSwing(){
        super("Authetification");
        this.initView();
        btnSortie.addActionListener(x->{
            this.dispose();
        });
        btnEntree.addActionListener(x->{
            String identifiant = this.textIdentifiant.getText();
          
            if(identifiant.length()>0) {
                client.setNomClient(identifiant);
                EntreeSalonSwing ess = new EntreeSalonSwing(client);
                client.start();
                try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                Thread t = new Thread(ess);
                t.start();
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"Saisir un identifiant ");
            }
        });
    }

    private void initView(){
        this.setSize(350,140);
        this.setPreferredSize(new Dimension(350,140));
        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-350)/2,(screenSize.height-140)/2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        btnEntree = new JButton("Entrer");
        btnEntree.setSize(20,30);
        btnSortie = new JButton("Quitter");
        btnEntree.setSize(20,30);
        textIdentifiant = new JTextField();
        labelIdentifiant = new JLabel("Identifiant");
        labelIdentifiant.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelHaut = new JPanel(new GridLayout(2,1));
        panelHaut.add(labelIdentifiant);
        panelHaut.add(textIdentifiant);
        panelHaut.setBackground(Color.gray);
        JPanel panelBas = new JPanel(new BorderLayout());
        panelBas.add(btnEntree,BorderLayout.EAST);
        panelBas.add(btnSortie,BorderLayout.WEST);

        this.setLayout(new BorderLayout());
        this.add(panelHaut,BorderLayout.CENTER);
        this.add(panelBas,BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        AuthentificationSwing aut = new AuthentificationSwing();
    }
}
