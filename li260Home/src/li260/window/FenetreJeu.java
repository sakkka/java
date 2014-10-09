package li260.window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
@SuppressWarnings("serial")

public class FenetreJeu extends JFrame implements ActionListener {

	private JButton bouton1;		// les objets boutons definis ici
	private JButton bouton2;
	
	public FenetreJeu () {
	
	// AFFICHAGE
		
		String [] haha = {"mon derche", "ton derche", "son derche"} ;
		
		setTitle("bra ba ba ba bam bim");
		setSize(500, 300);
		setLocationRelativeTo(this.getParent());   

		
		Container c = getContentPane() ;
		c.setLayout(new FlowLayout()); 				//Layout
	//	c.setBackground(Color.gray);

		
	//	CONTENU
		
		bouton1 = new JButton("le boutin");
		bouton2 = new JButton("le boutin 2");
	//	bouton.addActionListener(new EcouteurDeBoutons());
		
		bouton1.addActionListener(this);
		bouton2.addActionListener(this);
		
		JLabel info = new JLabel("touche mon derche");

		JComboBox combo = new JComboBox(haha);
		
		JList liste = new JList(haha);
		liste.setVisibleRowCount(3);
		JScrollPane scroll = new JScrollPane(liste);
		
		c.add(info);
		c.add(bouton1);
		c.add(bouton2);
		c.add(combo);
		c.add(scroll);
		
	// ECOUTEURS
		
		EcouteurDeSouris eds = new EcouteurDeSouris() ;
		addMouseListener(eds);
		setDefaultCloseOperation(3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == bouton1) {
			System.out.println("haha boutin1 !");
		}else{
			System.out.println("haha boutin2 !");
				
		}
		
	}

}
