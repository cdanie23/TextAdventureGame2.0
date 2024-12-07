package edu.westga.cs3211.text_adventure_game.model;

/**
 * The item class
 * 
 * @author Colby
 * @version Fall 2024
 */
public class Item {
	private String name;
	private int weight;
	private int effect;
	private int price;

	/**
	 * Creates an instance of an item
	 * 
	 * @param name   the name
	 * @param weight the weight
	 * @param effect the effect
	 * @param price  the price
	 * @preconditions: name!= null, !name.IsEmpty(), weight <= 100, weight > 0,
	 *                 price > 0, price <= 100
	 * @postconditions: this.name == name, this.weight == weight, this.effect ==
	 *                  effect, this.price == price
	 */
	public Item(String name, int weight, int effect, int price) {

		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}
		if (weight > 100) {
			throw new IllegalArgumentException("weight cannot be greater than 100");
		}
		if (weight < 0) {
			throw new IllegalArgumentException("weight cannot be negative");
		}
		if (price < 0) {
			throw new IllegalArgumentException("price cannot be negative");
		}
		if (price > 100) {
			throw new IllegalArgumentException("price cannot be greater than 100");
		}
		
		this.name = name;
		this.weight = weight;
		this.effect = effect;
		this.price = price;
	}

	/**
	 * Gets the name
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the weight
	 * @return the weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Gets the effect
	 * @return the effect
	 */
	public int getEffect() {
		return this.effect;
	}

	/**
	 * Gets the price
	 * @return the price
	 */
	public int getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
