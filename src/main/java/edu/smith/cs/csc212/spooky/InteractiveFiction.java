package edu.smith.cs.csc212.spooky;

import java.util.List;
import java.util.Set;

/**
 * This is our main class for SpookyMansion. It interacts with a GameWorld and
 * handles user-input. It can play any game, really.
 *
 * @author jfoley
 *
 */
public class InteractiveFiction {

	/**
	 * This method actually plays the game.
	 * 
	 * @param input - a helper object to ask the user questions.
	 * @param game  - the places and exits that make up the game we're playing.
	 * @return where - the place the player finished.
	 */
	static String runGame(TextInput input, GameWorld game) {
		// This is the current location of the player (initialize as start).
		Player player = new Player(game.getStart());

		// This is the time system for the game.
		GameTime time = new GameTime();

		// need help or not? only appear once.
		System.out.println("Type \"help\" to get instruction.");

		// Play the game until quitting.
		// This is too hard to express here, so we just use an infinite loop with
		// breaks.
		while (true) {
			// Print the description of where you are.
			Place here = game.getPlace(player.getPlace());
			System.out.println();
			System.out.println("... --- ...");
			System.out.println(here.getDescription(time));

			// check if you've been here before
			Set<String> visited = player.getVisited();
			if (visited.contains(here.getId())) {
				System.out.println(">>> This place look familiar...");
			}

			// Also tell the player what's here
			here.printAllStuff();

			// print the current time
			if (time.isNightTime()) {
				System.out.println("+++ Current time: " + time.getHour() + time.amOrpm() + ". It's dark out there.");
			} else {
				System.out.println("+++ Current time: " + time.getHour() + time.amOrpm() + ". The sun has risen.");
			}

			// Game over after print!
			if (here.isTerminalState()) {
				System.out.println("\nYou have spent " + time.getTotal() + " hours on this game!");
				break;
			}

			// Show a user the visible ways out of this place.
			List<Exit> visExits = here.getVisibleExits();
			for (int i = 0; i < visExits.size(); i++) {
				Exit e = visExits.get(i);
				System.out.println(" " + i + ". " + e.getDescription());
			}

			// Figure out what the user wants to do
			List<String> words = input.getUserWords("?");
			if (words.size() > 1) {
				System.out.println("Only give the system 1 word at a time!");
				continue;
			}

			// Get the word they typed as lowercase, and no spaces.
			// Do not uppercase action -- I have lowercased it.
			String action = words.get(0).toLowerCase().trim();

			// if the player wants tot quit the game.
			if (action.equals("quit") || action.equals("q") || action.equals("escape")) {
				if (input.confirm("Are you sure you want to quit?")) {
					break;
				} else {
					continue;
				}
			}

			// if the player wants to get help playing the game
			String instruction = "\nTo quit game: type \"quit\", \"escape\", or \"q\"\n"
					+ "To see the instruction again: type \"help\"\n"
					+ "To another room: type in the number of the room you'd like to go";
			if (action.equals("help")) {
				System.out.println(instruction);
				continue;
			}

			// SecretExits are between closet&basement and secretRoom&basement
			// if the player wants to search for invisible exits
			List<Exit> allExits = here.getExits();
			if (action.equals("search")) {
				here.search();
				continue;
			}
			// update the list of visible exits
			List<Exit> newVisExits = here.getVisibleExits();

			// what have you collected?
			// if the player wants to see their collection of stuff.
			if (action.equals("stuff")) {
				if (player.getCollection().size() == 0) {
					System.out.println("You have nothing... keep working...");
				} else {
					System.out.println("You have " + player.getCollection());
				}
				continue;
			}

			// if the player wants to collect all the stuff at this place
			if (action.equals("take")) {
				if (here.getStuff().size() > 0) {
					// if there are something here.
					player.collect(here.getStuff());
					here.taken();
					continue;
				} else {
					// if there's nothing here.
					System.out.println("There's nothing to take!!");
					continue;
				}
			}

			// if the player wants to rest for a while, time increases by 2 hrs
			if (action.equals("rest")) {
				time.increaseHour();
				time.increaseHour();
				continue;
			}

			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= newVisExits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = allExits.get(exitNum);

			// check is the player has the key to unlock the destination
			// the red door(entranceHall to kitchen) is originally locked
			if (destination instanceof LockedExit) {
				String needed = ((LockedExit) destination).getKey();
				if (player.getCollection().contains(needed)) {
					((LockedExit) destination).unlock();
				}
			}

			// move the player to its destination
			if (destination.canOpen(player)) {
				// save the player's memory before move
				player.saveMemory(player.getPlace());
				// move
				player.moveTo(destination.getTarget());
				time.increaseHour();
			} else {
				// if the destination is locked and the player doesn't have the key.
				System.out.println("You cannot unlock that right now. Maybe with a key?");
				continue;
			}
		}

		return player.getPlace();
	}

	/**
	 * This is where we play the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// This is a text input source (provides getUserWords() and confirm()).
		TextInput input = TextInput.fromArgs(args);

		// This is the game we're playing.
		GameWorld game = new SpookyMansion();

		// Actually play the game.
		runGame(input, game);

		// You get here by typing "quit" or by reaching a Terminal Place.
		System.out.println("\n\n>>> GAME OVER <<<");
	}

}
