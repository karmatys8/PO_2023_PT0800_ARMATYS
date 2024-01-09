package agh.ics.oop.model;

import agh.ics.oop.model.util.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

public class Utility {
    static void safePlace(AbstractWorldMap worldMap, Animal animal) {
        try {
            worldMap.place(animal);
        } catch (PositionAlreadyOccupiedException ignored) {}
    }

    static void testHelper(AbstractWorldMap worldMap, Vector2d vector, boolean result) {
        Animal animal = new Animal(vector);
        safePlace(worldMap, animal);

        Optional<WorldElement> optionalElement = worldMap.objectAt(vector);
        if (result) {
            Assertions.assertTrue(optionalElement.isPresent());
            Assertions.assertSame(animal, optionalElement.get());
        } else optionalElement.ifPresent(worldElement -> Assertions.assertNotSame(animal, worldElement));
    }
}
