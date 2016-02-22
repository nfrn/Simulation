package Core;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Food extends Element{
	public Food(String name,Coord coord){
		setName(name);
		setAlive(true);
		setCoord(coord);
		try {
			setImage(image = ImageIO.read(new File("src/Assets/Meat.png")));
		} catch (IOException e) {e.printStackTrace();}
	}
	public void moveGrid(){}
	public void moveFree(){}
	public int getLife() {return 0;}
	public void decLife() {}
	public void incLife(int value) {}
}

