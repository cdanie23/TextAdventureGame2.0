package edu.westga.cs3211.text_adventure_game.model.test.useitem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Player;
import edu.westga.cs3211.text_adventure_game.model.UseItem;

class TestTakeAction {
	private ArrayList<Item> startingItems;
	private Item item;
	private Location location;
	private Player player;
	@BeforeEach
	void setupAction() {
		startingItems = new ArrayList<Item>();
		item = new Item("Healing Potion", 10, 50, 10);
		startingItems.add(item);
		player = new Player(startingItems);
	}
	@Test
	void testTakeAction() {
		
		
		UseItem useItem = new UseItem(item);
		
		player.setHealth(30);
		useItem.takeAction(player, location);
		
		assertEquals(player.getHealth(), 80);
	}
	
	@Test
	void testTakeActionWhenHealthIsFull() {
		
		UseItem useItem = new UseItem(item);
		
		useItem.takeAction(player, location);
		
		assertEquals(player.getHealth(), 100);
	}
}
