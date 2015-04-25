package nibbles;
import java.util.ArrayList;


public abstract class MovableFieldObject extends FieldObject {

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	protected int direction;
	protected Coordinate head;
	protected ArrayList<Coordinate> toClear;
	protected boolean alive;
	
	public abstract boolean handleFrontalCollision(FieldObject fo);
	
	public Coordinate computeNextStep() {
		Coordinate newHead;
		if (direction == UP)
			newHead = new Coordinate(head.getX(),head.getY()-1);
		else if (direction == DOWN)
			newHead = new Coordinate(head.getX(),head.getY()+1);
		else if (direction == LEFT)
			newHead = new Coordinate(head.getX()-1,head.getY());
		else // (direction = RIGHT
			newHead = new Coordinate(head.getX()+1,head.getY());
		
		return newHead;
	}
	
	public void move() {
		Coordinate newHead = computeNextStep();
		body.add(0,newHead);
		toClear.add(body.get(body.size()-1));
		body.remove(body.size()-1);
		head = newHead;
	}
	
	public abstract void eat(int n);
	
	public void setDirection(int direction) {
		this.direction = direction; 
	}
	
	@Override
	public void draw(GameWindow gameWindow) {
		super.draw(gameWindow);
		for (Coordinate c: toClear)
			gameWindow.clear(c.getX(), c.getY());
		toClear.clear();
	}

	public boolean isAlive() {
		return alive;
	}

}
