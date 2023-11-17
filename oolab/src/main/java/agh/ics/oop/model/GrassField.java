package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{

    private final Map<Vector2d, WorldElement> grassMap = new HashMap<>();

    private int grassCount = 0;

    public GrassField(int grassCount) {
        Random rand = new Random();
        int maxVal = (int) Math.sqrt(grassCount * 10);
        for (int i = 0; i < grassCount; i++) {
            Vector2d newVector = new Vector2d(rand.nextInt(maxVal), rand.nextInt(maxVal));

            if (grassMap.get(newVector) == null) {
                grassMap.put(newVector, new Grass(newVector));
                this.grassCount++;
            } else {i--;};
        }
    }

    public String toString() {
        MapVisualizer currentMap = new MapVisualizer((WorldMap) this);

        Integer xMax= -2147483648;
        int yMax = xMax;
        int xMin = 2147483647;
        int yMin = xMin;

        for (Vector2d position: animals.keySet()) {
            // I should DRY code here but have problem with keeping track of maxes and minimums
            // I found it not worth it to spend more time on it without teachers help
            int x = position.getX();
            int y = position.getY();

            if (x > xMax) xMax = x;
            if (x < xMin) xMin = x;

            if (y > yMax) yMax = y;
            if (y < yMin) yMin = y;
        }

        for (Vector2d position: grassMap.keySet()) {
            int x = position.getX();
            int y = position.getY();

            if (x > xMax) xMax = x;
            if (x < xMin) xMin = x;

            if (y > yMax) yMax = y;
            if (y < yMin) yMin = y;
        }

        return currentMap.draw(new Vector2d(xMin, yMin), new Vector2d(xMax, yMax));
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
    public Map<Vector2d, WorldElement> getElements() {
        Map<Vector2d, WorldElement> result = Stream.concat(animals.entrySet().stream(), grassMap.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (value1, value2) -> new Animal(value1.getPosition())));
        // when there are 2 objects at the same tile it means that 1st is Animal and 2nd one is grass and Animal has priority over Grass

        // idk how I can make it easier
        // at least I somewhat understand what is going on here

        return Collections.unmodifiableMap(result);
    }
}
