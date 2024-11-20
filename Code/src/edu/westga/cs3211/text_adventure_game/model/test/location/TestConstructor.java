package edu.westga.cs3211.text_adventure_game.model.test.location;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Forward;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;

class TestConstructor {

	@Test
	void testValidConstructor() {
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Forward());
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		Location location = new Location(name, description, actions, adjacentLocations, locationType);
		
		assertEquals(location.getName(), name);
		assertEquals(location.getDescription(), description);
		assertEquals(location.getActions(), actions);
		assertEquals(location.getAdjacentLocations(), adjacentLocations);
		assertEquals(location.getLocationType(), locationType);
		
	}
	
	@Test
	void testInvalidName() {
		String name = null;
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Forward());
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		assertThrows(IllegalArgumentException.class, () -> new Location(name, description, actions, adjacentLocations, locationType));
	}
	@Test
	void testInvalidDescription() {
		String name = "Creaky Castle Gate";
		String description = null;
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Forward());
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		assertThrows(IllegalArgumentException.class, () -> new Location(name, description, actions, adjacentLocations, locationType));
	}
	
	@Test
	void testInvalidActions() {
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = null;
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		assertThrows(IllegalArgumentException.class, () -> new Location(name, description, actions, adjacentLocations, locationType));
		
	}
	
	@Test
	void testInvalidLocationType() {
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Forward());
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = null;
		
		assertThrows(IllegalArgumentException.class, () -> new Location(name, description, actions, adjacentLocations, locationType));
	}
	
	@Test
	void testInvalidAdjacentLocations() {
		String name = "Creaky Castle Gates";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Forward());
		HashMap<Direction, String> adjacentLocations = null;
		LocationType locationType = LocationType.Safe;
		
		assertThrows(IllegalArgumentException.class, () -> new Location(name, description, actions, adjacentLocations, locationType));
	}
	@Test
	void testNoAdjacentLocationsValidConstructor() {
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		LocationType locationType = LocationType.Safe;
		
		Location location = new Location(name, description, locationType);
		
		assertEquals(location.getName(), name);
		assertEquals(location.getDescription(), description);
		assertEquals(location.getLocationType(), locationType);
	}

}
