package agh.ics.oop.model;


import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d> {
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    private static final Vector2d lowerLeftBoundary = new Vector2d(0, 0);
    private final Vector2d upperRightBoundary;;

    public RectangularMap(int width, int height) {
        upperRightBoundary  = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;

        } else return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animals.containsValue(animal)) {
            Animal animalToMove = animals.remove(animal.getPosition());
            animalToMove.move(direction, (MoveValidator) this);
            animals.put(animalToMove.getPosition(), animalToMove);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return ! isOccupied(position) &&
                position.precedes(upperRightBoundary) && position.follows(lowerLeftBoundary);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer currentMap = new MapVisualizer((WorldMap) this);
        return currentMap.draw(lowerLeftBoundary, upperRightBoundary);
    }
}
