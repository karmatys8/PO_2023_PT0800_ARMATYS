package agh.ics.oop.model;

import agh.ics.oop.model.util.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Assertions;

public class Utility {
    static void safePlace(AbstractWorldMap worldMap, Animal animal) {
        try {
            worldMap.place(animal);
        } catch (PositionAlreadyOccupiedException ignored) {}
    }

    static void testHelper(AbstractWorldMap worldMap, Vector2d vector, boolean result) {
        Animal animal = new Animal(vector);
        safePlace(worldMap, animal);
        Assertions.assertEquals(result, animal == worldMap.objectAt(vector)); // checking references on purpose
    }
}
