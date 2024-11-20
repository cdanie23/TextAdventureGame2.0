package edu.westga.cs3211.text_adventure_game.model;

/**
 * The backward move action class
 * @author Colby
 * @version Fall 2024
 */
public class Backward extends MoveAction {
	
	private static final String DESCRIPTION = "Move Backward";

	/**
	 * Creates an instance of the backward move action
	 * Postcondition: description == "Move Backward" && direction == Direction.Backward
	 */
	public Backward() {
		super(DESCRIPTION, Direction.Backward);
	}

}
