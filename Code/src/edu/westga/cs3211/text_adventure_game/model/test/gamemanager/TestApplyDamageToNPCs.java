package edu.westga.cs3211.text_adventure_game.model.test.gamemanager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Npc;

class TestApplyDamageToNPCs {

	@ParameterizedTest
	@ValueSource(ints = {0, -10, -20 ,-50, -99})
	void testNpcDamageTaken(int testValue) {
		GameManager gm = new GameManager();
		gm.getCurrLocation().getNpcs().removeAll(gm.getCurrLocation().getNpcs());
		gm.getCurrLocation().addNpc(new Npc("testNPC", 0,0,100));
		
		gm.applyDamageToNPCs(testValue);
		for (Npc curr : gm.getCurrLocation().getNpcs()) {
			assertEquals(curr.getHealth(), 100 + testValue);
		}
	}
	
	@Test
	void testNpcHealth0() {
		GameManager gm = new GameManager();
		gm.getCurrLocation().getNpcs().removeAll(gm.getCurrLocation().getNpcs());
		Npc removedNpc = new Npc("testNPC", 0,0,100);
		gm.getCurrLocation().addNpc(removedNpc);
		assertEquals(true, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure npc is in list");
		gm.applyDamageToNPCs(-100);
		assertEquals(false, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure the npc with lower than 0 health are removed");
	}
	
	@Test
	void testNpcHealthNegative() {
		GameManager gm = new GameManager();
		gm.getCurrLocation().getNpcs().removeAll(gm.getCurrLocation().getNpcs());
		Npc removedNpc = new Npc("testNPC", 0,0,100);
		gm.getCurrLocation().addNpc(removedNpc);
		assertEquals(true, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure npc is in list");
		gm.applyDamageToNPCs(-101);
		assertEquals(false, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure the npc with lower than 0 health are removed");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, -10, -20 ,-50, -99})
	void testNpcDamageTakenWithPlayer(int testValue) {
		GameManager gm = new GameManager();
		gm.getPlayer().setDamage(testValue);
		gm.getCurrLocation().getNpcs().removeAll(gm.getCurrLocation().getNpcs());
		gm.getCurrLocation().addNpc(new Npc("testNPC", 0,0,100));
		
		gm.applyDamageToNPCs();
		for (Npc curr : gm.getCurrLocation().getNpcs()) {
			assertEquals(curr.getHealth(), 100 + testValue);
		}
	}
	
	@Test
	void testNpcHealth0WithPlayer() {
		GameManager gm = new GameManager();
		gm.getPlayer().setDamage(-100);
		gm.getCurrLocation().getNpcs().removeAll(gm.getCurrLocation().getNpcs());
		Npc removedNpc = new Npc("testNPC", 0,0,100);
		gm.getCurrLocation().addNpc(removedNpc);
		assertEquals(true, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure npc is in list");
		gm.applyDamageToNPCs();
		assertEquals(false, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure the npc with lower than 0 health are removed");
	}
	
	@Test
	void testNpcHealthNegativeWithPlayer() {
		GameManager gm = new GameManager();
		gm.getPlayer().setDamage(-101);
		gm.getCurrLocation().getNpcs().removeAll(gm.getCurrLocation().getNpcs());
		Npc removedNpc = new Npc("testNPC", 0,0,100);
		gm.getCurrLocation().addNpc(removedNpc);
		assertEquals(true, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure npc is in list");
		gm.applyDamageToNPCs();
		assertEquals(false, gm.getCurrLocation().getNpcs().contains(removedNpc), "Make sure the npc with lower than 0 health are removed");
	}

}
