package li260.IHMSwing;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class IHMSwing extends JPanel implements UpdateEventListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ObserverSWING> list ;
	
	
	public IHMSwing (ArrayList<ObserverSWING> liste) {
		this.list = liste ;
	}
	
	
	public void setList(ArrayList<ObserverSWING> newlist) {
		this.list = newlist ;
	}
	
	public void manageUpdate() {
		try { 						// pour les courses trop rapides
			Thread.sleep(1);
		 } catch(InterruptedException e) {
			 e.printStackTrace();
		}
		// Pour que l'affichage suive correctement
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			repaint();
		}
	}) ;
	}
	public void add (ObserverSWING obs){
		list.add(obs);
	}
	public void paint (Graphics g) {
		super.paint(g);
		for(ObserverSWING o : list){
			o.print(g);
		}
		
	}
}
