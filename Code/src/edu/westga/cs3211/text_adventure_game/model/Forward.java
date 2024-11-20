package edu.westga.cs3211.text_adventure_game.model;

/**
 * The forward action class
 * @author Colby
 * @version Fall 2024
 */
public class Forward extends MoveAction {
	
	private static final String DESCRIPTION = "Move Forward";

	/**
	 * Creates an instance of the forward action
	 * Postcondition: super.description == "Move Forward" && super.direction == Direction.Forward
	 */
	public Forward() {
		super(DESCRIPTION, Direction.Forward);
		
	}
}
