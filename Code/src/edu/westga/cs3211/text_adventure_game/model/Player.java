package edu.westga.cs3211.text_adventure_game.model;

import java.util.List;

/**
 * The player class
 * @author Colby
 * @version Fall 2024
 */
public class Player extends Damageable {

	/**
	 * Creates an instance of a player
	 * @param startingItems the starting items of the player
	 * @postcondition: this.health == 100, this.isDead == false, this.inventory != null
	 */
	public Player(List<Item> startingItems) {
		super(100, startingItems);
	}
		
}
