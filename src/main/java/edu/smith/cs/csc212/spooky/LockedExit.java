package edu.smith.cs.csc212.spooky;

public class LockedExit extends Exit {
	
	private boolean canOpen;
	
	private String key;
	
	
	public LockedExit(String target, String description, String key) {
		super(target, description);
		this.canOpen = false;
		this.key = key;
	}
	
	/**
	 * 
	 * @return key -- what key is needed to unlock the exit?
	 */
	public String getKey() {
		return this.key;
	}
	
	@Override
	public boolean canOpen(Player player) {
		return this.canOpen;
	}
	
	public void unlock() {
		this.canOpen = true;
	}

}
