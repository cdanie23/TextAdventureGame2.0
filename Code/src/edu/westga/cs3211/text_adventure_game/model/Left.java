package edu.westga.cs3211.text_adventure_game.model;

/**
 * The move left action class
 * @author Colby
 * @version Fall 2024
 */
public class Left extends MoveAction {
	
	private static final String DESCRIPTION = "Move Left";

	/**
	 * Creates an instance of the left action
	 * PostCondition: description == "Move Left" direction == Direction.Left
	 */
	public Left() {
		super(DESCRIPTION, Direction.Left);
	}
}
