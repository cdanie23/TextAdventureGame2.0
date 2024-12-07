package edu.westga.cs3211.text_adventure_game.model.test.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;

class TestToString {

	@Test
	void testToString() {
		Item item = new Item("Dagger", 10, 10, 10);
		
		assertEquals(item.toString(), "Dagger");
	}

}
