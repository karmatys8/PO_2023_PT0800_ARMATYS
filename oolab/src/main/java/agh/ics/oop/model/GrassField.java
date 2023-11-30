package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class GrassField extends AbstractWorldMap{

    private final Map<Vector2d, WorldElement> grassMap = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);


    public GrassField(int grassCount) {
        int maxVal = (int) Math.sqrt(grassCount * 10);

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxVal, maxVal, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    public String toString() {
        Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (Vector2d position: animals.keySet()) {
            lowerLeft = lowerLeft.upperRight(position);
            upperRight = upperRight.lowerLeft(position);
        }

        for (Vector2d position: grassMap.keySet()) {
            lowerLeft = lowerLeft.lowerLeft(position);
            upperRight = upperRight.upperRight(position);
        }

        return mapVisualizer.draw(lowerLeft, upperRight);
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
        WorldElement animal = super.objectAt(position);
        if (animal != null) {
            return animal;
        } else {
            return grassMap.get(position);
        }
    }

    @Override
    public List<WorldElement> getElements() {
        List<WorldElement> result = new ArrayList<>();

        result.addAll(animals.values());
        result.addAll(grassMap.values());

        return result;
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d lowerLeft = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (Vector2d position: animals.keySet()) {
            lowerLeft = lowerLeft.upperRight(position);
            upperRight = upperRight.lowerLeft(position);
        }

        for (Vector2d position: grassMap.keySet()) {
            lowerLeft = lowerLeft.upperRight(position);
            upperRight = upperRight.lowerLeft(position);
        }

        return new Boundary(lowerLeft, upperRight);
    }
}
