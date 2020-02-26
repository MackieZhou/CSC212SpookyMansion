package edu.smith.cs.csc212.spooky;

public class SecretExit extends Exit {

	public SecretExit(String target, String description) {
		super(target, description);
	}
	
	@Override
	public boolean isSecret() {
		return false;
	}
	
	@Override
	public void search() {
		
	}
	
	@Override
	public boolean canOpen(Player player) {
		
	}

}
