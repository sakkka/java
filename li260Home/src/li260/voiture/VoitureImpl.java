package li260.voiture;

import li260.geometrie.Vecteur;

public class VoitureImpl implements Voiture {

	// Valeurs inchangees

	private final double vmax, braquage ; 	//capacité
	private final double alpha_c, alpha_f, beta_f, alpha_derapage, masse ;	//gestion des forces

	// valeurs variables gestion voiture

	private Vecteur position;
	private Vecteur direction;
	private double vitesse;

	// DERAPAGES

	private boolean derapage ;
	private double sens_derapage ;
	private double vitesse_sortie_derapage ;
	private Vecteur direction_derapage ;
	
	private double[] tabVitesse = {0.1,	0.2, 0.3, 0.4, 0.5,	0.6, 0.7, 0.8,	0.9, 1.} ; 
	private double[] tabTurn = {1., 1., 0.8, 0.7, 0.6, 0.4,	0.3,0.2, 0.1, 0.075} ;


	public VoitureImpl (double vmax, double braquage, double alpha_c, double alpha_f, double beta_f, double alpha_derapage, double masse, Vecteur position, Vecteur direction, double vitesse, double vitesse_sortie_derapage) {

		//super();
		this.vmax = vmax;
		this.braquage = braquage;
		this.alpha_c = alpha_c;
		this.alpha_f = alpha_f;
		this.beta_f = beta_f;
		this.alpha_derapage = alpha_derapage;
		this.masse = masse;
		this.position = position;
		this.direction = direction;
		this.vitesse = vitesse;
		this.vitesse_sortie_derapage = vitesse_sortie_derapage ;

	}



	public Vecteur getPosition() {
		return position;
	}
	public Vecteur getDirection() {
		return direction;
	}
	public double getVitesse() {
		return vitesse;
	}


	public boolean getDerapage (Commande comm) {

		if (Math.abs(comm.getTurn()) > getMaxTurnSansDerapage()) {

			System.out.println ("Derapage !!! "+ comm.getTurn());	
			return true ; }
		return false ; 
	}
	

	public boolean getDerapageExt() {

		return derapage ;
	}


	public void drive(Commande comm) throws VoitureException {

		if (comm.getAccel() < -1.0 || comm.getAccel() > 1.0) {		
			System.out.println ("Command :   "+ comm +" not allowed (accel problem)");	
			throw new VoitureException("Commande out"+comm);
		}

		if (comm.getTurn() < -1  || comm.getTurn() > 1) {	
			System.out.println ("Command :   "+ comm +" not allowed (turn problem)");	
			throw new VoitureException("Commande out"+comm);	
		}

//		if(getDerapage(comm))
//			throw new VoitureException("DERAPAGE"+comm);
		//getDerapage(comm);
		
		
		if(!derapage && getDerapage(comm))
			debut_derapage(comm);

		if(derapage)
			driveAvecDerapage(comm);
		else
			driveSansDerapage(comm);


	}
	public void driveSansDerapage (Commande c){
		// approche normale
		//System.out.println ("DRIVE SANS DERAPAGE");
		// 1) gestion du volant
		direction = direction.rotation(c.getTurn() * braquage);

		//System.out.println ("direction GOVA : "+ direction.rotation(c.getTurn() * braquage) );

		// 2.1) gestion des frottements

		vitesse -= alpha_f;
		vitesse -= beta_f*vitesse;

		// 2.2) gestion de l'acceleration/freinage

		vitesse += c.getAccel() * alpha_c;

		// 2.3) garanties, bornes...
		direction = direction.toUnitVec();

		vitesse = Math.max(0., vitesse); // pas de vitesse négative
		vitesse = Math.min(vmax, vitesse);

		// 3) mise à jour de la position

		position.autoAdd(direction.fact(vitesse));

	}

	public double getMaxTurnSansDerapage() {
		for(int i=0; i<tabVitesse.length; i++)
			if(vitesse<=tabVitesse[i])
				return tabTurn[i];
		return 1.;
	}


	private void driveAvecDerapage(Commande c) {
		
		
		System.out.println ("DRIVE AC DERAPAGE");
	
		
		// freinage quelque soit la commande
		vitesse -= alpha_derapage;
		vitesse = Math.max(0., vitesse);

		// maj de la direction
		direction.rotation(Math.signum(c.getTurn()) * getMaxTurnSansDerapage() * braquage); // mod 2013
		direction_derapage.rotation(sens_derapage * getMaxTurnSansDerapage() * braquage * 1.1); // mod 2013

		// avance un peu selon
		position.autoAdd(direction.fact(vitesse));

		if(vitesse < vitesse_sortie_derapage )
			fin_derapage();

	}
	

	private void fin_derapage() {
		derapage = false;
		direction = direction_derapage.clone();

	}

	private void debut_derapage(Commande c){
		derapage = true;

		vitesse_sortie_derapage = 0.75 * vitesse; // 2013
		sens_derapage =  Math.signum(c.getTurn());
		direction_derapage = direction.clone();
	}

	public double getBraquage() {

		return braquage;
	}


	public double getMasse() {
		return masse;
	}


	public double getSens_derapage() {
		return sens_derapage;
	}


	public double getVitesse_sortie_derapage() {
		return vitesse_sortie_derapage;
	}


	public Vecteur getDirection_derapage() {
		return direction_derapage;
	}

	public double getVMax() {

		return vmax;
	}



	/*public void update(double accel, double turn){
	double modifAcc = accel * Voiture.getAcceleration();
	double modifRot = turn * Voiture.getRotation();
	double newVit = this.getVitesse() + modifAcc;
	if (newVit > 0.9)
		newVit = 0.9;
	if (newVit < 0)
		newVit = 0;
	this.setVitesse(newVit);
	this.setDirection(this.direction.rotation(modifRot));
	this.setPosition(this.getPosition().add(this.getDirection().mult(this.getVitesse())));		
}

public BufferedImage listeCom(Terrain[][] t){
	BufferedImage im = TerrainTools.imageFromTab(t);
	for(int i = 0;i<20;i++){
		this.update(1, 0);
		im.setRGB((int)this.getPosition().getX(),(int)this.getPosition().getY() , Color.black.getRGB());
	}
	FileOutputStream fos;
	try {
		fos = new FileOutputStream("ImageTerrain");
		ImageIO.write(im, "png", fos);
	}catch(Exception e){
		e.getStackTrace();
	}
	return im;	
}*/
}
