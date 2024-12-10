package edu.westga.cs3211.text_adventure_game.model.test.useitem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Item;

import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.Npc;


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
		
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		location = new Location(name, description, actions, adjacentLocations, locationType);
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
	
	@Test
	void testTakeActionWhenNotPlayer() {
		Npc goblinNpc = new Npc("Goblin", 10, 50, 100);
		ArrayList<Item> startingItems = new ArrayList<Item>();
		Item item = new Item("Healing Potion", 10, 50, 10);
		startingItems.add(item);
		goblinNpc.setHealth(10);
		UseItem useItem = new UseItem(item);
		
		useItem.takeAction(goblinNpc, location);
		
		assertEquals(goblinNpc.getHealth(), 60);
	}
}
