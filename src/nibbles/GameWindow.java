package nibbles;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameWindow extends JFrame {
	private static final long serialVersionUID = 8214150611122604273L;
	private ArrayList<KeyBuffer> registeredKeyBuffers;
	private FieldPanel panel;

	/**
	 * Creates a tiled window of the gives sizes and displays it on screen. Couples the
	 * window close button with exiting the Java program.  
	 * @param width the width of the window in number of cells
	 * @param height the height of the window in number of cells
	 * @param squaresize the pixel size of a single cell
	 */
	public GameWindow(int width, int height, int squaresize) {

		// init window
		super("Nibbles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// set window location to center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int left = (screenSize.width - width*squaresize)/ 2;
		int top  = (screenSize.height - height*squaresize)/ 2;
		setLocation(left, top);

		panel = new FieldPanel(width,height,squaresize);
		add(panel, BorderLayout.CENTER);

		this.registeredKeyBuffers = new ArrayList<KeyBuffer>();
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				registerKey(e.getKeyCode());
			}
		});

		pack();
		this.setVisible(true);
	}

	protected void registerKey(int k) {
		for (KeyBuffer buffer : registeredKeyBuffers) 
			buffer.register(k);
	}

	/**
	 * Registers a KeyBuffer object that will be notified whenever a key is pressed while the window is active.
	 * @param buf the KeyBuffer to be registered
	 */
	public void registerKeyBuffer(KeyBuffer buf) {
		registeredKeyBuffers.add(buf);
	}

	/**
	 * Readies a square to be draw at the given location in the given color next time update() is called.
	 * @param hx horizontal location of the square (index starts at 0) 
	 * @param vx vertical location of the square (index start at 0)
	 * @param color color of the square
	 */
	public void drawSquare(int hx, int vx, Color color) {
		panel.drawSquare(hx,vx,color);
	}

	/**
	 * Readies a circle to be draw at the given location in the given color next time update() is called.
	 * @param hx horizontal location of the circle (index starts at 0) 
	 * @param vx vertical location of the circle (index start at 0)
	 * @param color color of the circle
	 */
	public void drawCircle(int hx, int vx, Color color) {
		panel.drawCircle(hx,vx,color);
	}
	
	/**
	 * Readies a number to be draw at the given location in the given color next 
	 * time update() is called. The number to be drawn needs to be single digit 
	 * and will not be drawn if it is not.
	 * @param hx horizontal location of the number (index starts at 0)
	 * @param vx vertical location of the number (index start at 0)
	 * @param color color of the number
	 * @param number the number to be drawn
	 */
	public void drawNumber(int hx, int vx, Color color, int number) {
		if ((0<=number)  && (number<10))
			panel.drawNumber(hx,vx,color,number);
	}

	/**
	 * Clears the cell of all content, i.e. sets it back to the background color BLUE
	 * @param hx horizontal location
	 * @param vx vertical location
	 */
	public void clear(int hx, int vx) {
		panel.clear(hx,vx);
	}
	
	/**
	 * Draws the current state of all cells on screen.
	 */
	public void update() {
		panel.update();
	}


	private class FieldPanel extends JPanel {

		private static final long serialVersionUID = 1587595057655180095L;
		private static final int SQUARE = -2;
		private static final int CIRCLE = -1;
		private int squaresize;
		private int width;
		private int height;

		private boolean[][] notEmpty;
		private Color[][] color;
		private int[][] shape;

		protected FieldPanel(int width, int height, int squaresize) {

			this.width = width;
			this.height = height;
			this.squaresize = squaresize;
			this.notEmpty = new boolean[width][height];
			this.shape = new int[width][height];
			this.color = new Color[width][height];

			setPreferredSize(new Dimension(width*squaresize,height*squaresize));

		}

		protected void drawNumber(int i, int j, Color c, int number) {
			notEmpty[i][j] = true;
			shape[i][j] = number;
			color[i][j] = c;
		}

		protected void drawSquare(int i, int j, Color c) {
			notEmpty[i][j] = true;
			shape[i][j] = SQUARE;
			color[i][j] = c;
		}

		protected void drawCircle(int i, int j, Color c) {
			notEmpty[i][j] = true;
			shape[i][j] = CIRCLE;
			color[i][j] = c;
		}

		protected void clear(int i, int j) {
			notEmpty[i][j] = false;
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;

			//fillbackground
			g2.setColor(Color.BLUE);
			g2.fill(getVisibleRect());

			//draw cells
			for (int i=0; i<width; i++)
				for (int j=0; j<height; j++) {
					if (notEmpty[i][j]) {
						g2.setColor(color[i][j]);
						if (shape[i][j] == SQUARE)
							g2.fill(new Rectangle2D.Double(i*squaresize,j*squaresize,squaresize,squaresize));
						else if (shape[i][j] == CIRCLE)
							g2.fill(new Ellipse2D.Double(i*squaresize,j*squaresize,squaresize,squaresize));
						else
							g2.drawString(String.valueOf(shape[i][j]),i*squaresize,(j+1)*squaresize);
					}
				}
		}

		public void update() {
			repaint();
		}

	}

}