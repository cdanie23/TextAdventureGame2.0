package edu.westga.cs3211.text_adventure_game.model.test.npc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Damageable;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Npc;
import edu.westga.cs3211.text_adventure_game.model.NpcInteract;
import edu.westga.cs3211.text_adventure_game.model.Player;

public class NpcInteractTest {
    private Npc npc;
    private Damageable character;

    @BeforeEach
    public void setUp() {
        this.npc = new Npc("Goblin", 10, 50, 100);
        this.character = new Player(new ArrayList<Item>());
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
        String result = chestInteraction.takeAction(character);
        assertEquals("You looted the chest!", result);
       
      
       assertTrue(character.getInventory().contains(sword));

       assertEquals(0, chestNpc.getHealth());
    }

    @Test
    public void testLootChestAlreadyLooted() {
        Npc chestNpc = new Npc("Chest", 10, 50, 100);
        chestNpc.addItem(new Item("Sword", 5, 5, 5));  // Add an item to the chest
        chestNpc.setHealth(0);  // Mark the chest as already looted
        NpcInteract chestInteraction = new NpcInteract(chestNpc, "Loot the chest");

        String result = chestInteraction.takeAction(character);
        assertEquals("The chest has already been looted.", result);
    }

    @Test
    public void testTradeWithMerchant() {
        Npc merchantNpc = new Npc("Merchant", 10, 50, 100);
        NpcInteract merchantInteraction = new NpcInteract(merchantNpc, "Trade with the merchant");

        String result = merchantInteraction.takeAction(character);
        assertEquals("You successfully traded with the merchant.", result);
    }

    @Test
    public void testFightNpcKill() {
        Npc goblinNpc = new Npc("Goblin", 10, 50, 100);
        NpcInteract fightInteraction = new NpcInteract(goblinNpc, "Fight the goblin");
        character.setDamage(100);
        String result = fightInteraction.takeAction(character);
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
        String result = fightInteraction.takeAction(character);
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

        String result = fightInteraction.takeAction(character);
        assertEquals("The NPC is already dead.", result);
    }
    
    @Test
    public void testFightNpcWithWeapon() {
    	Npc goblinNpc = new Npc("Goblin", 10, 50, 100);
        NpcInteract fightInteraction = new NpcInteract(goblinNpc, "Fight the goblin");
    }
}
