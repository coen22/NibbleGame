package nibbles;

import java.awt.Color;

public class Teleporter extends FieldObject {

	private Teleporter partner;
	
	public Teleporter(int x, int y) {
		color = Color.GREEN;
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
		// TODO Auto-generated method stub
		return false;
	}

}
