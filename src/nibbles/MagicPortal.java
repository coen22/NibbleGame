package nibbles;

import java.awt.Color;
import java.util.ArrayList;

public class MagicPortal extends Teleporter {

	private ArrayList<MagicPortal> partnerList;
	
	public MagicPortal(int x, int y) {
		super(x, y);
		color = new Color(230, 126, 34);
		partnerList = new ArrayList<MagicPortal>();
	}
	
	public void addCoupling(MagicPortal m2) {
		for (MagicPortal mp : partnerList) {
			mp.addPartner(m2);
			m2.addPartner(mp);
		}
		
		addPartner(m2);
		m2.addPartner(this);
	}
	
	protected void addPartner(MagicPortal portal) {
		partnerList.add(portal);
	}
	
	protected MagicPortal getPartner() {
		MagicPortal mp = partnerList.get( (int) (Math.random()*partnerList.size()) );
		
		System.out.println(this + " : " + mp);
		
		return mp;
	}
	
	public String toString() {
		return body.get(0).toString();
	}
}
