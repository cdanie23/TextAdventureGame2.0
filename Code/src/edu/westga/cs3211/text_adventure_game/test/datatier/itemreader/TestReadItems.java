package edu.westga.cs3211.text_adventure_game.test.datatier.itemreader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.ItemReader;
import edu.westga.cs3211.text_adventure_game.model.Item;

class TestReadItems {
	 @Test
	    void testConstructorWithValidFile() {
	        File tempFile = new File("valid.txt");
	        ItemReader reader = new ItemReader(tempFile);
	        assertNotNull(reader);
	    }

	    @Test
	    void testConstructorWithNullFile() {
	        assertThrows(IllegalArgumentException.class, () -> new ItemReader(null));
	    }

	    @Test
	    void testReadItemsWithValidData() throws Exception {
	        File tempFile = File.createTempFile("test", ".txt");
	        tempFile.deleteOnExit();
	        
	        try (PrintWriter writer = new PrintWriter(tempFile)) {
	            writer.println("Sword,10,15,20");
	            writer.println("Shield,20,10,10");
	        }

	        ItemReader reader = new ItemReader(tempFile);
	        List<Item> items = reader.readItems();

	        assertEquals(2, items.size());
	        
	        assertEquals("Sword", items.get(0).getName());
	        assertEquals(10, items.get(0).getWeight());
	        assertEquals(15, items.get(0).getEffect());
	        assertEquals(20, items.get(0).getPrice());
	        
	        assertEquals("Shield", items.get(1).getName());
	        assertEquals(20, items.get(1).getWeight());
	        assertEquals(10, items.get(1).getEffect());
	        assertEquals(10, items.get(1).getPrice());
	    }

	    @Test
	    void testReadItemsWithEmptyFile() throws Exception {
	        File tempFile = File.createTempFile("test", ".txt");
	        tempFile.deleteOnExit();

	        ItemReader reader = new ItemReader(tempFile);
	        List<Item> items = reader.readItems();

	        assertTrue(items.isEmpty());
	    }

	    @Test
	    void testReadItemsWithMalformedData() throws Exception {
	        File tempFile = File.createTempFile("test", ".txt");
	        tempFile.deleteOnExit();

	        try (PrintWriter writer = new PrintWriter(tempFile)) {
	            writer.println("Invalid,Data");
	        }

	        ItemReader reader = new ItemReader(tempFile);

	        List<Item> items = reader.readItems();

	        assertTrue(items.isEmpty());
	    }

}
