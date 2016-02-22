package Core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Individual extends Element{
	public static final int SIZE = 8;
	private int life = 100;
	private  double angle; 

	private ArrayList<Genetic> genetics= new ArrayList<Genetic>(); 

	public Individual(String name, Coord coord, Double angle){
		setName(name);
		setAlive(true);
		setAge(0);
		setCoord(coord);
		this.angle= angle;
		
		for(int i = 0; i<SIZE; i++){
			genetics.add(new Genetic());
		}
		if(focused){
			try {
			setImage(image = ImageIO.read(new File("src/Assets/Down_F.png")));
			} catch (IOException e) {e.printStackTrace();}
		}
		else{
			try {
			setImage(image = ImageIO.read(new File("src/Assets/Down.png")));
			} catch (IOException e) {e.printStackTrace();}
		}
	}

	public void moveGrid(){
		Random random = new Random();
		int move = random.nextInt(100)+1;  
		if(move <= genetics.get(0).getGenetic()){
			int direction = random.nextInt(4)+1;
			switch(direction){
				case(1):
					if(getCoord().getY()==01){
						direction = random.nextInt(4)+1;
					}
					else{
						getCoord().decCoordY(1);
						if(focused){
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Top_F.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
						else{
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Top.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
					}
					break;
				case(2):
					if(getCoord().getX()==59){
						direction = random.nextInt(4)+1;
					}
					else{
						getCoord().incCoordX(1);
						if(focused){
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Right_F.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
						else{
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Right.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
					}
					break;
				case(3):
					if(getCoord().getY()==25){
						direction = random.nextInt(4)+1;
					}
					else{
						getCoord().incCoordY(1);
						if(focused){
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Down_F.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
						else{
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Down.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
					}
					break;
				case(4):
					if(getCoord().getX()==01){
						direction = random.nextInt(4)+1;
					}
					else{
						getCoord().decCoordX(1);
						if(focused){
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Left_F.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
						else{
							try {
							setImage(image = ImageIO.read(new File("src/Assets/Left.png")));
							} catch (IOException e) {e.printStackTrace();}
						}
					}
					break;
				}
		}
	}
	
	public void moveFree(){
		Random random = new Random();
		
		int move = random.nextInt(100)+1;  
		
		if(move <= genetics.get(0).getGenetic()){
			
			int direction = random.nextInt(2)+1; //1-left 2-right
			
			while(true){
				switch(direction){
					case(1):
						angle += Math.toRadians(30);
					case(2):
						angle -= Math.toRadians(30);
				}
				
				if(angle >= Math.toRadians(360)){
					angle -= Math.toRadians(360);
				}
				if(angle <= Math.toRadians(0)){
					angle += Math.toRadians(360);
				}
				
				double incX = Math.cos(angle);
				double incY = - Math.sin(angle);
			
				if(angle<=Math.toRadians(180)){
					if( (int)(getCoord().getY()+incY) >=1  && (int)(getCoord().getX()+ incX) >=1 && (int)(getCoord().getX()+ incX) <=58){
						System.out.println("N");
						getCoord().incCoordY(incY);
						getCoord().incCoordX(incX);
						try {
							image = ImageIO.read(new File("src/Assets/Top.png"));
						} catch (IOException e) {e.printStackTrace();}
						break;
					}
				}
				else if(angle<=Math.toRadians(360)){
					if( (int)(getCoord().getY()+incY) <=24  &&(int) (getCoord().getX()+ incX) <=58 && (int)(getCoord().getX()+ incX) >=1 ){
						System.out.println("S");
						getCoord().incCoordY(incY);
						getCoord().incCoordX(incX);
						try {
							image = ImageIO.read(new File("src/Assets/Down.png"));
						} catch (IOException e) {e.printStackTrace();}
						break;
					}
				}
				direction = random.nextInt(2)+1;
			}
			System.out.println(Math.toDegrees(angle) +": " +getCoord().getX() + " : " + getCoord().getY());
		}
	}
	
	public String toString(){
		String ind = getName() +"," + String.format("%03d", getAge())+ ":";
		for(Genetic g :genetics){
			ind += g.toString() +"|";
		}
		return ind+"\n" ;
	}
	
	public int getLife(){
		return life;
	}
	public void decLife(){
		this.life--;
	}
	public void incLife(int value) {
		this.life+=value;
	}
}
