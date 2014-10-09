package li260.mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import li260.algo.Dijkstra;
import li260.algo.Radar;
import li260.algo.RadarClassique;
import li260.algo.RadarDijkstra;
import li260.circuit.Circuit;
import li260.circuit.CircuitFactoryFromFile;
import li260.sauvable.SaveListeComm;
import li260.strategy.Strategy;
import li260.strategy.StrategyRadarPlusDijkstra;
import li260.terrain.LectureTerrain;
import li260.terrain.Terrain;
import li260.terrain.TerrainTools;
import li260.voiture.Commande;
import li260.voiture.Voiture;
import li260.voiture.VoitureException;
import li260.voiture.VoitureFactory;

public class TestRadarDijkstra {


	public static void main(String[] args) throws VoitureException, IOException {
		int i = 0 ;
		String filename = "track/2_safe.trk"; 	// 3 fonctionne pas !

		Circuit track = CircuitFactoryFromFile.build(filename);
		BufferedImage imageCircuit = TerrainTools.imageFromTab(LectureTerrain.lecture(filename));
		
		Voiture fefe = VoitureFactory.build(track);
		double[] thetas = {-Math.PI/10., -Math.PI/3., -Math.PI/4., -Math.PI/6, 0, Math.PI/6, Math.PI/4., Math.PI/3., Math.PI/10.} ;
		//double[] thetas = {-Math.PI/3., -Math.PI/6, 0, Math.PI/6, Math.PI/3.} ;
		
		ArrayList<Commande> listeCommandes = new ArrayList<Commande> ();	
		
		Dijkstra dij = new Dijkstra (track);
		
		Radar Rad = new RadarDijkstra (track, fefe, thetas, dij);
		Radar Rad2 = new RadarClassique (fefe, track, thetas); 												// Radar Utilise
	
		Strategy STRA = new StrategyRadarPlusDijkstra(fefe, track, Rad, Rad2); 									// Strategy Utilisee

		while((track.getTerrain(fefe.getPosition()) != Terrain.EndLine )) {
			i++;
				
			Commande comm = STRA.getCommande();
					
			System.out.println ("CMD  :   " + comm.getAccel()+ " turn : "+ comm.getTurn());			
			
			fefe.drive(comm);
			listeCommandes.add(comm);	
							
			System.out.println (i+" Position: "+ fefe.getPosition());
		//	System.out.println ("Vitesse :   " + fefe.getVitesse());
			System.out.println ("--------------------------------------------------------------------------------------------------------------------------\n");  

					
			imageCircuit.setRGB((int) fefe.getPosition().getY(), (int) fefe.getPosition().getX(), Color.orange.getRGB());
			
		}
		
		System.out.println (track.getArrivees());
		SaveListeComm.saveListeCommande(listeCommandes, "commandsRadDij.com");	
		ImageIO.write(imageCircuit, "png", new File("testRadDij.png"));
		

	}

}
