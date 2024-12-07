package edu.westga.cs3211.text_adventure_game.model.test.dropitem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.DropItem;
import edu.westga.cs3211.text_adventure_game.model.Item;

class TestConstructor {

	@Test
	void testValidConstructor() {
		Item item = new Item("Dagger", 10, 15, 10);
		DropItem action = new DropItem(item);
		
		assertEquals(action.getDescription(), "Drop Dagger");
		assertEquals(action.getItem(), item);
	}

}
