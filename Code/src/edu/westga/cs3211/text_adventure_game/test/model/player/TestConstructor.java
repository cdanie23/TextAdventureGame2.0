package edu.westga.cs3211.text_adventure_game.test.model.player;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.Player;

class TestConstructor {

	@Test
	void testValidConstructor() {
		Player player = new Player(new ArrayList<Item>());
		
		assertEquals(player.getHealth(), 100);
	}

}
