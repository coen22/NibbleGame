package nibbles;

import java.awt.Color;
import java.util.ArrayList;

public class Teleporter extends FieldObject {

	private Teleporter partner;
	
	public Teleporter(int x, int y) {
		color = Color.GREEN;
		body = new ArrayList<Coordinate>();
		body.add(new Coordinate(x, y));
	}
	
	public void coupleWith(Teleporter partner) {
		this.partner = partner;
		partner.setPartner(this);
	}
	
	protected Teleporter getPartner() {
		return partner;
	}

	protected void setPartner(Teleporter partner) {
		this.partner = partner;
	}
	
	protected Coordinate getLocation() {
		return body.get(0);
	}
	
	@Override
	public boolean handleCollision(MovableFieldObject mov) {
		
		mov.toClear.addAll(mov.body);
		mov.body.clear();
		mov.head = new Coordinate(getPartner().getLocation().getX(), getPartner().getLocation().getY());

		return true;
	}

}
