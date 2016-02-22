package Core;

import java.util.Random;

public class Genetic {
	public int genetic;
	public Genetic(){
		Random random = new Random();
		genetic=random.nextInt(100)+1;
	}
	public String toString(){
		return String.format("%03d", genetic);
	}
	public int getGenetic(){
		return genetic;
	}
}
