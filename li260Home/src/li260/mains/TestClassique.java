package li260.mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import li260.circuit.Circuit;
import li260.circuit.CircuitFactoryFromFile;
import li260.strategy.StrategyAllerToutDroit;
import li260.terrain.LectureTerrain;
import li260.terrain.TerrainTools;
import li260.voiture.Commande;
import li260.voiture.Voiture;
import li260.voiture.VoitureException;
import li260.voiture.VoitureFactory;

public class TestClassique {

public static void main(String[] args) throws VoitureException, IOException {
	
	String filename = "track/1_safe.trk";

	Circuit track = CircuitFactoryFromFile.build(filename); // factory static
	//track.getDepart();
	
	System.out.println ("donneesTRACK: "+ track.getPointDepart()+ track.getDirectionDepart());
	
	Voiture fefe = VoitureFactory.build(track); // factory static

	System.out.println ("donneesGOVA: "+ fefe.getDirection() + fefe.getVitesse() );
	
	BufferedImage imageCircuit = TerrainTools.imageFromTab(LectureTerrain.lecture(filename));
	
	ArrayList<Commande> coms = new ArrayList<Commande>();
	
	for(int i=0; i<50; i++) coms.add(StrategyAllerToutDroit.getCommande()); 	// accel a fond
	for(int i=0; i<50; i++) coms.add(StrategyAllerToutDroit.getCommande()); 	// accel a fond + droite
	for(int i=0; i<50; i++) coms.add(StrategyAllerToutDroit.getCommande()); 			// accel a fond
	for(int i=0; i<50; i++) coms.add(StrategyAllerToutDroit.getCommande()); 	// accel a fond + gauche

	
	for(Commande c:coms){
		
		fefe.drive(c);
		
		System.out.println ("position : "+ fefe.getPosition());
		System.out.println ("Vitesse fefe: "+ fefe.getVitesse());
		System.out.println ("Turn Commande: "+ c.getTurn());
		
		imageCircuit.setRGB((int) fefe.getPosition().getX(), (int) fefe.getPosition().getY(), Color.orange.getRGB());
	}

	imageCircuit.setRGB(0,0, Color.orange.getRGB());
	ImageIO.write(imageCircuit, "png", new File("test.png"));
}

}