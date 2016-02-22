package Core;

import java.awt.image.BufferedImage;

public abstract class Element {
	BufferedImage image ;
	
	
	@SuppressWarnings("unused")
	private int life;
	private boolean alive;
	private String name;
	private Coord coord;
	private int age;
	public boolean focused= false;
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Coord getCoord() {
		return coord;
	}	
	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void incAge(){
		age++;
	}
	
	public abstract void moveGrid();
	public abstract int getLife();
	public abstract void decLife();
	public abstract void incLife(int value);
	public abstract void moveFree();
}
