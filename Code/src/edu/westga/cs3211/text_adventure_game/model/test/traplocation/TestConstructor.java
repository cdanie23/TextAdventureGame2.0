package edu.westga.cs3211.text_adventure_game.model.test.traplocation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.TrapLocation;

class TestConstructor {

	@Test
	void testValidConstructor() {
		String name = "Creaky Castle Trap Room";
		String description = "The creaky castle trap room";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Trap;
		int damageInflicted = 100;
		
		TrapLocation location = new TrapLocation(name, description, actions, adjacentLocations, locationType, damageInflicted);
		
		assertEquals(location.getName(), name);
		assertEquals(location.getDescription(), description);
		assertEquals(location.getActions(), actions);
		assertEquals(location.getAdjacentLocations(), adjacentLocations);
		assertEquals(location.getLocationType(), locationType);
		assertEquals(location.getDamageInflicted(), 100);
	}
	
	@Test
	void testWhenDamageIsNegative() {
		String name = "Creaky Castle Trap Room";
		String description = "The creaky castle trap room";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Trap;
		int damageInflicted = -1;
		
		assertThrows(IllegalArgumentException.class, () -> new TrapLocation(name, description, actions, adjacentLocations, locationType, damageInflicted));
		
		
	}
	
	@Test
	void testWhenDamageIsGreaterThan100() {
		String name = "Creaky Castle Trap Room";
		String description = "The creaky castle trap room";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Trap;
		int damageInflicted = 101;
		
		assertThrows(IllegalArgumentException.class, () -> new TrapLocation(name, description, actions, adjacentLocations, locationType, damageInflicted));
		
		
	}
	
	@Test
	void testTrapLocationWithNoAdjacentLocations() {
		String name = "Creaky Castle Trap Room";
		String description = "The creaky castle trap room";
		LocationType locationType = LocationType.Trap;
		int damageInflicted = 100;
		
		TrapLocation location = new TrapLocation(name, description, locationType, damageInflicted);
		
		assertEquals(location.getName(), name);
		assertEquals(location.getDescription(), description);

		assertEquals(location.getLocationType(), locationType);
		assertEquals(location.getDamageInflicted(), 100);
	}
	
	@Test
	void testTrapLocationWithNoAdjacentLocationsDamageGreaterThan100() {
		String name = "Creaky Castle Trap Room";
		String description = "The creaky castle trap room";
		LocationType locationType = LocationType.Trap;
		int damageInflicted = 101;
		
		assertThrows(IllegalArgumentException.class, () -> new TrapLocation(name, description, locationType, damageInflicted)); 
		
	}
	
	@Test
	void testTrapLocationWithNoAdjacentLocationsDamageIsNegative() {
		String name = "Creaky Castle Trap Room";
		String description = "The creaky castle trap room";
		LocationType locationType = LocationType.Trap;
		int damageInflicted = -1;
		
		assertThrows(IllegalArgumentException.class, () -> new TrapLocation(name, description, locationType, damageInflicted)); 
		
	}

}
