package nibbles;
import java.util.ArrayList;


public class KeyBuffer {

	private ArrayList<Integer> interest;
	private ArrayList<Integer> memory;
	
	/**
	 * Creates a new KeyBuffer
	 */
	public KeyBuffer() {
		interest = new ArrayList<Integer>();
		memory = new ArrayList<Integer>();
	}
	
	protected void register(int k) {
		if (interest.contains(k))
			memory.add(k);
	}
	
	/**
	 * Reads out the first unread key and removes it from the buffer
	 * @return the KeyCode of the first unread key, or -1 if the buffer was empty
	 */
	public int getNext() {
		if (memory.isEmpty())
			return -1;
		else {
			int key = memory.get(0);
			memory.remove(0);
			return key;
		}
	}

	/**
	 * Registers a keycode as interesting.  From this point onwards, the buffer will 
	 * remember keypresses of the key with the listed code (on top of all previously 
	 * registered keycodes).
	 * @param k keycode of the key to be remembered
	 */
	public void registerInterest(int k) {
		interest.add(k);
	}
	
	/**
	 * Checks if the KeyBuffer is empty.
	 * @return true is there are no more remembered keypresses
	 */
	public boolean isEmpty() {
		return memory.isEmpty();
	}
	
}
