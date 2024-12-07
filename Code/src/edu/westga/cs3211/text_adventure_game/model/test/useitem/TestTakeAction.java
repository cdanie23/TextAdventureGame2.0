package edu.westga.cs3211.text_adventure_game.model.test.useitem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Player;
import edu.westga.cs3211.text_adventure_game.model.UseItem;

class TestTakeAction {

	@Test
	void testTakeAction() {
		ArrayList<Item> startingItems = new ArrayList<Item>();
		Item item = new Item("Healing Potion", 10, 50, 10);
		startingItems.add(item);
		Player player = new Player(startingItems);
		
		UseItem useItem = new UseItem(item);
		
		player.setHealth(30);
		useItem.takeAction(player);
		
		assertEquals(player.getHealth(), 80);
	}
	
	@Test
	void testTakeActionWhenHealthIsFull() {
		ArrayList<Item> startingItems = new ArrayList<Item>();
		Item item = new Item("Healing Potion", 10, 50, 10);
		startingItems.add(item);
		Player player = new Player(startingItems);
		UseItem useItem = new UseItem(item);
		
		useItem.takeAction(player);
		
		assertEquals(player.getHealth(), 100);
	}
}
