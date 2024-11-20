package edu.westga.cs3211.text_adventure_game.model;

/**
 * The move right action class
 * @author Colby
 * @version Fall 2024
 */
public class Right extends MoveAction {
	
	private static final String DESCRIPTION = "Move Right";

	/**
	 * Creates an instance of the move right action
	 * @postcondition: description == "Move Right" && direction == Direction.Right
	 */
	public Right() {
		super(DESCRIPTION, Direction.Right);
	}

}
