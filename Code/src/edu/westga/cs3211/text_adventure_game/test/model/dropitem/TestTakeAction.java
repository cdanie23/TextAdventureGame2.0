package edu.westga.cs3211.text_adventure_game.test.model.dropitem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.Player;

class TestTakeAction {

	@Test
	void testTakeAction() {
		ArrayList<Item> startingItems = new ArrayList<Item>();
		Item dagger = new Item("Dagger", 10, 15, 10);
		startingItems.add(dagger);
		Player player = new Player(startingItems);
		
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		Location location = new Location(name, description, actions, adjacentLocations, locationType);
		DropItem action = new DropItem(player.getInventory().get(0));
		
		action.takeAction(player, location);
		
		assertEquals(player.getInventory().size(), 0);
		
		assertEquals(location.getActions().size(), 2);
		
	}

}
