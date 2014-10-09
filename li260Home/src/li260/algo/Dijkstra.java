package li260.algo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import li260.circuit.Circuit;
import li260.geometrie.Vecteur;
import li260.terrain.TerrainTools;
import li260.voiture.Commande;

public class Dijkstra {
	public double[][] dist;
	private ArrayList<Vecteur> Q;
	private ComparatorDijkstra comp;
	private Circuit c;

	public Dijkstra(Circuit c) throws IOException{

		this.c=c;
		dist = new double[c.getHeight()][c.getWidth()];
		Q = new ArrayList<Vecteur>();

		comp = new ComparatorDijkstra (dist);

		for(int i=0; i<dist.length; i++){
			for(int j=0; j<dist[0].length; j++){
				dist[i][j]=Double.POSITIVE_INFINITY;
			}
		}

		for(Vecteur p: c.getArrivees()){
			dist[(int)p.getX()][(int)p.getY()] = 0;
			//System.out.println("arrivee setted : "+p.getX()+" "+p.getY()+" "+dist[(int)p.getX()][(int)p.getY()] );

			Q.add(p);
		}
		
		
		boolean loaded=false;
		File directory = new File(System.getProperty("user.dir"));	
		//get all the files from a directory
	
		File[] fList = directory.listFiles();
		
		for (File file : fList){
			String name2 ="matDij_"+c.getName();
			if (name2.startsWith(file.getName())){

				this.dist = loadMatrix(dist, c.getName());
				loaded = true;
				break;
			}
		
		}
		
		if(!loaded){
			System.out.println("no file to load");
			compute();
			saveMatrix(dist, c.getName());
		}
	}

	public void compute() {
		System.out.println("compute ! (..)");
		double plus;
		while(!Q.isEmpty()){
			Vecteur s = Collections.min(Q, comp);

			Q.remove(s);
			int x=(int)s.getX();
			int y=(int)s.getY();
			double d = dist[x][y]; 													 
			//System.out.println(Q.size()+ " "+x+" "+y+" "+d);
			 //System.out.println("compute ! (..)");
			//System.out.println(c.getWidth()+" "+c.getHeight());

			for(int i=-1;i<2;i++){

				for(int j=-1;j<2;j++){		 // les points de la matrice

					Vecteur ecart = new Vecteur(i,j);
					Vecteur vois = new Vecteur(x+i,y+j);

					//plus=14
					//si c'est la ligne d'arrivé


					if ((vois.getX()<0) || (c.getWidth()<=vois.getY()) || (0>vois.getY()) || (c.getHeight()<=vois.getX()))
						continue ;

					if((i==0)&&(j==0))
						continue; 

					if(dist[x][y] == 0)  // Si on est sur la ligne d'arrivée 	
						if(c.getDirectionArrivee().produitScalaire(ecart) > 0){
							break;
						}
							//continue; 


					if(!TerrainTools.isRunnable(c.getTerrain(vois)))
						continue;
					//        System.out.println((d+plus)+" tototot "+dist[y+i][x+j]);

					plus=1.4;
					if(i==0 || j==0)
						plus = 1;

					if(( d+plus ) < dist[x+i][y+j] ){ 	

						//       	 	System.out.println(dist[y+i][x+j]+" "+(d+plus));

						if(dist[x+i][y+j] == Double.POSITIVE_INFINITY)
							Q.add(vois);
						dist[x+i][y+j] = (d+plus);                 





					}
				}
			}
		}
	}

	public  double getDist(int i, int j) {
		// System.out.println("getDist : "+dist[i][j]);
		return dist[i][j];
	}
	
	public void saveMatrix(double[][] dist, String filename) {
		System.out.println("saving ..");
				
				 try {		   
		                DataOutputStream os = new DataOutputStream (new FileOutputStream("matDij_"+filename));
		   
		        		for(int i = 0; i<dist.length; i++){
		        			for(int j=0; j<dist[0].length; j++) {
		        				
		                os.writeDouble(dist[i][j]) ;
		                //os.writeChars(" ") ;
		   
		        			}
		        		}
		        		os.close() ;
		                
		                } catch (IOException e) {
		                	
		                      e.printStackTrace();
		                }
				
			}
	
	public static double[][] loadMatrix (double[][] dist, String filename) throws IOException{
		System.out.println("loading ..");
		try {
			@SuppressWarnings("resource")
			DataInputStream os = new DataInputStream(new FileInputStream("matDij_"+filename));
			//dist2 = new double[c.getHeight()][c.getWidth()];
//			liste = new ArrayList<Commande>();
//			double a,t;

	//		while(true){ // on attend la fin de fichier
			for(int i = 0; i<dist.length; i++){
    			for(int j=0; j<dist[0].length; j++) {
    				
    			
				dist[i][j] = os.readDouble();
				
    			}
			}
				//System.out.println ("(lu " +i+") accel: " + a+ " turn: "+t );
				

		} catch (EOFException e){
			 e.printStackTrace(); }
		
		return dist;
	}
		
		
	}


