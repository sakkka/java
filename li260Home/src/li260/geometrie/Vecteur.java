package li260.geometrie;

import java.io.Serializable;

import li260.sauvable.Sauvable;

public class Vecteur implements Serializable, Sauvable {

	private static final long serialVersionUID = 6076335700656010155L;
	private double x,y ;
	
	public Vecteur(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void save(String filename) {
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	
	@Override
	public String toString() {
		return "Vecteur [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = (Vecteur) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	public Vecteur add(Vecteur v){
		return new Vecteur(this.getX() + v.getX(), this.getY() + v.getY());
	}
	
	public void autoAdd(Vecteur v){
		this.setX(this.x + v.x);
		this.setY(this.y + v.y);
	}
	
	public void autoSub(Vecteur v){
		this.setX(this.x - v.x);
		this.setY(this.y - v.y);
	}
	
	public Vecteur sub(Vecteur v){
		return new Vecteur(this.getX() - v.getX(), this.getY() - v.getY());
	}
	
	public double produitScalaire(Vecteur v){
		return this.getX() * v.getX() + this.getY() * v.getY();
	}
	
	public double produitVectoriel(Vecteur v){
		return this.getX() * v.getY() - this.getY() * v.getX();
	}
	
	public void autoMult(double scal) {
		this.setX(this.x * scal);
		this.setY(this.y * scal);
	}
	
	public Vecteur mult(double scal){
		return new Vecteur(this.getX()*scal,this.getY()*scal);
	}
	
	public void autoRotation (double angle) {
		double vx = this.getX()*Math.cos(angle) - this.getY()*Math.sin(angle);
		double vy = this.getX()*Math.sin(angle) + this.getY()*Math.cos(angle);
		this.setX(vx);
		this.setY(vy);
	}
	
	public Vecteur rotation (double angle){
		//Vecteur v = new Vecteur(0,0);
		double vx = this.getX()*Math.cos(angle) - this.getY()*Math.sin(angle);	
		double vy = this.getX()*Math.sin(angle) + this.getY()*Math.cos(angle);
//		v.setX(vx);
//		v.setY(vy);
		return new Vecteur(vx, vy);
	}
	
	public double norme(){
		return this.getX()*this.getX() + this.getY()*this.getY();
	}
	
	public Vecteur clonage(Vecteur v){
		return new Vecteur(v.getX(),v.getY());
	}
	
	public Vecteur clone (){
		return new Vecteur(this.x,this.y); 
	}
	
	public Vecteur fact (double d) {
		return new Vecteur (this.x * d, this.y * d);
	}
	
	public boolean egalite(Vecteur v){
		return this.equals(v);
	}

	public Vecteur toUnitVec() {
		double d = this.norme();
	return new Vecteur(this.x/d, this.y/d) ;
	
	}
	
	
}
