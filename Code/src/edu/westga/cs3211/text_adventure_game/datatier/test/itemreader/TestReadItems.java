package edu.westga.cs3211.text_adventure_game.datatier.test.itemreader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.datatier.ItemReader;

class TestReadItems {
	private ItemReader itemReader;
	private File tempFile;
	
	@BeforeEach
	void setupFile() throws IOException {
		tempFile = File.createTempFile("testItem", ".txt");
		itemReader = new ItemReader(tempFile);
	}
	private void writeToFile(String content) throws IOException {
		try (FileWriter fileWriter = new FileWriter(tempFile)) {
			fileWriter.write(content);
		}
	}
	@Test
	void testWithInvalidFormat() throws IOException {
		writeToFile("1,Dagger,30,40");
		itemReader.readItems();
	}

}
