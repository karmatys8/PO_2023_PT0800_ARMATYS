package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap<Animal, Vector2d> {
    protected UUID id;
    protected final Map<Vector2d, WorldElement> animals = new HashMap<>();
    protected MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected List<MapChangeListener> listeners = new ArrayList<>();

    public AbstractWorldMap() {
        id = UUID.randomUUID();
    }

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
        Vector2d oldPosition = animal.getPosition();
        if (animal.equals(animals.get(oldPosition))) {
            Animal animalToMove = (Animal) animals.remove(oldPosition);
            animalToMove.move(direction, this);
            animals.put(animalToMove.getPosition(), animalToMove);
            updateListeners("animal moved from " + oldPosition + " to " + animalToMove.getPosition());
        }
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animals.get(position));
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

    public UUID getId() {
        return id; // maybe I should make it unmodifiable
    }

    public Collection<WorldElement> getOrderedAnimals() {
        return animals.values().stream()
                .sorted(Comparator.comparing((WorldElement animal) -> animal.getPosition().getX())
                        .thenComparing((WorldElement animal) -> animal.getPosition().getY()))
                .collect(Collectors.toList());
    }
}
