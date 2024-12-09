package edu.westga.cs3211.text_adventure_game.datatier.test.locationreader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.LocationReader;

class TestReadLocations {
	private LocationReader locationReader;
	
	@BeforeEach
	void setupFile() throws IOException {
		File tempFile = File.createTempFile("locations", ".txt");
		try (FileWriter fileWriter = new FileWriter(tempFile)) {
			fileWriter.write("Move Left, Right, Creaky Castle, (Left),");
		}
		locationReader = new LocationReader(tempFile);
	}
	
	@Test
	void testWhenFileNotFound() {
		File file = new File("unknownFile.txt");
		LocationReader locationReader = new LocationReader(file);
		locationReader.readLocations();
	}
	
	@Test
	void testInvalidFormat() {
		locationReader.readLocations();
	}

}
