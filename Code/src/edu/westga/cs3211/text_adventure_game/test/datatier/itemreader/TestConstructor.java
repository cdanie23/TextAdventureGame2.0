package edu.westga.cs3211.text_adventure_game.test.datatier.itemreader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.ItemReader;

class TestConstructor {

	@Test
	void testInvalidFile() {
		assertThrows(IllegalArgumentException.class, () -> {
			new ItemReader(null);
		});
	}

}
