package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

public class ConsoleMapDisplay implements MapChangeListener {
    int amountOfChanges = 0;
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println("\n\n" + worldMap.getId());
        System.out.println(message);
        System.out.println(worldMap);
        System.out.println(++amountOfChanges);
    }
}
