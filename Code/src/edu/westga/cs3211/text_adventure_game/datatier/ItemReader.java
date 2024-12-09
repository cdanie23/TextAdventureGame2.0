package edu.westga.cs3211.text_adventure_game.datatier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.westga.cs3211.text_adventure_game.model.Item;

/**
 * The item reader class
 * @author Colby
 * @version Fall 2024
 */
public class ItemReader {
	private File file;
	
	/**
	 * Creates an instance of the item reader
	 * @param file the file to read
	 * @precondition: file != null
	 * @postcondition: this.file == file
	 * 
	 */
	public ItemReader(File file) {
		if (file == null) {
			throw new IllegalArgumentException("file cannot be null");
		}
		this.file = file;
	}
	
	/**
	 * Reads all the items from the file
	 * @return the list of items
	 */
	public List<Item> readItems() {
		List<Item> items = new ArrayList<Item>();
		
		try (Scanner scnr = new Scanner(this.file)) {
			
			while (scnr.hasNext()) {
			String line = scnr.nextLine();
			String[] tokens = line.split(",");
			
			String name = tokens[0];
			int weight = Integer.parseInt(tokens[1]);
			int effect = Integer.parseInt(tokens[2]);
			int price = Integer.parseInt(tokens[3]);
			
			Item item = new Item(name, weight, effect, price);
			items.add(item);
			}
		} catch (Exception exception) {
			
			System.err.print(exception.getMessage());
		}
		return items;
	}
}
