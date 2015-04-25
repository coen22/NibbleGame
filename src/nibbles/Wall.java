package nibbles;
import java.awt.Color;
import java.util.ArrayList;


public class Wall extends FieldObject {

	public Wall(int startX, int startY, int stopX, int stopY) {
		
		shape = FieldObject.SQUARE;
		color = Color.RED;
		
		int maxDiff = Math.max(stopX-startX, stopY-startY);
		double xStep = 1.0*(stopX-startX)/maxDiff;
		double yStep = 1.0*(stopY-startY)/maxDiff;
		
		body = new ArrayList<Coordinate>();
		for (int i=0; i<=maxDiff; i++) {
			body.add(new Coordinate((int)(startX+i*xStep),(int)(startY+i*yStep)));
		}
	}
	
	@Override
	public boolean handleCollision(MovableFieldObject mov) {
		return false;
	}

}
