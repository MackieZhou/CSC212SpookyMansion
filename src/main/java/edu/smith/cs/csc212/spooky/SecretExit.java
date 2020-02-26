package edu.smith.cs.csc212.spooky;

public class SecretExit extends Exit {
	
	private boolean isSecret;

	public SecretExit(String target, String description) {
		super(target, description);
		this.isSecret = true;
	}
	
	@Override
	public boolean isSecret() {
		return this.isSecret;
	}
	
	/**
	 * if this exit is searched, it's visibility is changed into visible
	 */
	@Override
	public void search() {
		this.isSecret = false;
	}
	
//	@Override
//	public boolean canOpen(Player player) {
//		return true;
//	}

}
