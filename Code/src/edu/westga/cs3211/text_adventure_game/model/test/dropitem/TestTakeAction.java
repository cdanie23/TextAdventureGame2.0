package edu.westga.cs3211.text_adventure_game.model.test.dropitem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Player;

class TestTakeAction {

	@Test
	void testTakeAction() {
		ArrayList<Item> startingItems = new ArrayList<Item>();
		Item dagger = new Item("Dagger", 10, 15, 10);
		startingItems.add(dagger);
		Player player = new Player(startingItems);
		
		DropItem action = new DropItem(player.getInventory().get(0));
		
		action.takeAction(player);
		
		assertEquals(player.getInventory().size(), 0);
		
	}

}
