package edu.westga.cs3211.text_adventure_game.model.test.npc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.Damageable;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.Move;
import edu.westga.cs3211.text_adventure_game.model.Npc;
import edu.westga.cs3211.text_adventure_game.model.NpcInteract;
import edu.westga.cs3211.text_adventure_game.model.PickUpItem;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class NpcInteractTest {
    private Npc npc;
    private Damageable character;
    private Location location;

    @BeforeEach
    public void setUp() {
        this.npc = new Npc("Goblin", 10, 50, 100);
        this.character = new Player(new ArrayList<Item>());
        
        String name = "Creaky Castle Gate";
		String description = "The creaky castle gates";
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new Move(Direction.Forward));
		HashMap<Direction, String> adjacentLocations = new HashMap<Direction, String>();
		adjacentLocations.put(Direction.Forward, "Creaky Castle Halls");
		LocationType locationType = LocationType.Safe;
		
		location = new Location(name, description, actions, adjacentLocations, locationType);
    }

    @Test
    public void testNpcInteractCreationValid() {
        NpcInteract interaction = new NpcInteract(npc, "Fight the goblin");
        assertEquals("Fight the goblin", interaction.toString());
    }

    @Test
    public void testNpcInteractCreationNullNpc() {
        assertThrows(IllegalArgumentException.class, () -> new NpcInteract(null, "Fight the goblin"));
    }

    @Test
    public void testLootChest() {
        Npc chestNpc = new Npc("Chest", 10, 50, 100);
        Item sword = new Item("Sword", 5, 5, 5);
        chestNpc.addItem(sword);  
        NpcInteract chestInteraction = new NpcInteract(chestNpc, "Loot the chest");
        String result = chestInteraction.takeAction(character, location);
        assertEquals("You looted the chest!", result);
       
      
       assertTrue(location.getActions().size() == 2);

       assertEquals(0, chestNpc.getHealth());
    }

    @Test
    public void testLootChestAlreadyLooted() {
        Npc chestNpc = new Npc("Chest", 10, 50, 100);
        chestNpc.addItem(new Item("Sword", 5, 5, 5));  // Add an item to the chest
        chestNpc.setHealth(0);  // Mark the chest as already looted
        NpcInteract chestInteraction = new NpcInteract(chestNpc, "Loot the chest");

        String result = chestInteraction.takeAction(character, location);
        assertEquals("The chest has already been looted.", result);
    }

    @Test
    public void testTradeWithMerchant() {
        Npc merchantNpc = new Npc("Merchant", 10, 50, 100);
        NpcInteract merchantInteraction = new NpcInteract(merchantNpc, "Trade with the merchant");

        String result = merchantInteraction.takeAction(character, location);
        assertEquals("You successfully traded with the merchant.", result);
    }

    @Test
    public void testFightNpcKill() {
        Npc goblinNpc = new Npc("Goblin", 10, 50, 100);
        NpcInteract fightInteraction = new NpcInteract(goblinNpc, "Fight the goblin");
        character.setDamage(100);
        String result = fightInteraction.takeAction(character, location);
        assertEquals("You defeated the Goblin!", result);
        assertEquals(0, goblinNpc.getHealth());
        int coins =  ((Player)character).getCoins();
        assertEquals(true, coins > 0);
    }
    
    @Test
    public void testFightNpcNotKill() {
        Npc goblinNpc = new Npc("Goblin", 10, 50, 100);
        NpcInteract fightInteraction = new NpcInteract(goblinNpc, "Fight the goblin");
        character.setDamage(10);
        String result = fightInteraction.takeAction(character, location);
        assertEquals("You did not defeat the Goblin! Health: 90", result);
        assertEquals(90, goblinNpc.getHealth());
        int coins =  ((Player)character).getCoins();
        assertEquals(true, coins == 0);
    }

    @Test
    public void testFightNpcAlreadyDead() {
        Npc goblinNpc = new Npc("Goblin", 10, 50, 100);
        goblinNpc.setHealth(0);  // NPC is dead
        NpcInteract fightInteraction = new NpcInteract(goblinNpc, "Fight the goblin");

        String result = fightInteraction.takeAction(character, location);
        assertEquals("The NPC is already dead.", result);
    }
    
    @Test

    void testLootChestThenPickUpItem() {
    	 Npc chestNpc = new Npc("Chest", 10, 50, 100);
         Item sword = new Item("Sword", 5, 5, 5);
         chestNpc.addItem(sword);  
         NpcInteract chestInteraction = new NpcInteract(chestNpc, "Loot the chest");
         String result = chestInteraction.takeAction(character, location);
         assertEquals("You looted the chest!", result);
       
         assertTrue(location.getActions().size() == 2);

         assertEquals(0, chestNpc.getHealth());
         
         ArrayList<Item> startingItems = new ArrayList<Item>();
         startingItems.add(new Item("Potion", 10, 50, 10));
         Player player = new Player(startingItems);
         
         PickUpItem pickUpSword = (PickUpItem) location.getActions().get(1);
         pickUpSword.takeAction(player, location);
         
         assertEquals(player.getInventory().size(), 2);
         
    }
    @Test
    void testLootingAnEmptyChest() {
    	 Npc chestNpc = new Npc("Chest", 10, 50, 100);
       
        
         NpcInteract chestInteraction = new NpcInteract(chestNpc, "Loot the chest");
         String result = chestInteraction.takeAction(character, location);
         assertEquals("You looted an empty chest :(", result);
       
         assertTrue(location.getActions().size() == 1);

         assertEquals(0, chestNpc.getHealth());
    }
    public void testFightNpcWithWeapon() {
    	Npc goblinNpc = new Npc("Goblin", 10, 50, 100);
        NpcInteract fightInteraction = new NpcInteract(goblinNpc, "Fight the goblin");

    }
}
