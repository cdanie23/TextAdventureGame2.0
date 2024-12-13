package edu.westga.cs3211.text_adventure_game.test.model.location;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Move;

class TestConstructor {

	@Test
	void testValidConstructor() {
		String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
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
		actions.add(new Move(Direction.Forward));
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
		actions.add(new Move(Direction.Forward));
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
		actions.add(new Move(Direction.Forward));
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
		actions.add(new Move(Direction.Forward));
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
	
	@Test
    public void testLocationConstructor_ValidInput() {
        // Setup
        String name = "Test Location";
        String description = "This is a test location.";
        LocationType locationType = LocationType.Safe; // Assume Safe is a valid LocationType
        
        // Action
        Location location = new Location(name, description, locationType);
        
        // Verify
        assertEquals(name, location.getName(), "The name should be correctly set.");
        assertEquals(description, location.getDescription(), "The description should be correctly set.");
        assertEquals(locationType, location.getLocationType(), "The location type should be correctly set.");
    }

    @Test
    public void testLocationConstructor_NameNull() {
        // Setup
        String name = null;
        String description = "This is a test location.";
        LocationType locationType = LocationType.Safe;

        // Action & Verify
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Location(name, description, locationType);
        });
        assertEquals("name can not be null", exception.getMessage(), "Exception message should be 'name can not be null'");
    }

    @Test
    public void testLocationConstructor_NameBlank() {
        // Setup
        String name = "";
        String description = "This is a test location.";
        LocationType locationType = LocationType.Safe;

        // Action & Verify
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Location(name, description, locationType);
        });
        assertEquals("name can not be black", exception.getMessage(), "Exception message should be 'name can not be black'");
    }

    @Test
    public void testLocationConstructor_DescriptionNull() {
        // Setup
        String name = "Test Location";
        String description = null;
        LocationType locationType = LocationType.Safe;

        // Action & Verify
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Location(name, description, locationType);
        });
        assertEquals("description can not be null", exception.getMessage(), "Exception message should be 'description can not be null'");
    }

    @Test
    public void testLocationConstructor_DescriptionBlank() {
        // Setup
        String name = "Test Location";
        String description = "";
        LocationType locationType = LocationType.Safe;

        // Action & Verify
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Location(name, description, locationType);
        });
        assertEquals("description can not be black", exception.getMessage(), "Exception message should be 'description can not be black'");
    }
}
