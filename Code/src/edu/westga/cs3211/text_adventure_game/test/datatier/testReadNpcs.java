package edu.westga.cs3211.text_adventure_game.test.datatier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.NpcReader;
import edu.westga.cs3211.text_adventure_game.model.Npc;

class testReadNpcs {
	@Test
    void testConstructorWithValidFile() {
        File tempFile = new File("npcs.txt");
        NpcReader reader = new NpcReader(tempFile);
        assertNotNull(reader, "NpcReader should be instantiated with a valid file.");
    }

    @Test
    void testConstructorWithNullFile() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new NpcReader(null));
        assertEquals("file cannot be null", exception.getMessage(), "Constructor should throw exception for null file.");
    }

    @Test
    void testReadNpcsWithValidData() throws Exception {
        File tempFile = File.createTempFile("npcs", ".txt");
        tempFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("Goblin,5,10,20");
            writer.println("Orc,10,15,50");
        }

        NpcReader reader = new NpcReader(tempFile);
        List<Npc> npcs = reader.readNpcs();

        // Validate the number of NPCs
        assertEquals(2, npcs.size(), "The number of NPCs should match the input data.");

        // Validate the first NPC
        Npc goblin = npcs.get(0);
        assertEquals("Goblin", goblin.getName());
        assertEquals(5, goblin.getCoinDropMin());
        assertEquals(10, goblin.getCoinDropMax());
        assertEquals(20, goblin.getHealth());

        // Validate the second NPC
        Npc orc = npcs.get(1);
        assertEquals("Orc", orc.getName());
        assertEquals(10, orc.getCoinDropMin());
        assertEquals(15, orc.getCoinDropMax());
        assertEquals(50, orc.getHealth());
    }

    @Test
    void testReadNpcsWithEmptyFile() throws Exception {
        File tempFile = File.createTempFile("npcs", ".txt");
        tempFile.deleteOnExit();

        NpcReader reader = new NpcReader(tempFile);
        List<Npc> npcs = reader.readNpcs();

        assertTrue(npcs.isEmpty(), "The list of NPCs should be empty when the file is empty.");
    }

    @Test
    void testReadNpcsWithInvalidData() throws Exception {
        File tempFile = File.createTempFile("npcs", ".txt");
        tempFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("InvalidData");
        }

        NpcReader reader = new NpcReader(tempFile);

        // The method should handle exceptions and return an empty list
        List<Npc> npcs = reader.readNpcs();

        assertTrue(npcs.isEmpty(), "The list of NPCs should be empty when the file contains invalid data.");
    }

    @Test
    void testReadNpcsHandlesExceptionsGracefully() throws Exception {
        File nonExistentFile = new File("nonexistent.txt");

        NpcReader reader = new NpcReader(nonExistentFile);

        // This should handle the FileNotFoundException gracefully
        List<Npc> npcs = reader.readNpcs();

        assertTrue(npcs.isEmpty(), "The method should handle missing files gracefully and return an empty list.");
    }

}
