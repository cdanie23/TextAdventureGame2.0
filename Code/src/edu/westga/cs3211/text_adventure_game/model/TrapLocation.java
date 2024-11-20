package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The trap location class
 * @author Colby
 * @version Fall 2024
 */
public class TrapLocation extends Location {
	
	private int damageInflicted;
	
	/**
	 * Creates an instance of a trap location
	 * @param name the name of the location
	 * @param description the description of the location
	 * @param actions the actions at the location
	 * @param adjacentLocations the adjacent locations of the current location
	 * @param locationType the type of location
	 * @param damageInflicted the amount of damange to inflict
	 * @preconditions: name != null && name.isEmpty() && description != null && description.isEmpty() && actions != null && adjacentLocations != null &&
	 * 				  adjacentLocations.isEmpty() && locationType != null && 0 <= damageInflicted >= 100
	 * @postconditions: this.name == name && this.description == description && this.actions == actions && this.adjacentLocations == adjacentLocations &&
	 * 				   this.locationType == locationType && this.damageInflicted == damageInflicted
	 */
	public TrapLocation(String name, String description, ArrayList<Action> actions, HashMap<Direction, String> adjacentLocations, LocationType locationType, int damageInflicted) {
		super(name, description, actions, adjacentLocations, locationType);
		if (damageInflicted < 0) {
			throw new IllegalArgumentException("damage cannot be negative");
		}
		if (damageInflicted > 100) {
			throw new IllegalArgumentException("damange cannot be greater than 100");
		}
		this.damageInflicted = damageInflicted;
	}
	
	/**
	 * Creates an instance of a trap location
	 * @param name the name of the location
	 * @param description the description of the location
	 * @param locationType the type of location
	 * @param damageInflicted the amount of damage to inflict
	 * @preconditions: name != null && name.isEmpty() && description != null && description.isEmpty() && locationType != null && 0 <= damageInflicted >= 100
	 * @postconditions: this.name == name && this.description == description && this.locationType == locationType && this.damageInflicted == damageInflicted
	 */
	public TrapLocation(String name, String description, LocationType locationType, int damageInflicted) {
		super(name, description, locationType);
		if (damageInflicted < 0) {
			throw new IllegalArgumentException("damage cannot be negative");
		}
		if (damageInflicted > 100) {
			throw new IllegalArgumentException("damange cannot be greater than 100");
		}
		this.damageInflicted = damageInflicted;
	}

	/**
	 * Gets the damange inflicted by the location
	 * @return the damageInflicted
	 */
	public int getDamageInflicted() {
		return this.damageInflicted;
	}
}
