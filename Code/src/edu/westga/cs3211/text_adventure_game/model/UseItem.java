package edu.westga.cs3211.text_adventure_game.model;

/**
 * Use item action
 * @author Colby
 * @version Fall 2024
 */
public class UseItem extends ActionableItem {
	
	/**
	 * Creates an instance of the use item action
	 * @param selectedItem the item to use
	 */
	
	public UseItem(Item selectedItem) {
		super(selectedItem, "Use ");
	}
	  
	@Override
	public Boolean takeAction(Damageable character) {
		var currentHealth = character.getHealth();
		var newHealth = currentHealth + super.getItem().getEffect();
		
		if (character instanceof Player && newHealth > 100) {
			newHealth = 100;
			character.setHealth(newHealth);
			return true;
		}
		character.setHealth(newHealth);
		return true;
	}
	
}


