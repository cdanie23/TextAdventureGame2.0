package edu.westga.cs3211.text_adventure_game.model;

/**
 * The player class
 * @author Colby
 * @version Fall 2024
 */
public class Player {
	private int health;
	private Boolean isDead;
	
	/**
	 * Creates an instance of a player
	 * @postcondition: this.health == 100
	 */
	public Player() {
		this.health = 100;
		this.isDead = false;
	}

	/**
	 * Gets the health of the player
	 * @return the health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Sets the health of the player
	 * @param health the health to set
	 * @precondition health >= 0 && health <= 100
	 */
	public void setHealth(int health) {
		if (health < 0) {
			throw new IllegalArgumentException("health cannot be negative");
		}
		if (health > 100) {
			throw new IllegalArgumentException("health cannot be greater than 100");
		}
		this.health = health;
		if (this.health <= 0) {
			this.isDead = true;
		}
	}
	/**
	 * Gets if the player is dead
	 * @return true or false based on the players health
	 */
	
	public Boolean getIsDead() {
		return this.isDead;
	}
	
	/**
	 * Sets if the player is dead or alive
	 * @param isDead the value to set if player is dead or not
	 * @precondition isDead != null
	 * @postcondition this.isDead == isDead
	 */
	public void setIsDead(Boolean isDead) {
		if (isDead == null) {
			throw new IllegalArgumentException("is dead cannot be null");
		}
		this.isDead = isDead;
	}
}
