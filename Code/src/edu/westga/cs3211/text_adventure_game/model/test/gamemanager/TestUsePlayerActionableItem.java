package edu.westga.cs3211.text_adventure_game.model.test.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.PickUpItem;
import edu.westga.cs3211.text_adventure_game.model.UseItem;

class TestUsePlayerActionableItem {

	@Test
	void testValidAction() {
		GameManager gameManager = new GameManager();
		
		
		UseItem useItem = new UseItem(gameManager.getPlayer().getInventory().get(0));
		
		gameManager.getPlayer().setHealth(50);
		gameManager.usePlayerActionableItem(useItem);
		
		assertEquals(100, gameManager.getPlayer().getHealth());
	}
	
	@Test
	void testInvalidAction() {
		
	GameManager gameManager = new GameManager();
	UseItem useItem = null;
	
	assertThrows(IllegalArgumentException.class, () -> {
		gameManager.usePlayerActionableItem(useItem);
	});
	}
	
	@Test
	void testPickUpAction() {
		GameManager gameManager = new GameManager();
		gameManager.getPlayer().getInventory().clear();
		
		Item item = new Item("Healing Potion", 10, 50, 10);
		PickUpItem pickUpPotion = new PickUpItem(item);
		
		gameManager.usePlayerActionableItem(pickUpPotion);
		
		assertEquals(gameManager.getPlayer().getInventory().size(), 1);
		
		assertEquals(gameManager.getItemStatus(), "You have picked up Healing Potion");
	}
}
