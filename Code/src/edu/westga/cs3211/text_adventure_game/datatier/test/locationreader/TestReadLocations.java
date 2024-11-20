package edu.westga.cs3211.text_adventure_game.datatier.test.locationreader;

import java.io.File;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.LocationReader;

class TestReadLocations {

	@Test
	void testWhenFileNotFound() {
		File file = new File("unknownFile.txt");
		LocationReader locationReader = new LocationReader(file);
		locationReader.readLocations();
	}

}
