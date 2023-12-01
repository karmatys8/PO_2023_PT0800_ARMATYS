package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap<Animal, Vector2d> {

    protected final Map<Vector2d, WorldElement> animals = new HashMap<>();
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);

    protected List<MapChangeListener> listeners = new ArrayList<>();
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
            updateListeners("animal placed on " + position);
        } else {
            throw new PositionAlreadyOccupiedException(position);
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (animal.equals(animals.get(animal.getPosition()))) {
            Animal animalToMove = (Animal) animals.remove(animal.getPosition());
            animalToMove.move(direction, this);
            animals.put(animalToMove.getPosition(), animalToMove);
            updateListeners("animal moved from " + animal.getPosition() + " to " + animalToMove.getPosition());
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
        Boundary boundary = this.getCurrentBounds();

        return mapVisualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }

    public void addObserver(MapChangeListener listener) {
        listeners.add(listener);
    }

    public void removeObserver(MapChangeListener listener) {
        listeners.remove(listener);
    }

    protected void updateListeners(String message) {
        for(MapChangeListener listener: listeners) {
            listener.mapChanged(this, message);
        }
    }
}
