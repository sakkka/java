package li260.voiture;

public class Commande {
	
	private double accel ;
	private double turn ;
	
	public Commande(double accel, double turn) {
		super();
		this.accel = accel;
		this.turn = turn;
	}

	public double getAccel() {
		return accel;
	}


	public double getTurn() {
		return turn;
	}



	

}
