package edu.smith.cs.csc212.spooky;

/**
 * This class represents a GameTime system for the SpookyMansion game
 * 
 * @author MackieZhou
 *
 */
public class GameTime {

	/**
	 * the hour of the day
	 */
	private int hour;

	/**
	 * total time you have spent in the game
	 */
	private int total;

	/**
	 * Create a GameTime object. Initially, both total and hour are set to 0.
	 */
	public GameTime() {
		this.total = 0;
		this.hour = 0;
	}

	/**
	 * getter method for the hour of the day
	 *
	 * @return hour - the hour of the day
	 */
	public int getHour() {
		this.hour = this.total % 12;
		return this.hour;
	}

	/**
	 * getter method for the total time spent in the game.
	 * 
	 * @return total - total time spent in the game
	 */
	public int getTotal() {
		return this.total;
	}

	/**
	 * calculate AM or PM using total
	 * 
	 * @return string -- "AM" or "PM"
	 */
	public String amOrpm() {
		if (this.total % 24 < 12) {
			return "AM";
		} else {
			return "PM";
		}
	}

	/**
	 * calculate if it's day or night, it's day from 7-18
	 * 
	 * @return true/false
	 */
	public boolean isNightTime() {
		int hour24 = this.total % 24;
		if (hour24 < 7 || hour24 > 18) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * increase the hour of the day by 1
	 */
	public void increaseHour() {
		this.total++;
	}

}
