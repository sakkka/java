package li260.mains;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import li260.IHMSwing.IHMSwing;
import li260.IHMSwing.ObserverSWING;
import li260.algo.Dijkstra;
import li260.algo.Radar;
import li260.algo.RadarClassique;
import li260.algo.RadarDijkstra;
import li260.circuit.Circuit;
import li260.circuit.CircuitFactoryFromFile;
import li260.selector.StrategyRadar;
import li260.selector.StrategyRadarDij;
import li260.strategy.Strategy;
import li260.strategy.StrategyDerapage;
import li260.strategy.StrategySaka;
import li260.terrain.LectureTerrain;
import li260.terrain.TerrainException;
import li260.terrain.TerrainTools;
import li260.view.observers.CircuitObserver;
import li260.view.observers.VoitureObserveur;
import li260.voiture.Voiture;
import li260.voiture.VoitureException;
import li260.voiture.VoitureFactory;

public class MainSwing {

	public static void main(String[] args) throws VoitureException, IOException, TerrainException {

		String filename = "track/1_safe.trk";
		ArrayList<ObserverSWING> listSwing = new ArrayList<ObserverSWING> ();
		double thetas[]={0,-Math.PI/18,-Math.PI/12,-Math.PI/9,-Math.PI/7,-Math.PI/6,-Math.PI/5,-Math.PI/4,-Math.PI/3,-Math.PI/2.5,Math.PI/2.5,Math.PI/3,Math.PI/4,Math.PI/5,Math.PI/6,Math.PI/7,Math.PI/9,Math.PI/12,Math.PI/18};

		//	ArrayList<Commande> listeCmd = new ArrayList<Commande>() ;
		BufferedImage imageCircuit = TerrainTools.imageFromTab(LectureTerrain.lecture(filename));	

		Circuit track=null;
		track = CircuitFactoryFromFile.build(filename);

		//		Filtre monfiltre = new FiltreDiagonal();
		//		track = CircuitFFFFiltre.buildWithFilter(filename, monfiltre); // FILTRE POUR BOND
		//		
		Voiture fefe = VoitureFactory.build(track); // FEFE

		Dijkstra dij = new Dijkstra (track);

		Radar RadDij = new RadarDijkstra (track, fefe, thetas, dij);
		Radar Rad = new RadarClassique (fefe, track, thetas); 

		// choix des strategies adapet�es au circuit
		//	Strategy stra = new StrategyDerapage(fefe, track, RadDij, Rad);     // rad = DIJ ; rad2 = Classique
			Strategy stra = new StrategySaka(fefe, track, RadDij, Rad); 
		// Strategy stra = new StrategyRadarDij(fefe, track, RadDij); 
		//	Strategy stra = new StrategyRadar(fefe, track, thetas);

		Simulation2 Sim = new Simulation2(fefe, track, stra, filename);

		// AFFICHAGE
		
		JFrame fen = new JFrame () ;
		IHMSwing ihm = new IHMSwing(listSwing) ;


		ihm.add (new CircuitObserver(imageCircuit)) ;
		ihm.add (new VoitureObserveur(fefe)) ;
		//ihm.add (new TrajectoireObserveur (v)) ;
		//ihm.add (new RadarObserveur (strategy.getRadar(), v)) ;

		Sim.add(ihm) ; //possibilitr√© de mettre plusieur obs

		ihm.setPreferredSize(new Dimension (1024, 768)) ;
		fen.setContentPane(ihm) ;
		
//		Container c = fen.getContentPane() ;
//		FlowLayout miseEnFlot = new FlowLayout() ;
//		c.setLayout(miseEnFlot);
//		
//		JButton bouton = new JButton("le bouton");
//		bouton.setLocation(1000, 0);
//		JLabel info = new JLabel("okok");
//		info.setBounds(1200, 200, 40, 40);
//		c.add(info);
//		c.add(bouton);
		
		fen.setVisible (true) ;
		fen.pack();
		JOptionPane.showMessageDialog(fen, "Lancer la simulation ?", "Jeu", JOptionPane.INFORMATION_MESSAGE);
		

		Sim.playSimulation() ;
		JFrame windows= new JFrame();
		


		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.exit(0);

	}

}


