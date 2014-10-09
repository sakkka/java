package li260.algo;

import java.util.Comparator;

import li260.geometrie.Vecteur;

public class ComparatorDijkstra implements Comparator<Vecteur>  {
	
	public double[][] dist;
	
	
	public ComparatorDijkstra(double[][] dist) {
		
		this.dist = dist;
		
		}
	
	public int compare( Vecteur o1,  Vecteur o2) {
		int x1 = (int) o1.getX();
		int y1 = (int) o1.getY();
		int x2 = (int) o2.getX();
		int y2 = (int) o2.getY();
		//System.out.println(x1+" "+x2+" "+y1+" "+y2+" "+dist[x1][y1]+ " "+dist[x2][y2]);
		if(dist[x1][y1] > dist[x2][y2]) {
		//	System.out.println("le vecteur1 : "+(int)o1.getX()+" "+(int)o1.getY()+" > vecteur2 "+ (int)o2.getX()+" "+ (int)o2.getX() );
			return 1;
		}
		else if (dist[x1][y1] == dist[x2][y2]) {
			
		//	System.out.println("dist v1 : ("+dist[(int) o1.getX()][(int) o1.getY()]+" == dist v2 ("+ dist[(int) o2.getX()][(int) o2.getY()]+")" );
			return 0;
		}
		return -1;
	}
 }


