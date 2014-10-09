package li260.window;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

public class Fenetre2 extends JFrame
{
	public Fenetre2()
	{
		setTitle("Une fenetre dynamique");
		Container c = getContentPane();
		c.setBackground(Color.black);
		setSize(1000, 800);
		setLocationRelativeTo(this.getParent());   
		setVisible(true); 
		setDefaultCloseOperation(3);
		
	}    
} 
