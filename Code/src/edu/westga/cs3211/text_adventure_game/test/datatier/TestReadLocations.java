package edu.westga.cs3211.text_adventure_game.test.datatier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.LocationReader;
import edu.westga.cs3211.text_adventure_game.model.Direction;
import edu.westga.cs3211.text_adventure_game.model.Location;
import edu.westga.cs3211.text_adventure_game.model.LocationType;
import edu.westga.cs3211.text_adventure_game.model.TrapLocation;

class TestReadLocations {
	@Test
    void testConstructorWithValidFile() {
        File tempFile = new File("test.txt");
        LocationReader reader = new LocationReader(tempFile);
        assertNotNull(reader);
    }

    @Test
    void testConstructorWithNullFile() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LocationReader(null));
        assertEquals("file cannot be null", exception.getMessage());
    }

    @Test
    void testReadLocationsWithValidData() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("Location1,Description1,Move Forward&Move Right,Location2 (Forward)&Location3 (Right),Safe");
            writer.println("Location2,Description2,Move Backward,Location1 (Backward),Goal");
            writer.println("Location3,Description3,Move Left,Location1 (Left),Trap,50");
        }

        LocationReader reader = new LocationReader(tempFile);
        List<Location> locations = reader.readLocations();

        // Validate number of locations
        assertEquals(3, locations.size());

        // Validate the first location
        Location location1 = locations.get(0);
        assertEquals("Location1", location1.getName());
        assertEquals("Description1", location1.getDescription());
        assertEquals(2, location1.getActions().size());
        assertTrue(location1.getAdjacentLocations().containsKey(Direction.Forward));
        assertTrue(location1.getAdjacentLocations().containsKey(Direction.Right));

        // Validate the second location
        Location location2 = locations.get(1);
        assertEquals("Location2", location2.getName());
        assertEquals("Description2", location2.getDescription());
        assertEquals(LocationType.Goal, location2.getLocationType());

        // Validate the third location (trap location)
        TrapLocation location3 = (TrapLocation) locations.get(2);
        assertEquals("Location3", location3.getName());
        assertEquals("Description3", location3.getDescription());
        assertEquals(LocationType.Trap, location3.getLocationType());
        assertEquals(50, location3.getDamageInflicted());
    }

    @Test
    void testReadLocationsWithEmptyFile() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        LocationReader reader = new LocationReader(tempFile);
        List<Location> locations = reader.readLocations();

        assertTrue(locations.isEmpty());
    }

    @Test
    void testReadLocationsWithInvalidData() throws Exception {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.println("InvalidData");
        }

        LocationReader reader = new LocationReader(tempFile);

        // The method should handle exceptions and return an empty list
        List<Location> locations = reader.readLocations();

        assertTrue(locations.isEmpty());
    }

    @Test
    void testFileNotFound() {
        File nonExistentFile = new File("nonexistent.txt");

        LocationReader reader = new LocationReader(nonExistentFile);

        // This should handle the FileNotFoundException gracefully
        List<Location> locations = reader.readLocations();

        assertTrue(locations.isEmpty());
    }
    
    @Test
    void testGetFile() {
        File tempFile = new File("test.txt");
        LocationReader reader = new LocationReader(tempFile);

        // Verify that getFile returns the same file instance
        assertEquals(tempFile, reader.getFile(), "The getFile method should return the correct file.");
    }

}
