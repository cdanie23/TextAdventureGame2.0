package edu.westga.cs3211.text_adventure_game.model;

/**
 * The actionable item class
 * 
 * @author Colby
 * @version Fall 2024
 */
public abstract class ActionableItem extends Action {
	private Item item;

	/**
	 *  Creates an instance of the drop item
	 * @param selectedItem the selected item
	 * @param description the description of the item
	 * @postcondition: this.item == selectedItem
	 */
	public ActionableItem(Item selectedItem, String description) {
		super(description + selectedItem.getName());
		this.item = selectedItem;
	}

	/**
	 * Takes the action
	 * @param character the character
	 * @param currLocation the current location of the action
	 * @return true or false based on if the action was completed
	 */
	public abstract Boolean takeAction(Damageable character, Location currLocation);

	/**
	 * Gets the item
	 * @return the item
	 */
	public Item getItem() {
		return this.item;
	}
	
}
