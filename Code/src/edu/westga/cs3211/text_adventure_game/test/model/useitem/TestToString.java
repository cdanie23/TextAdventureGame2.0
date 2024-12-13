package edu.westga.cs3211.text_adventure_game.test.model.useitem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.UseItem;

class TestToString {

	@Test
	void testToString() {
		Item item = new Item("Dagger", 10, 10, 10);
		UseItem useItem = new UseItem(item);
		
		assertEquals(useItem.toString(), "Use Dagger");
	}

}
