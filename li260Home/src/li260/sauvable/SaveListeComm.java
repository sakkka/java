package li260.sauvable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import li260.voiture.Commande;

public class SaveListeComm {
	
	   public static void saveListeCommande (ArrayList<Commande> liste, String filename){
			                                                            
			   
			          try {		   
			                DataOutputStream os = new DataOutputStream (new FileOutputStream(filename)) ;
			   
			                for (Commande c : liste) {
			   
			                      os.writeDouble(c.getAccel()) ;
			   
			                      os.writeDouble(c.getTurn()) ;
			                      
			                      
			              		// System.out.println ("save " +i+ " turn : "+ c.getTurn());
			                }
			                os.close() ;
			                
			                } catch (IOException e) {
			                	
			                      e.printStackTrace();
			                }
	   }
	   
		public static ArrayList<Commande> loadListeCommande (String filename) throws IOException{
			ArrayList<Commande> liste = null;

			try {
				@SuppressWarnings("resource")
				DataInputStream os = new DataInputStream(new FileInputStream(filename));

				liste = new ArrayList<Commande>();
				double a,t;

				while(true){ // on attend la fin de fichier
					a = os.readDouble();
					t = os.readDouble();
					liste.add(new Commande(a,t));
					
					//System.out.println ("(lu " +i+") accel: " + a+ " turn: "+t );
					
				}
				

			} catch (EOFException e){
				return liste;
			}
		}

			  
}
