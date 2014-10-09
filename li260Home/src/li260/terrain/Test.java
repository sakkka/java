package li260.terrain;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// Test de cr�ation de l'image.
		
		Terrain[][] t = LectureTerrain.lecture("1_safe.trk");
		
		BufferedImage buff =TerrainTools.imageFromTab(t); 
		
		/*
		File out = new File ("image_circuit");
		ImageIO.write(buff, "png", out);   */
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("ImageTerrain");
			ImageIO.write(buff, "png", fos);
		}catch(Exception e){
			e.getStackTrace();
		}
		
		// Test trajectoire voiture
		
		/*Voiture v = new Voiture("1_safe.trk");
		System.out.println(v.getDirection());
		v.listeCom(t);
		System.out.println(v.getDirection());*/
		
		
	}

}
