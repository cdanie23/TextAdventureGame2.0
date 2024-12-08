package edu.westga.cs3211.text_adventure_game.model;

/**
 * Represents an interaction with an NPC.
 * 
 * @author Jacob
 * @version Fall 2024
 */
public class NpcInteract extends Action {
    private Npc npc;

    /**
     * Creates an interaction with the specified NPC.
     * 
     * @param npc         the NPC to interact with
     * @param description the description of the interaction
     * @precondition npc != null
     * @postcondition this.npc == npc
     */
    public NpcInteract(Npc npc, String description) {
        super(description);
        if (npc == null) {
            throw new IllegalArgumentException("NPC cannot be null.");
        }
        this.npc = npc;
    }

    /**
     * Takes the interaction action with the NPC.
     * 
     * @param character the player or character interacting
     * @return a message indicating the result of the interaction
     */
    public String takeAction(Damageable character) {
        if (this.npc.getName().equalsIgnoreCase("Chest")) {
            return this.lootChest(character);
        } else if (this.npc.getName().equalsIgnoreCase("Merchant")) {
            return this.tradeWithMerchant(character);
        } else {
            return this.fightNpc(character);
        }
    }

    /**
     * Loots a chest and adds items to the character's inventory.
     * 
     * @param character the character looting the chest
     * @return a message indicating the result of the interaction
     */
    private String lootChest(Damageable character) {
        if (this.npc.getIsDead()) {
            return "The chest has already been looted.";
        }
        character.getInventory().addAll(this.npc.getInventory());
        this.npc.setHealth(0);
        return "You looted the chest!";
    }

    /**
     * Trades with a merchant.
     * 
     * @param character the character trading with the merchant
     * @return a message indicating the result of the interaction
     */
    private String tradeWithMerchant(Damageable character) {
        // Add trade logic (e.g., exchanging coins for items)
        return "You successfully traded with the merchant.";
    }

    /**
     * Engages in a fight with the NPC.
     * 
     * @param character the character fighting the NPC
     * @return a message indicating the result of the interaction
     */
    private String fightNpc(Damageable character) {
        if (this.npc.getIsDead()) {
            return "The NPC is already dead.";
        }
        this.npc.setHealth(0);
        character.getInventory().add(new Item("Loot", 1, 0, this.npc.getRandomCoinDrop()));
        return "You defeated the " + this.npc.getName() + "!";
    }

    @Override
    public String toString() {
        return super.getDescription();
    }
}
