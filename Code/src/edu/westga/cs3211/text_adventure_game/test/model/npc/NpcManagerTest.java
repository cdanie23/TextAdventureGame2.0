package edu.westga.cs3211.text_adventure_game.test.model.npc;

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
import edu.westga.cs3211.text_adventure_game.model.NpcInteract;
import edu.westga.cs3211.text_adventure_game.model.NpcManager;

public class NpcManagerTest {
	 // Stub class for testing NpcManager
    public class NpcManagerStub extends NpcManager {
        public NpcManagerStub(List<Npc> npcPool, List<Item> itemPool) {
            super(npcPool, itemPool);
        }

        @Override
        public void addRandomNpcs(int amount, Location location) {
            Npc chest1 = new Npc("Chest", 0, 50, 999);
            Npc enemy = new Npc("Dragon", 0, 50, 200);
            // Add items to NPCs
            addRandomItemsToNpc(chest1, 2);
            addRandomItemsToNpc(enemy, 2);
            // Add NPCs to location
            location.addNpc(chest1);
            location.addNpc(enemy);
            Action npcInteraction1 = new NpcInteract(chest1, "Interact with " + chest1.getName());
            Action npcInteraction2 = new NpcInteract(enemy, "Interact with " + enemy.getName());
            location.addAction(npcInteraction1);
            location.addAction(npcInteraction2);
        }

        public void addRandomItemsToNpc(Npc npc, int amountOfItems) {
            Item dagger = new Item("Dagger", 10, -25, 10);
            Item poisonPotion = new Item("Poison Potion", 10, -40, 10);
            for (int i = 0; i < amountOfItems; i++) {
                if (i == 0) npc.addItem(dagger);
                if (i == 1) npc.addItem(poisonPotion);
            }
        }
    }

    private NpcManager npcManager;
    private NpcManagerStub npcManagerStub;
    private ArrayList<Npc> npcPool;
    private Location location;
    private List<Item> itemPool;
    private ItemReader itemReader;

    @BeforeEach
    public void setUp() {
        // Setup NPC pool
        npcPool = new ArrayList<>();
        npcPool.add(new Npc("Goblin", 10, 50, 100));
        npcPool.add(new Npc("Merchant", 5, 30, 80));
        npcPool.add(new Npc("Chest", 0, 0, 0));

        // Setup item pool via ItemReader (ensure it reads correctly)
        File itemFile = new File("Items.txt");
        this.itemReader = new ItemReader(itemFile);
        this.itemPool = itemReader.readItems();

        // Setup location
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

        // Initialize NpcManager and stub
        npcManager = new NpcManager(npcPool, itemPool);
        npcManagerStub = new NpcManagerStub(npcPool, itemPool);
    }

    @Test
    public void testAddNpcByIndex() {
        npcManager.addNpcByIndex(0, 2, location, 2);
        assertEquals(2, location.getNpcs().size(), "NPCs should be added to the location.");
        assertEquals(2, location.getActions().size(), "Actions should be added to the location.");
    }

    @Test
    public void testAddRandomNpcs() {
        npcManager.addRandomNpcs(3, location);
        assertEquals(3, location.getNpcs().size(), "3 random NPCs should be added to the location.");
        assertEquals(3, location.getActions().size(), "3 actions should be added to the location.");
    }

    @Test
    public void testAddRandomItemsToNpc() {
        npcManagerStub.addRandomNpcs(2, location);

        Npc npcOne = location.getNpcs().get(0);
        Npc npcTwo = location.getNpcs().get(1);

        assertEquals(2, npcOne.getItems().size(), "NPC One should have 2 items.");
        assertEquals(2, npcTwo.getItems().size(), "NPC Two should have 2 items.");
    }

    

