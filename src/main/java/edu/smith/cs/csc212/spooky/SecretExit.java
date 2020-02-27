package edu.smith.cs.csc212.spooky;

/**
 * The class represents the secret, invisible exits hidden in the game. Search
 * is needed to make them visible.
 * 
 * @author MackieZhou
 *
 */
public class SecretExit extends Exit {
	/**
	 * the status of the exit. Is it visible or secret?
	 */
	private boolean isSecret;

	/**
	 * create a new secretExit
	 * 
	 * @param target      - where it goes.
	 * @param description - how it looks.
	 */
	public SecretExit(String target, String description) {
		super(target, description);
		this.isSecret = true;
	}

	/**
	 * to see if the exit is still secret
	 * 
	 * @return boolean -- if the exit is secret
	 */
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

}
