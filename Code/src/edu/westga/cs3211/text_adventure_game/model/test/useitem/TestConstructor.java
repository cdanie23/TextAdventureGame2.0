package edu.westga.cs3211.text_adventure_game.model.test.useitem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Item;
import edu.westga.cs3211.text_adventure_game.model.UseItem;

class TestConstructor {

	@Test
	void test() {
		Item item = new Item("Dagger", 10, 10, 10);
		
		UseItem useItem = new UseItem(item);
		
		assertEquals(useItem.getDescription(), "Use Dagger");
		assertEquals(useItem.getItem(), item);
	}

}
