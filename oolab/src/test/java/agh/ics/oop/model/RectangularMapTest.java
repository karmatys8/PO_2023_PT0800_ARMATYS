package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class RectangularMapTest {

    @Test
    public void testIsOccupied() {
        RectangularMap recMap = new RectangularMap(4, 4);

        Vector2d[] toPlace = {new Vector2d(0, 0), new Vector2d(0, 1), new Vector2d(1, 2),
        new Vector2d(2, 0), new Vector2d(2, 3), new Vector2d(3, 0), new Vector2d(3, 2)};

        for (Vector2d vector:toPlace) {
            recMap.place(new Animal(vector));
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Vector2d vector = new Vector2d(i, j);
                Assertions.assertEquals(Arrays.asList(toPlace).contains(vector), recMap.isOccupied(vector));
            }
        }
    }

    @Test
    public void testCanMoveTo() {
        RectangularMap recMap = new RectangularMap(5, 2);

        for (int i = -1; i < 6; i++) {
            for (int j = -1; j < 3; j++) {
                Vector2d vector = new Vector2d(i, j);
                Assertions.assertEquals(i >= 0 && i < 5 && j >= 0 && j < 2, recMap.canMoveTo(vector));
            }
        }


        RectangularMap recMap2 = new RectangularMap(-3, 2);

        for (int i = -3; i < 3; i++) {
            Assertions.assertFalse(recMap2.canMoveTo(new Vector2d(i, 0)));
        }


        Vector2d vectorA = new Vector2d(4, 0); // idk if I should tests that depend on each other
        Vector2d vectorB = new Vector2d(3, 3);

        recMap.place(new Animal(vectorA));
        recMap.place(new Animal(vectorB));

        Assertions.assertFalse(recMap.canMoveTo(vectorA));
        Assertions.assertFalse(recMap.canMoveTo(vectorB));
    }

    void testHelper(RectangularMap recMap, Vector2d vector, boolean result) {
        // ideally I would pass assertTrue/assertFalse instead of boolean but idk how to do it yet
        Assertions.assertEquals(result, recMap.place(new Animal(vector)));
    }

    @Test
    public void testPlace() { // I could apply DRY here
        RectangularMap recMap = new RectangularMap(3, 6);

        Vector2d[] vectors1 = {new Vector2d(0, 0), new Vector2d(2, 0), new Vector2d(2, 5),
                new Vector2d(0, 5), new Vector2d(1, 1), new Vector2d(1, 0), new Vector2d(0, 4),
                new Vector2d(2, 3)};

        for (Vector2d vector:vectors1) {
            testHelper(recMap, vector, true);
        }

        for (Vector2d vector:vectors1) {
            testHelper(recMap, vector, false);
        }


        Vector2d[] vectors2 = {new Vector2d(0, 129), new Vector2d(4, 2), new Vector2d(1, -1),
                new Vector2d(2, 6), new Vector2d(3, 0), new Vector2d(-1, 0)};

        for (Vector2d vector:vectors2) {
            testHelper(recMap, vector, false);
        }
    }

    @Test
    public void testObjectAt() {
        Vector2d[] vectors = {new Vector2d(0, 0), new Vector2d(0, 7), new Vector2d(1, 0), new Vector2d(1, 7),
        new Vector2d(0, 2), new Vector2d(0, 6), new Vector2d(1, 1), new Vector2d(1, 4), new Vector2d(1, 5)};

        List<Animal> animals = new ArrayList<>(vectors.length);
        for (Vector2d vector:vectors) {
            animals.add(new Animal(vector));
        }


        for (int i = 0; i < animals.size(); i++) {
            Assertions.assertTrue(animals.get(i).isAt(vectors[i]));
        }
    }

    @Test
    public void testMove() {
        RectangularMap recMap = new RectangularMap(4, 4);

        Animal animal1 = new Animal(new Vector2d(0, 0));
        Animal animal2 = new Animal(new Vector2d(1, 0));
        Animal animal3 = new Animal(new Vector2d(0, 3));

        recMap.place(animal1);
        recMap.place(animal2);
        recMap.place(animal3);


        recMap.move(animal1, MoveDirection.RIGHT);
        recMap.move(animal1, MoveDirection.FORWARD);
        Assertions.assertTrue(animal1.isAt(new Vector2d(0, 0)));

        recMap.move(animal1, MoveDirection.BACK);
        Assertions.assertTrue(animal1.isAt(new Vector2d(0, 0)));


        recMap.move(animal2, MoveDirection.RIGHT);
        recMap.move(animal2, MoveDirection.BACK);
        Assertions.assertTrue(animal2.isAt(new Vector2d(1, 0)));

        recMap.move(animal2, MoveDirection.FORWARD);
        Assertions.assertTrue(animal2.isAt(new Vector2d(2, 0)));

        recMap.move(animal2, MoveDirection.LEFT);
        recMap.move(animal2, MoveDirection.BACK);
        Assertions.assertTrue(animal2.isAt(new Vector2d(2, 0)));

        for (int i = 1; i < 4; i++) {
            recMap.move(animal2, MoveDirection.FORWARD);
            Assertions.assertTrue(animal2.isAt(new Vector2d(2, i)));
        }

        recMap.move(animal2, MoveDirection.BACK);
        Assertions.assertTrue(animal2.isAt(new Vector2d(2, 2)));


        recMap.move(animal3, MoveDirection.FORWARD);
        Assertions.assertTrue(animal3.isAt(new Vector2d(0, 3)));

        recMap.move(animal3, MoveDirection.LEFT);
        recMap.move(animal3, MoveDirection.FORWARD);
        Assertions.assertTrue(animal3.isAt(new Vector2d(0, 3)));

        recMap.move(animal3, MoveDirection.BACK);
        Assertions.assertTrue(animal3.isAt(new Vector2d(1, 3)));

        recMap.move(animal3, MoveDirection.FORWARD);
        Assertions.assertTrue(animal3.isAt(new Vector2d(0, 3)));


        recMap.move(new Animal(new Vector2d(0, 0)), MoveDirection.FORWARD);
        Assertions.assertTrue(animal1.isAt(new Vector2d(0, 0)));

        recMap.move(new Animal(new Vector2d(0, 3)), MoveDirection.BACK);
        Assertions.assertTrue(animal3.isAt(new Vector2d(0, 3)));
    }
}
