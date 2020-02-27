package edu.smith.cs.csc212.spooky;

/**
 * This class represents the LockedExits that need a special key to be unlocked
 * 
 * @author MackieZhou
 *
 */
public class LockedExit extends Exit {

	/**
	 * the status of the exit, if it's locked or can be open
	 */
	private boolean canOpen;

	/**
	 * the key needed for this locked exit
	 */
	private String key;

	/**
	 * create a new LockedExit
	 * 
	 * @param target      - where it goes.
	 * @param description - how it looks.
	 * @param key         - the key needed to open this locked exit
	 */
	public LockedExit(String target, String description, String key) {
		super(target, description);
		this.canOpen = false;
		this.key = key;
	}

	/**
	 * a getter for the key needed
	 * 
	 * @return key - the key needed to unlock the exit
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * get the status of the exit
	 * 
	 * @return boolean - if this exit is open or a key is needed
	 */
	@Override
	public boolean canOpen(Player player) {
		return this.canOpen;
	}

	/**
	 * unlock the exit
	 */
	public void unlock() {
		this.canOpen = true;
	}

}
