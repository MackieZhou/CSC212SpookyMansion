package edu.smith.cs.csc212.spooky;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents all of the game state needed to understand the player.
 * At the beginning of this project, it is just the ID or name of a place.
 * 
 * @author jfoley
 *
 */
public class Player {
	/**
	 * The ID of the Place object where the player is currently.
	 */
	private String place;

	/**
	 * the player's memory
	 */
	private Set<String> visited;

	/**
	 * A player is created at the start of a game with just an initial place.
	 * 
	 * @param initialPlace - where do we start?
	 */
	public Player(String initialPlace) {
		this.place = initialPlace;
		this.visited = new HashSet<>();
	}

	/**
	 * Get access to the place instance variable from outside this class.
	 * 
	 * @return the id of the current location.
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Call this method when the player moves to a new place.
	 * 
	 * @param place - the place we are now located at.
	 */
	public void moveTo(String place) {
		this.place = place;
		
	}
	
	/**
	 * add the place into the player's memory
	 */
	public void saveMemory(String place) {
		this.visited.add(place);
	}

	/**
	 * @return the visited set.
	 */
	public Set<String> getVisited() {
		return this.visited;
	}
}
