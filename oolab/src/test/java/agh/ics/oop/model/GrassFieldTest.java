package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GrassFieldTest {

    @Test
    public void testisOccupied() {
        GrassField grassField = new GrassField(4); // grass will be placed from (0,0) up to (20, 20)

        Vector2d[] vectors = {new Vector2d(0, 0), new Vector2d(12, 17), new Vector2d(19, 0)};
        // 3 animals so at least 1 grass won't be covered

        for (Vector2d vector:vectors) {
            grassField.place(new Animal(vector));
        }

        int occupiedCounter = 0;
        for (int i = 0; i< 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (grassField.isOccupied(new Vector2d(i, j))) {
                    occupiedCounter++;
                }
            }
        }

        Assertions.assertEquals(3, occupiedCounter);
    }

    void testHelper(GrassField grassField, Vector2d vector, boolean result) {
        // ideally I would pass assertTrue/assertFalse instead of boolean but idk how to do it yet
        Assertions.assertEquals(result, grassField.place(new Animal(vector)));
    }

    @Test
    public void testPlace() {
        GrassField grassField = new GrassField(10);

        Vector2d[] vectors1 = {new Vector2d(0, 0), new Vector2d(2, 0), new Vector2d(2, 5),
                new Vector2d(0, 5), new Vector2d(1, 1), new Vector2d(1, 0), new Vector2d(0, 4),
                new Vector2d(2, 3)};

        for (Vector2d vector:vectors1) {
            testHelper(grassField, vector, true);
        }

        for (Vector2d vector:vectors1) {
            testHelper(grassField, vector, false);
        }


        Vector2d[] vectors2 = {new Vector2d(0, 129), new Vector2d(4, 2), new Vector2d(1, -1),
                new Vector2d(2, 6), new Vector2d(3, 0), new Vector2d(-1, 0), new Vector2d(-77777, 8888889),
                new Vector2d(-1,-1), new Vector2d(443434353, 347584754), new Vector2d(2147483647, -2147483648)};

        for (Vector2d vector:vectors2) {
            testHelper(grassField, vector, true);
        }
    }

    @Test
    public void testObjectAt() {
        GrassField grassField = new GrassField(9);  // grass will be placed from (0,0) up to (30, 30)

        int grassCounter = 0;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (grassField.objectAt(new Vector2d(i, j)) != null) {
                    grassCounter++;
                }
            }
        }

        Assertions.assertEquals(9, grassCounter);


        Vector2d[] vectors = {new Vector2d(-10, 0), new Vector2d(0, -6), new Vector2d(1, 80), new Vector2d(-1, -4),
                new Vector2d(-2147483648, 2147483647), new Vector2d(0, 777), new Vector2d(100, 100)};

        for (Vector2d vector:vectors) {
            grassField.place(new Animal(vector));
        }


        for (Vector2d vector : vectors) {
            Assertions.assertNotNull(grassField.objectAt(vector));
        }
    }

    // Note:
    // testing canMoveTo is redundant right now because only isOccupied is invoked in there
    // if you were to change canMoveTo implementation please add tests for it

    @Test
    public void testMove() {
        GrassField grassField = new GrassField(25);

        Animal animal1 = new Animal(new Vector2d(0, 0));
        Animal animal2 = new Animal(new Vector2d(1, 10));
        Vector2d vector3 = new Vector2d(-49, -49);
        Animal animal3 = new Animal(vector3);
        Vector2d vector4 = new Vector2d(50, 51);
        Animal animal4 = new Animal(vector4);

        grassField.place(animal1);
        grassField.place(animal2);
        grassField.place(animal3);
        grassField.place(animal4);


        for (int i = 1; i < 6; i++) {
            grassField.move(animal1, MoveDirection.FORWARD);
            Assertions.assertTrue(animal1.isAt(new Vector2d(0, i)));
        }


        grassField.move(animal2, MoveDirection.RIGHT);
        for (int i = 0; i < 3; i++) {
            grassField.move(animal2, MoveDirection.BACK);
            Assertions.assertTrue(animal2.isAt(new Vector2d(-i, 10)));
        }


        for (int i = 0; i < 14; i++) {
            grassField.move(animal3, MoveDirection.LEFT);
            Assertions.assertTrue(animal3.isAt(vector3));
        }

        grassField.move(animal3, MoveDirection.FORWARD);
        Assertions.assertTrue(animal3.isAt(new Vector2d(-49, -50)));


        grassField.move(animal4, MoveDirection.LEFT);
        grassField.move(animal4, MoveDirection.FORWARD);
        Assertions.assertTrue(animal4.isAt(new Vector2d(49, 51)));
        grassField.move(animal4, MoveDirection.BACK);
        Assertions.assertTrue(animal4.isAt(vector4));
    }
}
