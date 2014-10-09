package li260.terrain;

import java.awt.image.BufferedImage;

import li260.geometrie.Vecteur;

public class TerrainTools {

	public static Terrain terrainFromChar(char c) throws TerrainException  {
		Terrain[] values = Terrain.values();
		for(int i = 0; i <values.length; i++) {
			if (c==Terrain.conversion[i]) 
				return values[i];
		}
		throw new TerrainException("Terrain inconnu : "+c);
	}
	
	public static char charFromTerrain(Terrain c) {
		return Terrain.conversion [c.ordinal()];
	}
	public static int terrainToRGB(Terrain c) {
		return Terrain.convColor[c.ordinal()].getRGB();
	}
	
	public static boolean isRunnable(Terrain t) {
		return ( t==Terrain.Route || t == Terrain.Boue || t == Terrain.BandeRouge || t== Terrain.BandeBlanche || t == Terrain.StartPoint || t == Terrain.EndLine);
	}
	
	public static int getTerrainWidth(Terrain [][] tab){
		return tab[0].length;
	}
	public static int getTerrainHeight(Terrain [][] tab){
		return tab.length;
	}
	
	public static BufferedImage imageFromTab (Terrain [][] tab) {
		BufferedImage im = new BufferedImage (tab[0].length, tab.length, BufferedImage.TYPE_INT_ARGB) ;
		for(int i =0; i<tab[0].length; i++){
			for(int j = 0; j<tab.length; j++){	
				im.setRGB(i,j, terrainToRGB(tab[j][i]));
			}
		}
		/*FileOutputStream fos;
		try {
			fos = new FileOutputStream("ImageTerrain");
			ImageIO.write(im, "png", fos);
		}catch(Exception e){
			e.getStackTrace();
		}*/
		return im;			
	}
	
	public static Vecteur getStartPointCoord(Terrain [][] tab) throws TerrainException{
		for(int i =0; i<tab[0].length; i++){
			for(int j = 0; j<tab.length; j++){	
				if(tab[i][j] == Terrain.StartPoint)
					return new Vecteur(j,i);
			}
		}
		throw new TerrainException("Pas de StartPoint");
	}
}
