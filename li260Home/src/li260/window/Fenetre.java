package li260.window;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Fenetre extends JFrame implements ActionListener	 {
	
	private JButton bouton1;
	private JButton bouton2 ;
	private static final long serialVersionUID = 1L;

	public  Fenetre () {

		{
			setTitle("Une fenetre dynamique");
			Container c = getContentPane();
			c.setBackground(Color.gray);
			setSize(300, 200);
			c.setLayout(new FlowLayout());
			JButton bouton1 = new JButton("Bouton De Ouf");
			JButton bouton2 = new JButton("Bouton De Ouf 2");
			c.add(bouton1);
			c.add(bouton2);
			setLocationRelativeTo(this.getParent());   
			bouton1.addActionListener(this);
			bouton2.addActionListener(this);
			setDefaultCloseOperation(3);
		} 
	}
	
	public void actionPerformed (ActionEvent a) {
		  if(a.getSource() == bouton1)  {
				System.out.println(" bouton !");

		}
		  if(a.getSource() == bouton2) {
				System.out.println(" bouton ! bababa");

		  }
	}


}
