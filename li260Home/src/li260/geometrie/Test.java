package li260.geometrie;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test Vecteurs");
		// Save
		try{
			FileOutputStream fos = new FileOutputStream("testSauv");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(new Vecteur(2,2));
			fos.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

}
