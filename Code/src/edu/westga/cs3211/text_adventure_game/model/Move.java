package edu.westga.cs3211.text_adventure_game.model;

import java.util.HashMap;
import java.util.List;

/**
 * The move action class
 * @author Colby
 * @version Fall 2024
 */
public class Move extends Action {
	
	private static final String MOVE_DESCRIPTION = "Move ";
	private Direction direction;
	
	/**
	 * Creates an instance of a move action
	 * @param direction the direction of the movement
	 * @postcondition: this.direction == direction && super.description == description
	 */
	public Move(Direction direction) {
		super(MOVE_DESCRIPTION + direction.toString());
		this.direction = direction;
	}
	
	/**
	 * Gets the direction
	 * @return the direction
	 */
	
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * Takes the move action
	 * @param adjacentLocations the adjacent locations of the current location
	 * @param allLocations all the locations in the game
	 * @return the new location
	 */
	public Location takeAction(HashMap<Direction, String> adjacentLocations, List<Location> allLocations) {
		Location newLocation = null;
		String nameOfNewLocation = adjacentLocations.get(this.direction);
		
		for (Location location : allLocations) {
			if (location.getName().equals(nameOfNewLocation)) {
				newLocation = location;
				break;
			}
		}
		Location currLocation = newLocation;
		return currLocation;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + this.direction.toString();
	}

}
