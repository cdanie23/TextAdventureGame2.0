package edu.westga.cs3211.text_adventure_game.model.test.npc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.ItemReader;
import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Npc;
import edu.westga.cs3211.text_adventure_game.model.NpcManager;

public class NpcManagerTest {

    private NpcManager npcManager;
    private ArrayList<Npc> npcPool;
    private Location location;
    private List<Item> itemPool;
    private ItemReader itemReader;
    @BeforeEach
    public void setUp() {
        this.npcPool = new ArrayList<>();
        npcPool.add(new Npc("Goblin", 10, 50, 100));
        npcPool.add(new Npc("Merchant", 5, 30, 80));
        npcPool.add(new Npc("Chest", 0, 0, 0));
        
        File itemFile = new File("Items.txt");
        this.itemReader = new ItemReader(itemFile);
        this.itemPool = this.itemReader.readItems();
       
        
        String name = "Forest";
        String description = "A dense forest filled with mystery.";
        ArrayList<Action> actions = new ArrayList<>();
        
        HashMap<Direction, String> adjacentLocations = new HashMap<>();
        adjacentLocations.put(Direction.Forward, "Village");
        adjacentLocations.put(Direction.Left, "Cave");
        adjacentLocations.put(Direction.Right, "Lake");
        adjacentLocations.put(Direction.Backward, "Mountain");

        LocationType locationType = LocationType.Safe;
        
        location = new Location(name, description, actions, adjacentLocations, locationType);
        
        npcManager = new NpcManager(npcPool, itemPool);
    }

    @Test
    public void testAddNpcByIndex() {
        npcManager.addNpcByIndex(0, 2, location, 2);
        assertEquals(2, location.getNpcs().size(), "NPCs should be added to the location.");
        assertEquals(2, location.getActions().size(), "Actions should be added to the location.");
    }

    @Test
    public void testAddNpcByIndexInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> npcManager.addNpcByIndex(10, 1, location, 2),
                "Invalid index should throw IllegalArgumentException.");
    }

    @Test
    public void testAddNpcByIndexInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () -> npcManager.addNpcByIndex(0, -1, location, 2),
                "Amount must be greater than 0.");
    }

    @Test
    public void testAddRandomNpcs() {
        npcManager.addRandomNpcs(3, location);
        assertEquals(3, location.getNpcs().size(), "3 random NPCs should be added to the location.");
                assertEquals(3, location.getActions().size(), "3 actions should be added to the location.");
    }

    @Test
    public void testAddRandomNpcsInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () -> npcManager.addRandomNpcs(0, location),
                "Amount must be greater than 0.");
    }

    @Test
    public void testNpcManagerInvalidNpcPool() {
        assertThrows(IllegalArgumentException.class, () -> new NpcManager(null, List.of()),
                "NPC pool cannot be null or empty.");
        assertThrows(IllegalArgumentException.class, () -> new NpcManager(new ArrayList<>(), List.of()),
                "NPC pool cannot be null or empty.");
    }

    @Test
    public void testAdjacentLocations() {
        assertTrue(location.getAdjacentLocations().containsKey(Direction.Forward));
        assertTrue(location.getAdjacentLocations().containsKey(Direction.Left));
        assertTrue(location.getAdjacentLocations().containsKey(Direction.Right));
        assertTrue(location.getAdjacentLocations().containsKey(Direction.Backward));
        
        assertEquals("Village", location.getAdjacentLocations().get(Direction.Forward));
        assertEquals("Cave", location.getAdjacentLocations().get(Direction.Left));
        assertEquals("Lake", location.getAdjacentLocations().get(Direction.Right));
        assertEquals("Mountain", location.getAdjacentLocations().get(Direction.Backward));
    }

    @Test
    public void testLocationType() {
        assertEquals(LocationType.Safe, location.getLocationType(), "The location type should be 'Safe'.");
    }
    
    @Test
    void testAddRandomItemsToNpc() {
    	this.npcManager.addRandomNpcs(2, location);
    	
    	Npc npcOne = location.getNpcs().get(0);
    	Npc npcTwo = location.getNpcs().get(1);
    	
    	assertTrue(npcOne.getItems().size() > 0);
    	assertTrue(npcTwo.getItems().size() > 0);
    }
}
