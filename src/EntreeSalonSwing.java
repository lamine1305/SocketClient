import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntreeSalonSwing extends JFrame {

    String nomUser;
    String nomInterlocuteur;
    JList<String> jListPersonne ;
    JButton btnChater;
    JButton btnBloquer;
    JLabel labelPersonnesCon;
    public EntreeSalonSwing(String nomUser){
        super("salon de chat");
        this.nomUser = nomUser;
        this.setSize(350,330);
        this.setPreferredSize(new Dimension(350,330));
        this.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width-350)/2,(screenSize.height-330)/2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        btnChater = new JButton("Entrer");
        btnChater.setSize(20,30);
        btnBloquer = new JButton("Quitter");
        btnChater.setSize(20,30);
        labelPersonnesCon = new JLabel("Personnes connectées");
        labelPersonnesCon.setHorizontalAlignment(SwingConstants.CENTER);
        String[] personne = {"Lamine", "adam","Yanis","Karim","Mouadh","Nael"};
        jListPersonne = new JList(personne);
        JPanel panelHaut = new JPanel(new BorderLayout());
        panelHaut.add(labelPersonnesCon,BorderLayout.NORTH);
        panelHaut.add(jListPersonne,BorderLayout.SOUTH);
        panelHaut.setBackground(Color.gray);
        JPanel panelBas = new JPanel(new BorderLayout());
        panelBas.add(btnChater,BorderLayout.EAST);
        panelBas.add(btnBloquer,BorderLayout.WEST);

        this.setLayout(new BorderLayout());
        this.add(panelHaut,BorderLayout.NORTH);
        this.add(panelBas,BorderLayout.SOUTH);
        this.setVisible(true);
        btnBloquer.addActionListener(x->{
            this.jListPersonne.remove(jListPersonne.getSelectedIndex());
        });

        btnChater.addActionListener(x->{
            if(this.jListPersonne.getSelectedIndex()>=0){
                this.nomInterlocuteur = this.jListPersonne.getSelectedValue();
                ClientSwing cs = new ClientSwing(this.nomUser,this.nomInterlocuteur);
            }
        });

    }
}
