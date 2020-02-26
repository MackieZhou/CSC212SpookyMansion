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
			System.out.println(here.getDescription());

			// check if you've been here before
			Set<String> visited = player.getVisited();
			if (visited.contains(here.getId())) {
				here.visited = true;
//				System.out.println(here.getId()+" visited = "+here.visited);
				System.out.println(">>> This place look familiar...");
			}
			player.saveMemory(player.getPlace());

			// Game over after print!
			if (here.isTerminalState()) {
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

			if (action.equals("quit") || action.equals("q") || action.equals("escape")) {
				if (input.confirm("Are you sure you want to quit?")) {
					// quit!
					break;
				} else {
					// go to the top of the game loop!
					continue;
				}
			}

			// To get help playing the game
			String instruction = "\nTo quit game: type \"quit\", \"escape\", or \"q\"\n"
					+ "To see the instruction again: type \"help\"\n"
					+ "To another room: type in the number of the room you'd like to go";
			if (action.equals("help")) {
				System.out.println(instruction);
				continue;
			}

			// To check if the user is searching for invisible exits
			// All ways out of this place, including the hidden ones
			List<Exit> allExits = here.getExits();
//			System.out.println(here.getExits());

			if (action.equals("search")) {
				for (int i = 0; i < allExits.size(); i++) {
					Exit e = allExits.get(i);
//					System.out.println(e.isSecret);
					if (e instanceof SecretExit) {
						e.search();
					}
//					System.out.println(e.isSecret + "\n");
				}
				continue;
			}

//			// debug
//			List<Exit> newVisibleExits = here.getVisibleExits();
//			System.out.println(newVisibleExits);

			// From here on out, what they typed better be a number!
			Integer exitNum = null;
			try {
				exitNum = Integer.parseInt(action);
			} catch (NumberFormatException nfe) {
				System.out.println("That's not something I understand! Try a number!");
				continue;
			}

			if (exitNum < 0 || exitNum >= allExits.size()) {
				System.out.println("I don't know what to do with that number!");
				continue;
			}

			// Move to the room they indicated.
			Exit destination = allExits.get(exitNum);
			if (destination.canOpen(player)) {
				player.moveTo(destination.getTarget());
			} else {
				// TODO: some kind of message about it being locked?
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
