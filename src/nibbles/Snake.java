package nibbles;
import java.awt.Color;
import java.util.ArrayList;


public class Snake extends MovableFieldObject {

	protected int allowedSize = 3;
	protected KeyBuffer keyBuffer;
	protected int[] keyList;
	
	public Snake(int startX, int startY, int direction, Color color, int[] keyList, KeyBuffer keyBuffer) {
		this.shape = FieldObject.CIRCLE;
		this.color = color;
		toClear = new ArrayList<Coordinate>();
		alive = true;
		
		head = new Coordinate(startX,startY);
		body = new ArrayList<Coordinate>();
		body.add(head);
		this.direction = direction;
		this.keyBuffer = keyBuffer;
		this.keyList = keyList;
	}

	@Override
	public void move() {
		Coordinate newHead = computeNextStep();
		body.add(0,newHead);
		
		if (body.size() > allowedSize) {
			toClear.add(body.get(body.size()-1));
			body.remove(body.size()-1);
		}
		
		head = newHead;
	}

	@Override
	public boolean handleFrontalCollision(FieldObject fo) {
		alive = fo.handleCollision(this);
		return false;
	}

	@Override
	public boolean handleCollision(MovableFieldObject mov) {
		return false;
	}

	public void eat(int n) {
		this.allowedSize += n;
	}
	
	public void checkDirectionChanges() {
		if (!keyBuffer.isEmpty())
			setDirection(translateDirection(keyBuffer.getNext()));
	}
	
	private int translateDirection(int keyCode) {
		if (keyCode == keyList[0])
			return MovableFieldObject.UP;
		else if (keyCode == keyList[1])
			return MovableFieldObject.DOWN;
		else if (keyCode == keyList[2])
			return MovableFieldObject.LEFT;
		else // (keyCode == keyList[3])
			return MovableFieldObject.RIGHT;
	}

}
