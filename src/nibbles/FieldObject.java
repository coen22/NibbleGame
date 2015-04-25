package nibbles;
import java.awt.Color;
import java.util.ArrayList;


public abstract class FieldObject {

	public static final int SQUARE = 0;
	public static final int CIRCLE = 1;
	
	protected ArrayList<Coordinate> body;
	protected int shape;
	protected Color color;
	
	public abstract boolean handleCollision(MovableFieldObject mov);
	
	public void draw(GameWindow gameWindow) {
		for (Coordinate c: body) {
			if (shape == SQUARE)
				gameWindow.drawSquare(c.getX(), c.getY(), color);
			else if (shape == CIRCLE)
				gameWindow.drawCircle(c.getX(), c.getY(), color);
		}
	}

	public boolean occupies(Coordinate target) {
		return body.contains(target);
	}
	
}
