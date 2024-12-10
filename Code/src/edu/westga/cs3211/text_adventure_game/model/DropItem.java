package edu.westga.cs3211.text_adventure_game.model;

/**
 * The drop item action
 * @author Colby
 * @version Fall 2024
 */

public class DropItem extends ActionableItem {
	
	/**
	 *  Creates an instance of the drop item
	 * @param selectedItem the selected item
	 * @postcondition: this.item == selectedItem
	 */
	public DropItem(Item selectedItem) {
		super(selectedItem, "Drop ");
	}

	@Override
	public Boolean takeAction(Damageable character, Location currLocation) {
		PickUpItem itemPickUp = new PickUpItem(super.getItem());
		currLocation.addAction(itemPickUp);
		return character.getInventory().remove(super.getItem());
	}

}
