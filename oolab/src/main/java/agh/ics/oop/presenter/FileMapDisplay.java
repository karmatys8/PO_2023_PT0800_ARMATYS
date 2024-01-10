package agh.ics.oop.presenter;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class FileMapDisplay implements MapChangeListener {
    private final UUID mapID;

    public FileMapDisplay(UUID mapID) {
        this.mapID = mapID;
    }

    @Override
    public void mapChanged(WorldMap<Animal, Vector2d> worldMap, String message) {
        String filename = mapID + ".log";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println("Move information:");
            writer.println(message);

            writer.println("\nCurrent map state:\n");
            writer.println(worldMap.toString());

            writer.println("-----------------");
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }
}
