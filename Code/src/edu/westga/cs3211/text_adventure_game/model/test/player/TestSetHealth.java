package edu.westga.cs3211.text_adventure_game.model.test.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Player;

class TestSetHealth {

	@Test
	void testSetHealth() {
		Player player = new Player();
		
		int newHealth = 55;
		player.setHealth(newHealth);
		
		assertEquals(player.getHealth(), newHealth);
	}
	@Test
	void testWhenHealthIsNegative() {
		Player player = new Player();
		
		int newHealth = -1; 
		
		assertThrows(IllegalArgumentException.class, () -> {
			player.setHealth(newHealth);
		});
	}
	@Test
	void testWhenHealthIsGreaterThan100() {
		Player player = new Player();
		
		int newHealth = 101; 
		
		assertThrows(IllegalArgumentException.class, () -> {
			player.setHealth(newHealth);
		});
	}
}
