package edu.westga.cs3211.text_adventure_game.model.test.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Player;

class TestConstructor {

	@Test
	void testValidConstructor() {
		Player player = new Player();
		
		assertEquals(player.getHealth(), 100);
	}

}
