package edu.smith.cs.csc212.spooky;

public class GameTime {

	/**
	 * the hour of the day
	 */
	private int hour;

	/**
	 * total time you have spent in the game
	 */
	private int total;

	public void Gametime() {
		this.total = 0;
		this.hour = 0;
	}

	public int getHour() {
		this.hour = this.total % 12;
		return this.hour;
	}

	public int getTotal() {
		return this.total;
	}
	
	/**
	 * calculate am/pm using total
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

	public void increaseHour() {
		this.total++;
	}

}
