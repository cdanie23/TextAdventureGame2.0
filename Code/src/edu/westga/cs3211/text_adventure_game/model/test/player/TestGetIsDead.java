package edu.westga.cs3211.text_adventure_game.model.test.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Player;

class TestGetIsDead {

	@Test
	void testWhenIsDeadIsNull() {
		Player player = new Player();
		
		assertThrows(IllegalArgumentException.class, () -> {
			player.setIsDead(null);
		});
	}

}
