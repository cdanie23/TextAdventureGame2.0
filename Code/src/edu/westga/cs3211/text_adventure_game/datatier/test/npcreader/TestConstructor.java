package edu.westga.cs3211.text_adventure_game.datatier.test.npcreader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.NpcReader;

class TestConstructor {

	@Test
	void testNullFile() {
		assertThrows(IllegalArgumentException.class, () -> {
			new NpcReader(null);
		});
	}

}
