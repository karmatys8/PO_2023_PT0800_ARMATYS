package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.*;

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
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);

        } else {
            throw new PositionAlreadyOccupiedException(position);
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animal.equals(animals.get(animal.getPosition()))) {
            Animal animalToMove = (Animal) animals.remove(animal.getPosition());
            animalToMove.move(direction, (MoveValidator) this);
            animals.put(animalToMove.getPosition(), animalToMove);
        }
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }


    public List<WorldElement> getElements() {
        return Collections.unmodifiableList(new ArrayList<>(animals.values()));
    }

    @Override
    public String toString() {
        MapVisualizer currentMap = new MapVisualizer(this);
        Boundary boundary = this.getCurrentBounds();

        return currentMap.draw(boundary.lowerLeft(), boundary.upperRight());
    }
}
