package agh.ics.oop.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap<Animal, Vector2d> {

    protected final Map<Vector2d, WorldElement> animals = new HashMap<>();

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (! isOccupied(position));
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
            Animal animalToMove = (Animal) animals.remove(animal.getPosition());
            animalToMove.move(direction, (MoveValidator) this);
            animals.put(animalToMove.getPosition(), animalToMove);
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }


    public Map<Vector2d, WorldElement> getElements() {
        return Collections.unmodifiableMap(animals);
    }
}
