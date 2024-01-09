package agh.ics.oop.model;

@FunctionalInterface
public interface MapChangeListener {
    void mapChanged(WorldMap<Animal, Vector2d> worldMap, String message);
}
