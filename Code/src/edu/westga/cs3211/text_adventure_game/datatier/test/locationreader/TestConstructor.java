package edu.westga.cs3211.text_adventure_game.datatier.test.locationreader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.LocationReader;

class TestConstructor {

	@Test
	void testInvalidConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new LocationReader(null));
	}
	
}
