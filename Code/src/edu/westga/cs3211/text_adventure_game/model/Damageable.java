package edu.westga.cs3211.text_adventure_game.model;

import java.util.List;

/**
 * The damagable abstract class
 * @author Colby
 * @version Fall 2024
 */
public abstract class Damageable { 
	private int health;
	private int damage;
	private Boolean isDead;
	private List<Item> inventory;
	
	/**
	 * Creates an instance of the damageable class
	 * @param health the health of the character
	 * @param startingItems the starting items of the character
	 * @postcondition: this.health == health, this.isDead == false, this.inventory == startingItems
	 */
	public Damageable(int health, List<Item> startingItems) {
		this.health = health;
		this.isDead = false;
		this.inventory = startingItems;
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
	 */
	public void setHealth(int health) {

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
	
	/**
	 * Gets the inventory
	 * @return the inventory
	 */
	public List<Item> getInventory() {
		return this.inventory;
	}
	
	/**
	 * Gets the total weight
	 * @return the total weight of the inventory
	 */
	public int getTotalWeight() {
		int totalWeight = 0;
		for (Item item : this.inventory) {
			totalWeight += item.getWeight();
		}
		return totalWeight;
	}
	
	/**
	 * Gets the damage
	 * @return the damage
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * Sets the Damage
	 * @param damage the damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
