package edu.westga.cs3211.text_adventure_game.model.test.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Player;

class TestSetHealth {

	@Test
	void testSetHealth() {
		Player player = new Player(new ArrayList<Item>());
		
		int newHealth = 55;
		player.setHealth(newHealth);
		
		assertEquals(player.getHealth(), newHealth);
	}
	

}
