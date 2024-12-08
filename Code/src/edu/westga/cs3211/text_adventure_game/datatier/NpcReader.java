package edu.westga.cs3211.text_adventure_game.datatier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.westga.cs3211.text_adventure_game.model.Npc;

/**
 * The NPC reader class
 * Reads NPC data from a file and creates NPC objects.
 * 
 * @author Jacob
 * @version Fall 2024
 */
public class NpcReader {
    private File file;

    /**
     * Creates an instance of the NPC reader
     * 
     * @param file the file to read
     * @precondition: file != null
     * @postcondition: this.file == file
     */
    public NpcReader(File file) {
        if (file == null) {
            throw new IllegalArgumentException("file cannot be null");
        }
        this.file = file;
    }

    /**
     * Reads all the NPCs from the file
     * 
     * @return the list of NPCs
     */
    public List<Npc> readNpcs() {
        List<Npc> npcs = new ArrayList<>();

        try (Scanner scnr = new Scanner(this.file)) {
            while (scnr.hasNext()) {
                String line = scnr.nextLine();
                String[] tokens = line.split(",");

                String name = tokens[0];
                int coinDropMin = Integer.parseInt(tokens[1]);
                int coinDropMax = Integer.parseInt(tokens[2]);
                int health = Integer.parseInt(tokens[3]);

                Npc npc = new Npc(name, coinDropMin, coinDropMax, health);
                npcs.add(npc);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return npcs;
    }
}
