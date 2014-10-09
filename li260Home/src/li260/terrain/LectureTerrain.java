package li260.terrain;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class LectureTerrain {
	
	public static Terrain[][] lecture(String filename){
		String s;
		int i=0,j=0,m=0,n=0;
		Terrain[][] tab;
		FileInputStream file;
		InputStreamReader fr;
		BufferedReader in = null;
		try{
			file = new FileInputStream(filename);
			fr = new InputStreamReader(file);
			in = new BufferedReader(fr);		
			s = in.readLine();
			i = Integer.parseInt(s);
			s = in.readLine();
			j = Integer.parseInt(s);
								
		}catch(NumberFormatException e){
			e.printStackTrace();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		tab = new Terrain[j][i];
		for(m=0;m<j;m++){
			try {
				s = in.readLine();
				char[] tabChar = s.toCharArray();
				
				for(n=0;n<i;n++){
					switch(tabChar[n]){
						case 'g' : tab[m][n] = Terrain.Herbe; break;
						case 'b' : tab[m][n] = Terrain.Eau; break;
						case '.' : tab[m][n] = Terrain.Route; break;
						case 'w' : tab[m][n] = Terrain.BandeBlanche; break;
						case 'r' : tab[m][n] = Terrain.BandeRouge; break;
						case '*' : tab[m][n] = Terrain.StartPoint; break;
						case '!' : tab[m][n] = Terrain.EndLine; break;
						default : if (true){ throw new TerrainException("("+m+","+n+")"); }
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			} catch(TerrainException e){
				e.printStackTrace();
			} catch (NullPointerException e){
				System.out.println(i +" "+ j);
				e.printStackTrace();
			}
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tab;
	}
}
			

