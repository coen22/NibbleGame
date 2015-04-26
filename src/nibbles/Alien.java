package nibbles;

import java.awt.Color;
import java.util.ArrayList;

public class Alien extends MovableFieldObject {

	public Alien(int x, int y, int dir) {
		color = new Color(231, 76, 60);
		shape = CIRCLE;

		alive = true;
		toClear = new ArrayList<Coordinate>();
		body = new ArrayList<Coordinate>();
		body.add(new Coordinate(x, y));
		head = new Coordinate(x, y);
	}
	
	public void move() {
		super.move();
	}
	
	@Override
	public boolean handleFrontalCollision(FieldObject fo) {
		if (fo instanceof Snake)
			((Snake) fo).alive = false;
		
		switch (direction) {
		case DOWN:
			setDirection(UP);
			break;
		case UP:
			setDirection(DOWN);
			break;
		case LEFT:
			setDirection(RIGHT);
			break;
		default:
			setDirection(LEFT);
			break;
		}

		return false;
	}

	@Override
	public boolean handleCollision(MovableFieldObject mov) {
		switch (direction) {
		case DOWN:
			setDirection(UP);
			break;
		case UP:
			setDirection(DOWN);
			break;
		case LEFT:
			setDirection(RIGHT);
			break;
		default:
			setDirection(LEFT);
			break;
		}

		return false;
	}

	@Override
	public void eat(int n) {
		
	}
}
