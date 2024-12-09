package edu.westga.cs3211.text_adventure_game.model;

import java.util.List;

/**
 * The player class
 * @author Colby
 * @version Fall 2024
 */
public class Player extends Damageable {
	private int coins;
	
	/**
	 * Creates an instance of a player
	 * @param startingItems the starting items of the player
	 * @postcondition: this.health == 100, this.isDead == false, this.inventory != null, this.isEncumbered == false
	 */
	
	public Player(List<Item> startingItems) {
		super(100, startingItems);
		this.coins = 0;
		this.setDamage(10);
	}
	/**
	 * Gets if the player is encumbered
	 * @return true or false based on if the players inventory weight exceeds the total game weight
	 */
	
	public Boolean isEncumbered() {
		return GameManager.MAX_WEIGHT <= this.getTotalWeight();
	}
	/**
	 * Gets the coins
	 * @return the coins
	 */
	
	public int getCoins() {
		return this.coins;
	}
	/**
	 * Sets the coins
	 * @param coins the coins to set
	 */
	
	public void setCoins(int coins) {
		this.coins = coins;
	}
}
