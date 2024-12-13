package edu.westga.cs3211.text_adventure_game.model;

/**
 * The actions class
 * 
 * @author Colby
 * @version Fall 2024
 */
public abstract class Action {
	private String description;

	/**
	 * Creates an instance of an actions
	 * 
	 * @param description the description of the action Precondition: description !=
	 *                    null && description.isEmpty() && actionType != null
	 *                    Postcondition: this.description == description
	 *                    this.actionType == actionType
	 */
	public Action(String description) {
		if (description == null) {
			throw new IllegalArgumentException("description can not be null");
		}
		if (description.isBlank()) {
			throw new IllegalArgumentException("description can not be blank");
		}
		this.description = description;

	}

	/**
	 * Gets the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}
