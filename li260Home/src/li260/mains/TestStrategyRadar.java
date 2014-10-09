package li260.mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import li260.circuit.Circuit;
import li260.circuit.CircuitFactoryFromFile;
import li260.sauvable.SaveListeComm;
import li260.selector.StrategyRadar;
import li260.strategy.Strategy;
import li260.terrain.LectureTerrain;
import li260.terrain.Terrain;
import li260.terrain.TerrainTools;
import li260.voiture.Commande;
import li260.voiture.Voiture;
import li260.voiture.VoitureException;
import li260.voiture.VoitureFactory;

public class TestStrategyRadar {

public static void main(String[] args) throws VoitureException, IOException {
	
	String filename = "track/1_safe.trk";
	Circuit track = CircuitFactoryFromFile.build(filename); // factory static
	System.out.println ("donnees TRACK DEPART: "+ track.getPointDepart()+ track.getDirectionDepart());
	BufferedImage imageCircuit = TerrainTools.imageFromTab(LectureTerrain.lecture(filename));
	
	
	Voiture fefe = VoitureFactory.build(track); // factory static
	double[] thetas = {-Math.PI/3., -Math.PI/6, 0, Math.PI/6, Math.PI/3.} ;
	ArrayList<Commande> listeCommandes = new ArrayList<Commande> ();
	System.out.println ("donnees fefe DEPART : "+ fefe.getDirection()+ " vitesse : " + fefe.getVitesse()+"\n" );	

	int i = 0 ;
	Strategy STRA = new StrategyRadar(fefe, track, thetas);


	while((track.getTerrain(fefe.getPosition()) != Terrain.EndLine )) {

		i ++;
			
		Commande comm = STRA.getCommande();
				
		
//		if (i == 1000) {
//		comm = new Commande(0.9, thetas[0]);                       					 // pour lancer des trucs pas cools
//		}
		
		
		fefe.drive(comm);
		listeCommandes.add(comm);	
						
		System.out.println (i+" Position: "+ fefe.getPosition());
	//	System.out.println ("Vitesse :   " + fefe.getVitesse());
		System.out.println ("--------------------------------------------------------------------------------------------------------------------------\n");  

				
		imageCircuit.setRGB((int) fefe.getPosition().getY(), (int) fefe.getPosition().getX(), Color.orange.getRGB());
		
	}
	
	System.out.println (track.getArrivees());
	
	SaveListeComm.saveListeCommande(listeCommandes, "commands.com");
	//SaveListeComm.loadListeCommande("tkt.com");
	
	
	
	
	
	ImageIO.write(imageCircuit, "png", new File("test.png"));
}

}

