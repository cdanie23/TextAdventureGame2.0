package edu.westga.cs3211.text_adventure_game.model.test.location;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.Npc;

class TestAddNpc {

	 @Test
	    public void testAddNpcValid() {
	    	String name = "Creaky Castle Gate";
			String description = "The creaky castle gates";
			ArrayList<Action> actions = new ArrayList<Action>();
			actions.add(new Move(Direction.Forward));
			HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
			adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
			LocationType locationType = LocationType.Safe;
			
			Location location = new Location(name, description, actions, adjacentLocations, locationType);
	    	Npc npc = new Npc("TestNpc", 0, 0, 0);
	        location.addNpc(npc); 
	        assertEquals(true, location.getNpcs().contains(npc), "The NPC should be added to the location.");
	    }

		@Test
	    public void testAddNpcNullThrowsException() {
			String name = "Creaky Castle Gate";
			String description = "The creaky castle gates";
			ArrayList<Action> actions = new ArrayList<Action>();
			actions.add(new Move(Direction.Forward));
			HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
			adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
			LocationType locationType = LocationType.Safe;
			
			Location location = new Location(name, description, actions, adjacentLocations, locationType);
	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            location.addNpc(null); // Attempt to add null NPC
	        });
	        assertEquals("NPC cannot be null", exception.getMessage());
	    }

}
