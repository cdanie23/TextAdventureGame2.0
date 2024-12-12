package edu.westga.cs3211.text_adventure_game.datatier.test.npcreader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.NpcReader;

class testReadNpcs {
	private NpcReader npcReader;
	private File tempFile;

	
	@BeforeEach
	void setupFile() throws IOException {
		tempFile = File.createTempFile("NpcTest", ".txt");
		try (FileWriter fileWriter = new FileWriter(tempFile)) {
			fileWriter.write("testing, invalid& input1()Yes");
		}
		npcReader = new NpcReader(tempFile);
	}
	@Test
	void testInvalidFormat() {
		npcReader.readNpcs();
	}

}
