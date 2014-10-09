package li260.mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import li260.algo.RadarClassique;
import li260.algo.ResultatRadar;
import li260.circuit.Circuit;
import li260.circuit.CircuitFactoryFromFile;
import li260.terrain.LectureTerrain;
import li260.terrain.TerrainTools;
import li260.voiture.Commande;
import li260.voiture.Voiture;
import li260.voiture.VoitureException;
import li260.voiture.VoitureFactory;

public class TestRadar {

public static void main(String[] args) throws VoitureException, IOException {
	
	String filename = "track/1_safe.trk";

	Circuit track = CircuitFactoryFromFile.build(filename); // factory static
	//track.getDepart();
	
		System.out.println ("donneesTRACK: "+ track.getPointDepart()+ track.getDirectionDepart());
	
	Voiture fefe = VoitureFactory.build(track); // factory static
	double[] thetas = {-Math.PI/3., -Math.PI/6, 0, Math.PI/6, Math.PI/3.} ;
	
	

	
//	double[] scores = RC.getScores();
//	
//	for(double d:scores)
//		System.out.println(d);
//	
//	System.out.println ("RESR angle best index "+ RC.returnBest()); 
//	System.out.println ("RESR angle "+ ResR.getAngle());
//	System.out.println ("RESR best Score "+ ResR.getScores());
//	System.out.println ("RESR best Distance  "+ ResR.getDistancePixel());
//	if(true)
//		return;
	

	

		System.out.println ("donnees fefe : "+ fefe.getDirection() + fefe.getVitesse() );
	
	BufferedImage imageCircuit = TerrainTools.imageFromTab(LectureTerrain.lecture(filename));
	
	ArrayList<Commande> coms = new ArrayList<Commande>();
	
	for(int i=0; i<200; i++) coms.add(new Commande(1.0, 0.)); 	// accel a fond
	for(int i=0; i<50; i++) coms.add(new Commande(1.0, 0.1));
	for(int i=0; i<50; i++) coms.add(new Commande(1.0, 0.0));

	
	for(Commande c:coms){
		
		fefe.drive(c);
		
		RadarClassique RC = new RadarClassique (fefe, track, thetas);
		RC.getScores();
		RC.getBestIndex();
		ResultatRadar ResR = RC.getResultatRadar();
		
		
		System.out.println ("position : "+ fefe.getPosition());
		//System.out.println ("Vitesse fefe: "+ fefe.getVitesse());
		System.out.println ("Turn Commande: "+ c.getTurn());
		
		
		
		
		
		System.out.println ("RESR angle best index "+ RC.getBestIndex()); 
		
		System.out.println ("RESR angle "+ ResR.getAngle());
		System.out.println ("RESR best Score "+ ResR.getScores());
		System.out.println ("RESR best Distance  "+ ResR.getDistancePixel());
		
		System.out.println ("\n--------------------------------------------------------------------------------------------------------------------------\n");  
		//Vecteur dir = fefe.getDirection();
		//dir.rotation(ResR.getAngle());
		
		
		
		
		
		
		imageCircuit.setRGB((int) fefe.getPosition().getY(), (int) fefe.getPosition().getX(), Color.orange.getRGB());
	}

	//imageCircuit.setRGB(0,0, Color.orange.getRGB());
	ImageIO.write(imageCircuit, "png", new File("test.png"));
}

}
