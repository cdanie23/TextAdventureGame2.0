package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Non-Playable Character (NPC) in the game.
 * 
 * @author Jacob
 * @version Fall 2024
 */
public class Npc extends Damageable {
    private String name;
    private int coinDropMin;
    private int coinDropMax;
    private List<Item> items;

    /**
     * Creates an instance of an NPC
     * 
     * @param name         the name of the NPC
     * @param coinDropMin  the minimum coin drop value
     * @param coinDropMax  the maximum coin drop value
     * @param health       the health of the NPC
     * @postcondition: this.name == name, this.coinDropMin == coinDropMin,
     *                 this.coinDropMax == coinDropMax, this.getHealth() == health
     */
    public Npc(String name, int coinDropMin, int coinDropMax, int health) {
        super(health, List.of());
        this.setDamage((int) (health * .1));
        this.name = name;
        this.coinDropMin = coinDropMin;
        this.coinDropMax = coinDropMax;
        this.items = new ArrayList<Item>();
    }

    /**
     * Gets the name of the NPC.
     * 
     * @return the name of the NPC
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the minimum coin drop value.
     * 
     * @return the minimum coin drop value
     */
    public int getCoinDropMin() {
        return this.coinDropMin;
    }

    /**
     * Gets the maximum coin drop value.
     * 
     * @return the maximum coin drop value
     */
    public int getCoinDropMax() {
        return this.coinDropMax;
    }
    
    /**
     * Gets the list of items the NPC possesses.
     * 
     * @return the list of items
     */
    public List<Item> getItems() {
        return this.items;
    }

    /**
     * Sets the list of items the NPC possesses.
     * 
     * @param items the new list of items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * Adds an item to the NPC's inventory.
     * 
     * @param item the item to add
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Removes an item from the NPC's inventory.
     * 
     * @param item the item to remove
     * @return true if the item was removed, false if the item was not found
     */
    public boolean removeItem(Item item) {
        return this.items.remove(item);
    }

    /**
     * Calculates the random coin drop value for this NPC.
     * 
     * @return a random value between coinDropMin and coinDropMax (inclusive)
     */
    public int getRandomCoinDrop() {
        return (int) (Math.random() * (this.coinDropMax - this.coinDropMin + 1)) + this.coinDropMin;
    }

    @Override
    public String toString() {
        return String.format("Npc[name=%s, coinDropMin=%d, coinDropMax=%d, health=%d]", this.name, this.coinDropMin, this.coinDropMax, this.getHealth());
    }
}
