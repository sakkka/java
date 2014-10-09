package li260.mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import li260.algo.Dijkstra;
import li260.circuit.Circuit;
import li260.circuit.CircuitFactoryFromFile;
import li260.terrain.LectureTerrain;
import li260.terrain.TerrainTools;
import li260.voiture.Voiture;
import li260.voiture.VoitureException;
import li260.voiture.VoitureFactory;

public class MainDijkstra {
	
	public static void main(String[] args) throws VoitureException, IOException {
		
		String filename = "track/3_safe.trk";
		Circuit track = CircuitFactoryFromFile.build(filename); // factory static
		
		BufferedImage imageCircuit = TerrainTools.imageFromTab(LectureTerrain.lecture(filename));
		
		
		Voiture fefe = VoitureFactory.build(track); // factory static
		double[] thetas = {-Math.PI/3., -Math.PI/6, 0, Math.PI/6, Math.PI/3.} ;
		
//		ArrayList<Commande> listeCommandes = new ArrayList<Commande> ();


					
//		double [][] distance =	new	double	[track.getHeight()] [track.getWidth()] ;
//		
//			for	(int i=0; i<track.getHeight(); i++) {
//				
//				for	(int j=0; j<track.getWidth(); j++) {
//					
//					distance[i][j] = Double.MAX_VALUE ;
//				}
//			}
						

			

		Dijkstra dij = new Dijkstra(track);
		dij.compute();
		
		for	(int i=0; i<track.getHeight(); i++) {
			
			for	(int j=0; j<track.getWidth(); j++) {
				
				if(TerrainTools.isRunnable(track.getTerrain(i,j))) {
					
					
//					if(dij.getDist(i, j) != Double.POSITIVE_INFINITY)
//						System.out.println(dij.getDist(i, j)+" dist main ----------");
					
					Color c = new Color((int) ((dij.getDist(i,j)*10)%255), 0, 0);
					//Color c1 = new Color (100, 0, 0);
					
					imageCircuit.setRGB(j, i, c.getRGB());
				
				}
			}
		}
			
		
		
		
		
		
		ImageIO.write(imageCircuit, "png", new File("testDij.png"));
	}
}


