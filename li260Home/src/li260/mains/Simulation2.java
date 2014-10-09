package li260.mains;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import li260.IHMSwing.UpdateEventListener;
import li260.IHMSwing.UpdateEventSender;
import li260.circuit.Circuit;
import li260.sauvable.SaveListeComm;
import li260.strategy.Strategy;
import li260.terrain.LectureTerrain;
import li260.terrain.Terrain;
import li260.terrain.TerrainTools;
import li260.voiture.Commande;
import li260.voiture.Voiture;
import li260.voiture.VoitureException;
public class Simulation2  implements UpdateEventSender {

	private Voiture fefe ;
	private Circuit track ;
	private Strategy stra ;
	private State etat ;
	private ArrayList<UpdateEventListener> ecouteurs ;
	private ArrayList<Commande> listeCmd ;
	private boolean pause ;
	private String filenameCirc ;


	public Simulation2 (Voiture fefe, Circuit track, Strategy stra, String filename) {

		this.fefe = fefe ;
		this.track = track ;
		this.stra = stra ;
		listeCmd = new ArrayList<Commande>();
		ecouteurs = new ArrayList<UpdateEventListener>();
		pause = false;
		etat=State.EnCours;
		filenameCirc = filename ;
	}

	public void playSimulation () throws VoitureException, IOException {

		BufferedImage imageCircuit = TerrainTools.imageFromTab(LectureTerrain.lecture(filenameCirc));
		int i = 0 ;
		while(this.etat == State.EnCours) {

			while(pause==true){
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Commande comm = stra.getCommande();					
			listeCmd.add(comm) ;			
			fefe.drive(comm);							// ORDRE a revoir
			update();
			updateState() ;
			imageCircuit.setRGB((int) fefe.getPosition().getY(), (int) fefe.getPosition().getX(), Color.orange.getRGB());

			if(i % 50 == 0 ) {
				imageCircuit.setRGB((int) fefe.getPosition().getY()+2, (int) fefe.getPosition().getX(), Color.cyan.getRGB());
			}
			
			System.out.println (i+" --------------------------------------------------------------------------------------------------------------------------\n");  
			System.out.println (i+" Commande: "+ comm.getTurn()+" "+comm.getAccel()+"  Vitesse : "+fefe.getVitesse());
			i ++;
		}
		SaveListeComm.saveListeCommande(listeCmd, "CommandesSimu2.com");
		ImageIO.write(imageCircuit, "png", new File("ImageSimu2.png"));
	}

	public void updateState() {

		if (track.getTerrain(fefe.getPosition()) != Terrain.EndLine && TerrainTools.isRunnable(track.getTerrain(fefe.getPosition())) )
		{	 this.etat = State.EnCours ; }


		if (track.getTerrain(fefe.getPosition()) == Terrain.EndLine){
			System.out.println ("____FINISH____ ");
			this.etat = State.Finish ;
		}

		if (! TerrainTools.isRunnable(track.getTerrain(fefe.getPosition()))) 
		{
			System.out.println ("____CRASH____ ");

			this.etat = State.Crash ;
		}

	}

	public void update() {
		for (UpdateEventListener listener : ecouteurs)
			listener.manageUpdate();
	}

	public void add (UpdateEventListener listener) {
		ecouteurs.add(listener);
	}
	public void removeall(){
		ecouteurs.clear();
	}

	public ArrayList<Commande> getListCmd () {
		return listeCmd;
	}

	public State getState () {
		return etat ;
	}



}