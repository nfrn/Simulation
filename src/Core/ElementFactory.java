package Core;

import java.util.Random;

public class ElementFactory {
	public Element createElement(String type, String name){
		Random random = new Random();
		Element element = null;
		if(type.equals("I")){
			Coord coord = new Coord(random.nextInt(58)+1,random.nextInt(24)+1);
			double angle = Math.toRadians(random.nextDouble()*360);
			return new Individual(name,coord,angle);
		}else
		if(type.equals("F")){
			Coord coord = new Coord(random.nextInt(58)+1,random.nextInt(24)+1);
			return new Food(name, coord);
		}
		return element;			
	}
}
