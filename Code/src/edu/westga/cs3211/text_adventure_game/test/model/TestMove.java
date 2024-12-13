package edu.westga.cs3211.text_adventure_game.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Move;

class TestMove {

	@Test
    void testConstructorWithValidDirection() {
        Direction direction = Direction.Forward;
        Move move = new Move(direction);

        assertEquals(direction, move.getDirection(), "The direction should be correctly set.");
        assertEquals("Move Forward", move.getDescription(), "The description should concatenate 'Move ' with the direction.");
    }
    
    @Test
    void testMoveConstructorAndDirection() {
        // Create a Move action with a Forward direction
        Direction direction = Direction.Forward;
        Move moveAction = new Move(direction);

        // Assert that the direction is correctly set
        assertEquals(direction, moveAction.getDirection(), "Direction should be Forward.");
    }

    @Test
    void testTakeAction() {
        // Prepare sample locations
        Location location1 = new Location("Creaky Castle Gate", "Some description", LocationType.Safe);
        Location location2 = new Location("Creaky Castle Halls", "Another description", LocationType.Safe);
        
        // Create a hashmap for adjacent locations (simulate adjacent locations of the current location)
        HashMap<Direction, String> adjacentLocations = new HashMap<>();
        adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
        
        // List of all locations (could be larger in a real case)
        List<Location> allLocations = new ArrayList<>();
        allLocations.add(location1);
        allLocations.add(location2);
        
        // Create a Move action with a Forward direction
        Move moveAction = new Move(Direction.Forward);

        // Call the takeAction method and get the new location
        Location newLocation = moveAction.takeAction(adjacentLocations, allLocations);

        // Assert that the new location is the expected one (Creaky Castle Halls)
        assertEquals(location2, newLocation, "The new location should be 'Creaky Castle Halls'.");
    }
    
    @Test
    void testTakeActionNoMatchingLocation() {
        // Prepare sample locations
        Location location1 = new Location("Creaky Castle Gate", "Some description", LocationType.Safe);
        
        // Create a hashmap for adjacent locations (simulate adjacent locations of the current location)
        HashMap<Direction, String> adjacentLocations = new HashMap<>();
        adjacentLocations.put(Direction.Forward, "NonExistent Location"); // No such location in the list
        
        // List of all locations (could be larger in a real case)
        List<Location> allLocations = new ArrayList<>();
        allLocations.add(location1); // Creaky Castle Gate
        
        // Create a Move action with a Forward direction
        Move moveAction = new Move(Direction.Forward);

        // Call the takeAction method and get the new location
        Location newLocation = moveAction.takeAction(adjacentLocations, allLocations);

        // Assert that the new location is null since no matching location was found
        assertNull(newLocation, "The new location should be null as no matching location is found.");
    }
    
    @Test
    void testToString() {
        Direction direction = Direction.Right;
        Move move = new Move(direction);

        assertEquals("Move Right", move.toString(), "The toString method should return 'Move <Direction>'.");
    }

}
