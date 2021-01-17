import javax.swing.*;
import java.awt.*;

public class EntreeSalonSwing extends JFrame implements Runnable {

	String nomInterlocuteur;
	JList<String> jListPersonne ;
	JButton btnChater;
	JButton btnBloquer;
	JLabel labelPersonnesCon;
	String[] personne ;
	//DefaultListModel model;
	DefaultListModel<String> model =new DefaultListModel<>();
	Client client;
	public EntreeSalonSwing(Client c){
		super("salon de chat : " + c.getNomClient());
		model = new DefaultListModel<>();
		this.client = c;
		this.initView();
		btnBloquer.addActionListener(x->{
			int index = jListPersonne.getSelectedIndex();
			if(index>=0) {
				model.remove(index);
			}
		});
		btnChater.addActionListener(x->{
			if(this.jListPersonne.getSelectedIndex()>=0){
				this.nomInterlocuteur = this.jListPersonne.getSelectedValue();
				ClientSwing cs = new ClientSwing(this.nomInterlocuteur,this.client);
				Thread th = new Thread(cs);
				th.start();
			}
		});
	}

	private void initView(){
		this.setSize(350,330);
		this.setPreferredSize(new Dimension(350,330));
		this.setResizable(true);
		//client.start();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-350)/2,(screenSize.height-330)/2);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		btnChater = new JButton("Entrer");
		btnChater.setSize(20,30);
		btnBloquer = new JButton("Bloquer");
		btnChater.setSize(20,30);
		labelPersonnesCon = new JLabel("Personnes connectées");
		labelPersonnesCon.setHorizontalAlignment(SwingConstants.CENTER);

		jListPersonne = new JList<>(this.model);
		/*System.out.println("----->" + client.getListClient().length);
		for (int i=0;i<this.client.getListClient().length;i++) {
			if(client.getListClient()[i]!=null) {
				model.addElement(client.getListClient()[i]);
			}
		}*/
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

	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i=0;i<this.client.getListClient().length;i++) {
				String val =client.getListClient()[i];
				if((val != null)&&(val != "")) {
					if(!model.contains(val)) {
						this.model.addElement(val);
					}
					jListPersonne.setModel(this.model);
					jListPersonne.repaint();
					System.out.println("--M-->" + val );
				}

			}
		}
	}
}
