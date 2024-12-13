package edu.westga.cs3211.text_adventure_game.test.model.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.cs3211.text_adventure_game.model.Item;

class TestConstructor {

	@Test
	void testValidConstructor() {
		Item item = new Item("Dagger", 10, 15, 20);
		
		assertEquals(item.getName(), "Dagger");
		assertEquals(item.getWeight(), 10);
		assertEquals(item.getEffect(), 15);
		assertEquals(item.getPrice(), 20);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {""})
	void testInvalidName(String strings) {
		
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Item(strings, 10, 10, 10);
		});
	}
	
	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Item(null, 10, 10, 10);
		});
	}
	
	@Test
	void testWeightGreaterThan100() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("Dagger", 101, 10, 10);
		});
	}
	
	@Test
	void testNegativeWeight() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("Dagger", -1, 10, 10);
		});
	}
	
	@Test
	void testNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("Dagger", 10, 10, -1);
		});
	}
	
	@Test
	void testPriceGreaterThan100() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Item("Dagger", 10, 10, 101);
		});
	}
}
