package li260.filtre;

import li260.terrain.Terrain;
import li260.terrain.TerrainTools;

public class FiltreDiagonal implements Filtre {

	public void filtrer(Terrain[][] track) {
		for ( int i =0; i <=track.length-2 ; i++) {

            for(int j =0; j <=track[0].length-2 ;j++){
            	
            	if (( TerrainTools.isRunnable(track[i][j])) &&(TerrainTools.isRunnable(track[i+1][j+1])) &&
            			(!(TerrainTools.isRunnable(track[i+1][j]))) && (!(TerrainTools.isRunnable(track[i][j+1])))){

                    track[i][j]=Terrain.Herbe ;
                    track[i+1][j+1]=Terrain.Herbe ;

                }

                else  if(( TerrainTools.isRunnable(track[i+1][j])) &&(TerrainTools.isRunnable(track[i][j+1])) &&
                		(!(TerrainTools.isRunnable(track[i][j]))) && (!(TerrainTools.isRunnable(track[i+1][j+1])))){

                    track[i+1][j]=Terrain.Herbe ;
                    track [i][j+1]=Terrain.Herbe ;

                }
            }
		}
		
	}
	
	public void filtrerSam (Terrain[][] track){
    	
		for ( int i =0; i <=track.length-2 ; i++) {

            for(int j =0; j <=track[0].length-2 ;j++){
            	
            	if (( TerrainTools.isRunnable(track[i][j])) &&(TerrainTools.isRunnable(track[i+1][j+1])) &&
            			(!(TerrainTools.isRunnable(track[i+1][j]))) && (!(TerrainTools.isRunnable(track[i][j+1])))){

                    track[i][j]=Terrain.Herbe ;
                    track[i+1][j+1]=Terrain.Herbe ;

                }

                else  if(( TerrainTools.isRunnable(track[i+1][j])) &&(TerrainTools.isRunnable(track[i][j+1])) &&
                		(!(TerrainTools.isRunnable(track[i][j]))) && (!(TerrainTools.isRunnable(track[i+1][j+1])))){

                    track[i+1][j]=Terrain.Herbe ;
                    track [i][j+1]=Terrain.Herbe ;

                }
            }
		}
    	
}

	
	public Boolean checkMat (Terrain[][] track, int i, int j){
		if(TerrainTools.isRunnable(track[i][j])) {
			if(TerrainTools.isRunnable(track[i+1][j+1])) {
				return true;
			}
		}
		return false ;
	}
	
	public void colorMat (Terrain[][] track, int x, int y){
		for (int i=x; i <= x+1; i++)
			for (int j=y; j <= y+1; j++) {
				track[i][j] = Terrain.Herbe;
			}
	}
	

}