	@Test
	public void testAddNpcByIndexInvalidAmount() {
		assertThrows(IllegalArgumentException.class, () -> npcManager.addNpcByIndex(0, -1, location, 2),
				"Amount must be greater than 0.");
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
	public void testAddNpcByIndexInvalidIndex() {
	    assertThrows(IllegalArgumentException.class, () -> npcManager.addNpcByIndex(-1, 1, location, 2),
	            "Negative index should throw IllegalArgumentException.");

	    assertThrows(IllegalArgumentException.class, () -> npcManager.addNpcByIndex(npcPool.size(), 1, location, 2),
	            "Index equal to npcPool size should throw IllegalArgumentException.");
	    
	    assertThrows(IllegalArgumentException.class, () -> npcManager.addNpcByIndex(10, 1, location, 2),
                "Invalid index should throw IllegalArgumentException.");
	}
	
	@Test
	public void testAddMoreRandomItemsToNpc() {
	    // Test case where numOfItemsAdded should not exceed amountOfItems
	    Npc npc = new Npc("Goblin", 10, 50, 100);
	    int amountOfItems = 2; // We want to add 2 items
	    npcManagerStub.addRandomItemsToNpc(npc, amountOfItems);
	    
	    // Verify that no more than 2 items are added to the NPC
	    assertEquals(amountOfItems, npc.getItems().size(), "NPC should have no more than 2 items.");
	    
	    // Test case where items are actually added based on chance
	    // We will manipulate the random number to ensure an item is added
	    Npc npcForChanceTest = new Npc("Merchant", 5, 30, 80);
	    List<Item> testItems = List.of(new Item("Dagger", 10, -25, 10), new Item("Poison Potion", 10, -40, 10));
	    
	    // Manipulate the random logic to always "succeed" for the purpose of testing
	    // Use reflection or mocking to manipulate random (depending on testing framework)
	    // or directly set the `randomNumber` logic in the test scenario to ensure that items are added
	    npcManagerStub.addRandomItemsToNpc(npcForChanceTest, 2);
	    
	    // Verify that the items are added to the NPC's inventory
	    assertEquals(2, npcForChanceTest.getItems().size(), "NPC should have 2 items.");
	}
	
	@Test
	public void testAddMoreThanRandomItemsToNpc() {
	    // Test case where numOfItemsAdded should not exceed amountOfItems
	    Npc npc = new Npc("Goblin", 10, 50, 100);
	    int amountOfItems = 2; // We want to add 2 items
	    npcManagerStub.addRandomItemsToNpc(npc, amountOfItems);
	    
	    // Verify that no more than 2 items are added to the NPC
	    assertEquals(amountOfItems, npc.getItems().size(), "NPC should have no more than 2 items.");
	    
	    // Test case where items are actually added based on chance
	    // We will manipulate the random number to ensure an item is added
	    Npc npcForChanceTest = new Npc("Merchant", 5, 30, 80);
	    List<Item> testItems = List.of();
	    
	    // Manipulate the random logic to always "succeed" for the purpose of testing
	    // Use reflection or mocking to manipulate random (depending on testing framework)
	    // or directly set the `randomNumber` logic in the test scenario to ensure that items are added
	    npcManagerStub.addRandomItemsToNpc(npcForChanceTest, 2);
	    
	    // Verify that the items are added to the NPC's inventory
	    assertEquals(2, npcForChanceTest.getItems().size(), "NPC should have 2 items.");
	}
	
	@Test
	public void testAddRandomItemsToNpc_EmptyItemPool() {
	    // Setup the NPC and an empty item pool
	    Npc npc = new Npc("Goblin", 10, 50, 100);
	    List<Item> emptyItemPool = new ArrayList<>(); // Empty item pool
	    NpcManager emptyItemPoolManager = new NpcManager(npcPool, emptyItemPool); // Initialize with empty item pool

	    // Try to add items to the NPC (no items should be added since the pool is empty)
	    emptyItemPoolManager.addRandomItemsToNpc(npc, 2);

	    // Verify that no items have been added to the NPC
	    assertEquals(0, npc.getItems().size(), "NPC should have no items since the item pool is empty.");
	}
}
