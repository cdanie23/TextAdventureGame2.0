package edu.westga.cs3211.text_adventure_game.model;

import java.util.List;
import java.util.Random;

/**
 * This manages the Npc creation and adding them into the action list avaliable
 * 
 * @author Jacob
 * @version Fall 2024
 */
public class NpcManager {

    private List<Npc> npcPool;
    private Random random;

    /**
     * Creates an instance of NpcManager.
     *
     * @param npcPool a list of NPCs to randomly pick from
     * @precondition npcPool != null && !npcPool.isEmpty()
     * @postcondition this.npcPool == npcPool
     */
    public NpcManager(List<Npc> npcPool) {
        if (npcPool == null || npcPool.isEmpty()) {
            throw new IllegalArgumentException("NPC pool cannot be null or empty.");
        }
        this.npcPool = npcPool;
        this.random = new Random();
    }

    /**
     * Adds NPCs to a location based on a specific index and amount.
     *
     * @param index    the index of the NPC in the npcPool list
     * @param amount   the number of times to add the selected NPC to the location
     * @param location the location to add the NPCs to
     * @precondition index >= 0 && index < npcPool.size() && amount > 0 && location != null
     * @postcondition NPCs are added to the location
     */
    public void addNpcByIndex(int index, int amount, Location location) {
        if (index < 0 || index >= this.npcPool.size()) {
            throw new IllegalArgumentException("Invalid index.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }

        Npc npcToAdd = this.npcPool.get(index);
        for (int loopVar = 0; loopVar < amount; loopVar++) {
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
            Action npcInteraction = new NpcInteract(npcToAdd, "Interact with " + npcToAdd.getName());
            location.addNpc(npcToAdd);
            location.addAction(npcInteraction);
        }
    }
}
