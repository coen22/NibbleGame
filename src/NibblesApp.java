
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import nibbles.*;

public class NibblesApp {

	public static void main(String[] args) {
		int width = 70;
		int height = 50;
		int squaresize = 10;
		
		GameWindow window = new GameWindow(width,height,squaresize);
		
		//allObjects stores all objects participating in the game
		ArrayList<FieldObject> allObjects = new ArrayList<FieldObject>();
		
		allObjects.add(new Wall(0,0,width-1,0));
		allObjects.add(new Wall(0,0,0,height-1));
		allObjects.add(new Wall(0,height-1,width-1,height-1));
		allObjects.add(new Wall(width-1,0,width-1,height-1));
		allObjects.add(new Wall(10,height/2,width-10,height/2));
		
		// Initialize snake 1
		KeyBuffer buf1 = new KeyBuffer();
		buf1.registerInterest(KeyEvent.VK_UP);
		buf1.registerInterest(KeyEvent.VK_DOWN);
		buf1.registerInterest(KeyEvent.VK_LEFT);
		buf1.registerInterest(KeyEvent.VK_RIGHT);
		window.registerKeyBuffer(buf1);
		int[] list1 = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT};
		allObjects.add(new Snake(width/10,height/4,MovableFieldObject.RIGHT,Color.YELLOW,list1,buf1));
		
		// Initialize snake 2
		KeyBuffer buf2 = new KeyBuffer();
		buf2.registerInterest(KeyEvent.VK_W);
		buf2.registerInterest(KeyEvent.VK_S);
		buf2.registerInterest(KeyEvent.VK_A);
		buf2.registerInterest(KeyEvent.VK_D);
		window.registerKeyBuffer(buf2);
		int[] list2 = {KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D};
		allObjects.add(new Snake(width-width/10,height-height/4,MovableFieldObject.LEFT,Color.MAGENTA,list2,buf2));

		// Initialize 2 connected teleporters (Uncomment to test your Teleporter implementation)
//		Teleporter t1 = new Teleporter(width/10,height-height/4);
//		Teleporter t2 = new Teleporter(width-width/10,height/4);
//		t1.coupleWith(t2);
//		allObjects.add(t1);
//		allObjects.add(t2);
		
		// Initialize 4 connected magic portals (Uncomment to test your MagicPortal implementation)
//		MagicPortal m1 = new MagicPortal(width/3,height/3);
//		MagicPortal m2 = new MagicPortal(width-width/3,height/3);
//		MagicPortal m3 = new MagicPortal(width/3,height-height/3);
//		MagicPortal m4 = new MagicPortal(width-width/3,height-height/3);
//		m1.addCoupling(m2);
//		m1.addCoupling(m3);
//		m1.addCoupling(m4);
//		m2.addCoupling(m3);
//		m2.addCoupling(m4);
//		m3.addCoupling(m4);
//		allObjects.add(m1);
//		allObjects.add(m2);
//		allObjects.add(m3);
//		allObjects.add(m4);
		
		// Initialize the aliens (Uncomment to test your Alien implementation)
//		allObjects.add(new Alien(width/2,height/10,MovableFieldObject.DOWN));
//		allObjects.add(new Alien(width/2,height-height/10,MovableFieldObject.UP));
	
		//Food MUST be initialized last, so it can find itself a random open cell
		allObjects.add(new Food(width,height,allObjects));
		
		//Draw all objects ready to start and wait till the user(s) is(are) ready too.
		for (FieldObject fo: allObjects)
			fo.draw(window);
		JOptionPane.showMessageDialog(window,"Click OK when ready.");
		
		// Two stopping conditions
		// 1. all snakes are dead
		Boolean gameOver = false;
		// 2. all numbers 1-9 eaten
		Boolean won = false;
		
		//Finally, the game loop ...
		while (!gameOver && !won) {
			try {Thread.sleep(100);} catch (Exception e) {};
			
			//Check for player input
			for (FieldObject snake: allObjects)
				if (snake instanceof Snake)
					((Snake)snake).checkDirectionChanges();
			
			//Check for collisions
			for (FieldObject fo: allObjects)
				if (fo instanceof MovableFieldObject) {
					MovableFieldObject mov = (MovableFieldObject) fo;
					if (mov.isAlive()) {
						Coordinate target = mov.computeNextStep();
						for (FieldObject other: allObjects)
							if (other.occupies(target))
								mov.handleFrontalCollision(other);
					}
				}
			
			//Move all living objects
			for (FieldObject fo: allObjects)
				if (fo instanceof MovableFieldObject) {
					MovableFieldObject mov = (MovableFieldObject) fo;
					if (mov.isAlive()) mov.move();
				}
			
			//Draw the changes 
			for (FieldObject fo: allObjects)
				fo.draw(window);
			window.update();
			
			//Check for living snakes
			gameOver = true;
			for (FieldObject snake: allObjects)
				if (snake instanceof Snake)
					gameOver = gameOver && !((Snake)snake).isAlive();
			
			//Check for finished level
			for (FieldObject f: allObjects) {
				if (f instanceof Food)
					won = ((Food)f).getValue() == 10;
			}
		}
		
		if (gameOver)
			JOptionPane.showMessageDialog(window,"Game Over. Try again. (Are you done implementing yet?).");
		else
			JOptionPane.showMessageDialog(window,"Well done! (Are you done implementing yet?).");
		
		System.exit(0);
		
	}

}

