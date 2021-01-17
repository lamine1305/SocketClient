import javax.swing.*;
import javax.swing.border.Border;

import static java.awt.Event.ENTER;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClientSwing extends JFrame implements Runnable {

	JButton btn;
	TextArea zoneHistoriqueText;
	TextArea taSisir;

	String demandeur;
	String recepteur;
	String textEnvoye="";
	Boolean send= false;
	Client client;
	public ClientSwing(String nomInterlocuteur,Client cl) {
		super("Messenger " +cl.getNomClient() +" discution avec " + nomInterlocuteur);
		this.demandeur = cl.getNomClient();
		this.recepteur = nomInterlocuteur;
		this.client = cl;
		this.initView();
		
		this.btn.addActionListener(x->{
			if(!this.taSisir.getText().isEmpty()) {
				this.send = false;
				this.zoneHistoriqueText.setText(this.zoneHistoriqueText.getText() + '\n' +this.demandeur +" : " + this.taSisir.getText());
				textEnvoye ="<<<"+this.demandeur +">>>,<<<"+this.recepteur+">>>" + this.taSisir.getText();

				client.send(textEnvoye);
				this.taSisir.setText("");
			}
		});
	}

	private  void initView() {
		this.setSize(550,480);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		zoneHistoriqueText = new TextArea();
		zoneHistoriqueText.setForeground(Color.BLUE);
		taSisir = new TextArea();
		taSisir.setPreferredSize(new Dimension(0,60));
		btn = new JButton("Envoyer");
		btn.setSize(20,30);

		btn.setBackground(Color.orange);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-550)/2,(screenSize.height-480)/2);
		JPanel panelHaut = new JPanel(new BorderLayout());
		panelHaut.add(zoneHistoriqueText, BorderLayout.CENTER);
		panelHaut.setBackground(Color.orange);
		JPanel panelBas = new JPanel(new BorderLayout());
		panelBas.setBackground(Color.red);
		panelBas.add(taSisir, BorderLayout.CENTER);
		panelBas.add(btn, BorderLayout.EAST);
		this.setLayout(new BorderLayout());
		this.add(panelHaut, BorderLayout.CENTER);
		this.add(panelBas, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	@Override
	public void run() {
		while(true) {
		try {
			Thread.sleep(100);
			if((client.getReceiveMessage() != null)&&(!client.getReceiveMessage().contains("##"))&&(client.getMessageDiscution()!="-xx")) {
				this.zoneHistoriqueText.setText(this.zoneHistoriqueText.getText() + '\n' + this.recepteur+ " :" + client.getMessageDiscution());
				client.setMessageDiscution("-xx");
			}
		}catch(Exception e) {
			System.out.println("Probleme de récupérations des messages envoyés par l'interlocuteur !!!");
		}
		}
	}
	
	public void notifierArriveeMsg(String msg) {
	}
}
