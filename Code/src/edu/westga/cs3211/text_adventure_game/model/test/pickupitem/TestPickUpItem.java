package edu.westga.cs3211.text_adventure_game.model.test.pickupitem;

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
import edu.westga.cs3211.text_adventure_game.model.PickUpItem;
import edu.westga.cs3211.text_adventure_game.model.Player;

class TestPickUpItem {
	private PickUpItem pickUpItem;
	private Item item;
	Location location;
	Player player;
	ArrayList<Item> startingItems;
	@BeforeEach
	void setupAction() {
		item = new Item("Healing Potion", 10, 50, 10);
		pickUpItem = new PickUpItem(item);
		
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		location = new Location(name, description, actions, adjacentLocations, locationType);
		
		startingItems = new ArrayList<Item>();
		startingItems.add(item);
		player = new Player(startingItems);
	}
	@Test
	void testValidConstructor() {
		assertEquals(pickUpItem.getItem(), item);
		assertEquals(pickUpItem.getDescription(), "Pick Up " + item.getName());
	}
	@Test
	void testTakeAction() {
		pickUpItem.takeAction(player, location);
		
		assertTrue(player.getInventory().contains(item));
		
	}

}
