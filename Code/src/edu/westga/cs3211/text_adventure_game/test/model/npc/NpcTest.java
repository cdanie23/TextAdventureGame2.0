package edu.westga.cs3211.text_adventure_game.test.model.npc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Npc;

import java.util.List;

public class NpcTest {
    private Npc npc;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        this.npc = new Npc("Goblin", 10, 50, 100);

        this.item1 = new Item("Sword", 5, 5, 5);
        this.item2 = new Item("Shield", 3, 3, 3);

        this.npc.addItem(item1);
        this.npc.addItem(item2);
    }

    @Test
    public void testNpcCreation() {
        assertEquals("Goblin", npc.getName());
        assertEquals(10, npc.getCoinDropMin());
        assertEquals(50, npc.getCoinDropMax());
        assertEquals(100, npc.getHealth());
    }

    @Test
    public void testGetItems() {
        List<Item> items = npc.getItems();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));
    }

    @Test
    public void testAddItem() {
        Item newItem = new Item("Potion", 1, 1, 1);
        npc.addItem(newItem);
        assertTrue(npc.getItems().contains(newItem));
    }

    @Test
    public void testRemoveItem() {
        boolean result = npc.removeItem(item1);
        assertTrue(result);
        assertFalse(npc.getItems().contains(item1));

        result = npc.removeItem(new Item("Helmet", 1, 1, 1));
        assertFalse(result);
    }

    @Test
    public void testGetRandomCoinDrop() {
        int coinDrop = npc.getRandomCoinDrop();
        assertTrue(coinDrop >= npc.getCoinDropMin() && coinDrop <= npc.getCoinDropMax());
    }

    @Test
    public void testToString() {
        String expected = "Npc[name=Goblin, coinDropMin=10, coinDropMax=50, health=100]";
        assertEquals(expected, npc.toString());
    }
    
    @Test
    public void testGetHealth() {
        assertEquals(100, npc.getHealth());
    }

    @Test
    public void testSetItems() {
        List<Item> newItems = List.of(new Item("Bow", 4, 4, 4), new Item("Arrow", 2, 2, 2));
        npc.setItems(newItems);

        assertEquals(newItems, npc.getItems());
    }
}