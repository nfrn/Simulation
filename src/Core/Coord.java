package Core;

public class Coord {
	private double x;
	private double y;
	public Coord(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	public void incCoordX(double inc) {
		setX(getX() + inc);
	}
	public void incCoordY(double inc) {
		setY(getY() + inc);
	}
	public void decCoordX(double inc) {
		setX(getX() - inc);
	}
	public void decCoordY(double inc ) {
		setY(getY() - inc);
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
}