package nibbles;


public class Coordinate {

	private final int x,y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object o) {
		Coordinate c = (Coordinate)o;
		return ((x==c.getX()) && (y==c.getY()));
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
