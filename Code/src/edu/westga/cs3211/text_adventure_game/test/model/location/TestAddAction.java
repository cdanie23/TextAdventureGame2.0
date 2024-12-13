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

class TestAddAction {

	@Test
    public void testAddActionValid() {
        String name = "Creaky Castle Gate";
        String description = "The creaky castle gates";
        ArrayList<Action> actions = new ArrayList<Action>();
        HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
        adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
        LocationType locationType = LocationType.Safe;

        Location location = new Location(name, description, actions, adjacentLocations, locationType);
        Action action = new Move(Direction.Backward); 

        location.addAction(action); 
        assertEquals(true, location.getActions().contains(action), "The action should be added to the location.");
    }

    @Test
    public void testAddActionNullThrowsException() {
        String name = "Creaky Castle Gate";
        String description = "The creaky castle gates";
        ArrayList<Action> actions = new ArrayList<Action>();
        HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
        adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
        LocationType locationType = LocationType.Safe;

        Location location = new Location(name, description, actions, adjacentLocations, locationType);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            location.addAction(null); 
        });
        assertEquals("Action cannot be null", exception.getMessage());
    }

}
