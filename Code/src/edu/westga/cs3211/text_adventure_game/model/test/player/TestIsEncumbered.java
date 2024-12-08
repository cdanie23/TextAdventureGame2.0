package edu.westga.cs3211.text_adventure_game.model.test.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Player;

class TestIsEncumbered {

	@Test
	void testWhenEncumbered() {
		List<Item> startingItems = new ArrayList<Item>();
		Item dagger = new Item("Dagger", 50, 10, 10);
		Item potion = new Item("Healing Potion", 50, 10, 10);
		startingItems.add(dagger);
		startingItems.add(potion);
		Player player = new Player(startingItems);
		
		assertTrue(player.isEncumbered());
	}
	
	@Test
	void testWhenNotEncumbered() {
		List<Item> startingItems = new ArrayList<Item>();
		Item dagger = new Item("Dagger", 10, 10, 10);
		Item potion = new Item("Healing Potion", 10, 50, 10);
		startingItems.add(dagger);
		startingItems.add(potion);
		Player player = new Player(startingItems);
		
		assertFalse(player.isEncumbered());
	}
}
