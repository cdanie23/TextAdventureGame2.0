package edu.westga.cs3211.text_adventure_game.test.model.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.ItemReader;
import edu.westga.cs3211.text_adventure_game.datatier.NpcReader;
import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.Npc;
import edu.westga.cs3211.text_adventure_game.model.NpcInteract;
import edu.westga.cs3211.text_adventure_game.model.NpcManager;

class TestInteractWithNpc {

	public class NpcManagerStub extends NpcManager {
		public NpcManagerStub(List<Npc> npcPool, List<Item> itemPool) {
			super(npcPool, itemPool);
			new Random();
		}

		@Override
		public void addRandomNpcs(int amount, Location location) {
			Npc chest1 = new Npc("Chest", 0, 50, 999);
			Npc enemy = new Npc("Dragon", 0, 50, 200);
			this.addRandomItemsToNpc(chest1, 2);

			location.addNpc(chest1);
			location.addNpc(enemy);
			Action npcInteraction1 = new NpcInteract(chest1, "Interact with " + chest1.getName());
			Action npcInteraction2 = new NpcInteract(enemy, "Interact with " + chest1.getName());
			location.addAction(npcInteraction1);
			location.addAction(npcInteraction2);
		}

		public void addRandomItemsToNpc(Npc npc, int amountOfItems) {
			Item dagger = new Item("Dagger", 10, -25, 10);
			Item posionPotion = new Item("Posion Potion", 10, -40, 10);

			npc.addItem(posionPotion);
			npc.addItem(dagger);
		}
	}

	private ItemReader itemReader;
	private NpcManagerStub npcManager;
	private List<Item> itemPool;
	private List<Npc> npcPool;
	private NpcReader npcReader;
	private GameManager gameManager;

	@BeforeEach
	void setupGame() {
		File itemFile = new File("Items.txt");
		this.itemReader = new ItemReader(itemFile);
		this.itemPool = this.itemReader.readItems();

		File npcFile = new File("Npc.txt");
		this.npcReader = new NpcReader(npcFile);
		this.npcPool = this.npcReader.readNpcs();

		this.npcManager = new NpcManagerStub(this.npcPool, this.itemPool);
		this.gameManager = new GameManager();
	}

	@Test
	void testWhenNpcIsChest() {
		this.gameManager.getCurrLocation().getNpcs().clear();
		this.gameManager.getActions().clear();
		this.npcManager.addRandomNpcs(2, this.gameManager.getCurrLocation());
		this.gameManager.setupActions();

		this.gameManager.interactWithNpc((NpcInteract) this.gameManager.getActions().get(0));

		assertEquals(this.gameManager.getActions().size(), 4);

	}

	@Test
	void testWhenNpcIsEnemy() {
		this.gameManager.getCurrLocation().getNpcs().clear();
		this.gameManager.getActions().clear();
		this.npcManager.addRandomNpcs(2, this.gameManager.getCurrLocation());
		this.gameManager.setupActions();

		this.gameManager.getPlayer().setDamage(20);
		NpcInteract fightDragon = (NpcInteract) this.gameManager.getActions().get(1);
		this.gameManager.interactWithNpc(fightDragon);

		int playerHealth = this.gameManager.getPlayer().getHealth();
		int npcHealth = fightDragon.getNpc().getHealth();
		assertEquals(playerHealth, 80);
		assertEquals(npcHealth, 180);

	}
}
