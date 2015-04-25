package nibbles;
import java.awt.Color;
import java.util.ArrayList;


public class Food extends FieldObject {

	protected int value;
	protected int width;
	protected int height;
	protected ArrayList<FieldObject> allObjects;
	
	
	public Food(int width, int height, ArrayList<FieldObject> allObjects) {
		this.width = width;
		this.height = height;
		this.allObjects = allObjects;
		this.value = 1;
		this.body = new ArrayList<Coordinate>();
		Coordinate target = findEmptyLocation(width, height, allObjects);
		body.add(target);
	}

	@Override
	public boolean handleCollision(MovableFieldObject mov) {
		mov.eat(value);
		value++;
		body.remove(0);
		Coordinate target = findEmptyLocation(width, height, allObjects);
		body.add(target);
		return true;
	}

	@Override
	public void draw(GameWindow gw) {
		gw.drawNumber(body.get(0).getX(), body.get(0).getY(), Color.GREEN, value);
	}
	
	private Coordinate findEmptyLocation(int width, int height, ArrayList<FieldObject> allObjects) {
		Coordinate target = null;
		boolean foundSpot = false;
		while (!foundSpot) {
			target = new Coordinate((int)(Math.random()*(width-1)),(int)(Math.random()*(height-1)));
			boolean occupied = false;
			for (FieldObject fo: allObjects)
				occupied = occupied || fo.occupies(target);
			foundSpot = !occupied;	
		}
		return target;
	}

	public int getValue() {
		return value;
	}
}

