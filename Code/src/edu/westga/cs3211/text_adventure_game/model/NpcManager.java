package edu.westga.cs3211.text_adventure_game.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This manages the Npc creation and adding them into the action list avaliable
 * 
 * @author Jacob and Kate
 * @version Fall 2024
 */
public class NpcManager {

    private List<Npc> npcPool;
    private Random random;
    private List<Item> itemPool;

    /**
     * Creates an instance of NpcManager.
     *
     * @param npcPool a list of NPCs to randomly pick from
     * @param itemPool a list of Items to randomly pick from
     * @precondition npcPool != null && !npcPool.isEmpty()
     * @postcondition this.npcPool == npcPool, this.itemPool == itemPool
     */
    public NpcManager(List<Npc> npcPool, List<Item> itemPool) {
        if (npcPool == null || npcPool.isEmpty()) {
            throw new IllegalArgumentException("NPC pool cannot be null or empty.");
        }
        this.npcPool = npcPool;
        this.itemPool = itemPool;
        this.random = new Random();
    }
    
    /**
	* Adds NPCs to the current location at a chance.
	* If NPCs are added, the number of NPCs is determined by a random weight
	* 
	* @param currLocation this is the current location to add everything to 
	*/
    public void addNpcsToLocation(Location currLocation) {
	    Random random = new Random();

	    if (random.nextInt(100) < 50) {
	        int roll = random.nextInt(100);
	        int numNpcsToAdd;

	        if (roll < 66) {
	            numNpcsToAdd = 1;
	        } else if (roll < 95) {
	            numNpcsToAdd = 2;
	        } else {
	            numNpcsToAdd = 3;
	        }

	        for (int index = 0; index < numNpcsToAdd; index++) {
	            int randomIndex = random.nextInt(this.npcPool.size());
	            Npc npcToAdd = this.npcPool.get(randomIndex);
	            int numOfItemsToAdd = this.random.nextInt(0, 3);
	            this.addRandomItemsToNpc(npcToAdd, numOfItemsToAdd);
	            Action npcInteraction = new NpcInteract(npcToAdd, "Interact with " + npcToAdd.getName());
	            currLocation.addNpc(npcToAdd);
	            currLocation.addAction(npcInteraction);
	        }
	    }
	}

    /**
     * Adds NPCs to a location based on a specific index and amount.
     *
     * @param index    the index of the NPC in the npcPool list
     * @param amount   the number of times to add the selected NPC to the location
     * @param location the location to add the NPCs to
     * @param amountOfItems the amount of items to add to inventory
     * @precondition index >= 0 && index < npcPool.size() && amount > 0 && location != null
     * @postcondition NPCs are added to the location
     */
    public void addNpcByIndex(int index, int amount, Location location, int amountOfItems) {
        if (index < 0 || index >= this.npcPool.size()) {
            throw new IllegalArgumentException("Invalid index.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        Npc npcToAdd = this.npcPool.get(index);
        for (int loopVar = 0; loopVar < amount; loopVar++) {
        	this.addRandomItemsToNpc(npcToAdd, amountOfItems);
            Action npcInteraction = new NpcInteract(npcToAdd, "Interact with " + npcToAdd.getName());
            location.addNpc(npcToAdd);
            location.getActions().add(npcInteraction);
        }
    }

    /**
     * Adds NPCs to a location based on a random NPC from the npcPool list.
     *
     * @param amount   the number of NPCs to add to the location
     * @param location the location to add the NPCs to
     * @precondition amount > 0 && location != null
     * @postcondition Random NPCs are added to the location
     */
    public void addRandomNpcs(int amount, Location location) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        for (int loopVar = 0; loopVar < amount; loopVar++) {
            int randomIndex = this.random.nextInt(this.npcPool.size());
            Npc npcToAdd = this.npcPool.get(randomIndex);
            int numOfItemsToAdd = this.random.nextInt(0, 3);
            this.addRandomItemsToNpc(npcToAdd, numOfItemsToAdd);
            Action npcInteraction = new NpcInteract(npcToAdd, "Interact with " + npcToAdd.getName());
            location.addNpc(npcToAdd);
            location.addAction(npcInteraction);
        }
    }
    
    /**
     * Adds random items to npc
     * 
     * @param npc the npc that items are added to
     * @param amountOfItems the amount of items the npc gets added
     */
    public void addRandomItemsToNpc(Npc npc, int amountOfItems) {
    	int numOfItemsAdded = 0;
    	Collections.shuffle(this.itemPool);
    	for (Item item : this.itemPool) {
    		if (numOfItemsAdded >= amountOfItems) {
    			break;
    		}
    		int chanceToRecieve = 100 - item.getPrice();
    		int randomNumber = this.random.nextInt(1, 100);
    		if (randomNumber <= chanceToRecieve) {
    			npc.addItem(item);
    			numOfItemsAdded++;
    		}
    	}
    }
}
