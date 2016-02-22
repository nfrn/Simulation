package Core;

import java.util.Map;
import java.util.TreeMap;

public class Population {
	private TreeMap<String,Element>_population = new TreeMap<String,Element>();
	private TreeMap<String,Element>_food = new TreeMap<String,Element>();
	
	boolean died = false;
	
	private String generation = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private int index=0;
	private String mode;
	
	private ElementFactory factory = new ElementFactory();
	
	private int indSize;
	public void setInd(int indSize){
		for(int i = 0; i< indSize; i++){
			String indName;
			if (i>9){
				indName = generation.charAt(index) + ""+i;
			}else{
				indName = generation.charAt(index) + "0"+i;
			}
			_population.put(indName,factory.createElement("I",indName));
		}
	}
	
	public Population(int indSize, int foodSize){
		this.indSize=indSize;
		for(int i = 0; i< foodSize; i++){
			String foodName;
			if (i>9){
				foodName = generation.charAt(index) + ""+i;
			}else{
				foodName = generation.charAt(index) + "0"+i;
			}
			_food.put(foodName,factory.createElement("F",foodName));
		}
		setInd(indSize);
	}

	public void reset(){
		_population.clear();
		setInd(indSize);
	}
	public void update(){
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			if(entry.getValue().isAlive()){
				if(mode.equals("Grid Mode")){
					entry.getValue().moveGrid();
				}
				else if(mode.equals("Free mode")){
					entry.getValue().moveFree();
				}
				entry.getValue().incAge();
				entry.getValue().decLife();
				if(entry.getValue().getLife()==0){
					entry.getValue().setAlive(false);
				}
			}
		}
		updateDied();
	}
	public void updateDied(){
		this.died=true;
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			if(entry.getValue().isAlive()){
				this.died=false;
				break;
			}
		}
		
	}

	public String toString(){
		String population = "<html><div style=\"text-align: center;\">";
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			population += entry.getValue().toString();
			population += "<br>";
		}
		return population + "</html>";
	}

	public char getGeneration() {
		return generation.charAt(index);
	}
	public void nextGeneration() {
		index+=1;
		reset();
	}

	public TreeMap<String, Element> get_population() {
		return _population;
	}

	public TreeMap<String, Element> get_food() {
		return _food;
	}
	
	public void clear(){
		_population.clear();
		_food.clear();
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	
	public void setFocus(String name){
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			if(entry.getValue().isAlive()){
				if(entry.getValue().getName().equals(name)){
					entry.getValue().setFocused(true);
				}
			}
		}
	}
	public void clearFocus(String name){
		for(Map.Entry<String, Element> entry : _population.entrySet()){
			if(entry.getValue().isAlive()){
				if(entry.getValue().getName().equals(name)){
					entry.getValue().setFocused(false);
				}
			}
		}
	}
	public boolean isDied() {
		return died;
	}

	public void setDied(boolean died) {
		this.died = died;
	}
}

