package edu.westga.cs3211.text_adventure_game.model;

/**
 * The move action class
 * @author Colby
 * @version Fall 2024
 */
public abstract class MoveAction extends Action {
	
	private Direction direction;
	
	/**
	 * Creates an instance of a move action
	 * @param description the description of the action
	 * @param direction the direction of the movement
	 * @postcondition: this.direction == direction && super.description == description
	 */
	public MoveAction(String description, Direction direction) {
		super(description);
		this.direction = direction;
	}
	
	/**
	 * Gets the direction
	 * @return the direction
	 */
	
	public Direction getDirection() {
		return this.direction;
	}

}
