package edu.westga.cs3211.text_adventure_game.test.model.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.ActionableItem;
import edu.westga.cs3211.text_adventure_game.model.Damageable;
import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.PickUpItem;
import edu.westga.cs3211.text_adventure_game.model.Player;
import edu.westga.cs3211.text_adventure_game.model.UseItem;

class TestUsePlayerActionableItem {
	
	 private GameManager gameManager;
	    private Player player;

	    @BeforeEach
	    public void setUp() {
	        gameManager = new GameManager();
	        player = gameManager.getPlayer();
	        player.setHealth(50); // Default health for tests
	        player.getInventory().clear(); // Clear inventory for a clean slate
	    }

	    @Test
	    void testValidAction() {
	        // Add a healing potion to the player's inventory
	        Item healingPotion = new Item("Healing Potion", 10, 50, 10);
	        player.getInventory().add(healingPotion);

	        // Create a UseItem action
	        UseItem useItem = new UseItem(healingPotion);

	        // Execute the action
	        gameManager.usePlayerActionableItem(useItem);

	        // Assert health was restored and potion removed
	        assertEquals(100, player.getHealth(), "Player's health should be restored to 100");
	        assertFalse(player.getInventory().contains(healingPotion), "Potion should be removed from inventory");
	        assertEquals("You have used Healing Potion", gameManager.getItemStatus());
	    }

	    @Test
	    void testInvalidAction() {
	        // Attempt to use a null action
	        UseItem useItem = null;

	        // Assert an exception is thrown
	        assertThrows(IllegalArgumentException.class, () -> {
	            gameManager.usePlayerActionableItem(useItem);
	        }, "Null action should throw IllegalArgumentException");
	    }

	    @Test
	    void testPickUpAction() {
	        // Create a new item and a pick-up action
	        Item item = new Item("Healing Potion", 10, 50, 10);
	        PickUpItem pickUpPotion = new PickUpItem(item);

	        // Execute the action
	        gameManager.usePlayerActionableItem(pickUpPotion);

	        // Assert item was added to inventory and status updated
	        assertEquals(1, player.getInventory().size(), "Inventory should contain the picked-up item");
	        assertTrue(player.getInventory().contains(item), "Picked-up item should be in inventory");
	        assertEquals("You have picked up Healing Potion", gameManager.getItemStatus());
	    }

	    @Test
	    void testDropAction() {
	        // Add an item to the inventory and create a drop action
	        Item item = new Item("Old Sword", 5, 10, 1);
	        player.getInventory().add(item);
	        DropItem dropItem = new DropItem(item);

	        // Execute the action
	        gameManager.usePlayerActionableItem(dropItem);

	        // Assert item was removed from inventory and status updated
	        assertFalse(player.getInventory().contains(item), "Dropped item should be removed from inventory");
	        assertEquals("You have dropped Old Sword", gameManager.getItemStatus());
	    }

	    @Test
	    void testUseItemWithoutEffect() {
	        // Create a non-potion item and add to inventory
	        Item sword = new Item("Sword", 10, 20, 5);
	        player.getInventory().add(sword);

	        // Create a UseItem action
	        UseItem useItem = new UseItem(sword);

	        // Execute the action
	        boolean result = gameManager.usePlayerActionableItem(useItem);

	        // Assert inventory remains unchanged and status updated
	        assertTrue(result, "Action should complete successfully");
	        assertTrue(player.getInventory().contains(sword), "Non-potion item should remain in inventory");
	        assertEquals("You have used Sword", gameManager.getItemStatus());
	    }

	    @Test
	    void testActionThatFails() {
	        // Create a mock action that fails
	        ActionableItem mockFailingAction = new ActionableItem( new Item("Broken Item", 0, 0, 0), "Description") {

				@Override
				public Boolean takeAction(Damageable character, Location currLocation) {
					return false;
				}
	        };

	        // Execute the action
	        boolean result = gameManager.usePlayerActionableItem(mockFailingAction);

	        // Assert the action failed and no changes were made
	        assertFalse(result, "Action should fail");
	        assertEquals(null, gameManager.getItemStatus());
	    }
}
