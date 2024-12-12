package edu.westga.cs3211.text_adventure_game.model;

/**
 * 	Pick up item action class
 * @author Colby
 * @version Fall 2024
 */
public class PickUpItem extends ActionableItem {
	/**
	 * Creates an instance of the pick up item class
	 * @param selectedItem the item to pick up 
	 */
	public PickUpItem(Item selectedItem) {
		super(selectedItem, "Pick Up ");
		
	}

	@Override
	public Boolean takeAction(Damageable character, Location currLocation) {
		if ((character.getTotalWeight() + super.getItem().getWeight()) <= GameManager.MAX_WEIGHT) {
			currLocation.getActions().remove(this);
			return character.getInventory().add(super.getItem());
		}
		return false;
	}
}
