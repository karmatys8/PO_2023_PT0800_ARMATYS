package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void testOrientation() {
        MoveValidator<Vector2d> moveValidator = new RectangularMap(5, 5);

        Animal animal1 = new Animal();
        Assertions.assertEquals(MapDirection.NORTH, animal1.getOrientation());

        animal1.move(MoveDirection.LEFT, moveValidator);
        Assertions.assertEquals(MapDirection.WEST, animal1.getOrientation());

        animal1.move(MoveDirection.FORWARD, moveValidator);
        Assertions.assertEquals(MapDirection.WEST, animal1.getOrientation());

        animal1.move(MoveDirection.LEFT, moveValidator);
        Assertions.assertEquals(MapDirection.SOUTH, animal1.getOrientation());

        animal1.move(MoveDirection.RIGHT, moveValidator);
        Assertions.assertEquals(MapDirection.WEST, animal1.getOrientation());

        for (int i = 0; i < 6; i++) animal1.move(MoveDirection.LEFT, moveValidator);
        Assertions.assertEquals(MapDirection.EAST, animal1.getOrientation());
    }

    @Test
    public void testMoving() {
        MoveValidator<Vector2d> moveValidator = new RectangularMap(5, 5);

        Animal animal1 = new Animal();
        System.out.println(animal1.getPosition());
        animal1.move(MoveDirection.FORWARD, moveValidator);
        System.out.println(animal1.getPosition());
        Assertions.assertTrue(animal1.isAt(new Vector2d(2, 3)));

        animal1.move(MoveDirection.RIGHT, moveValidator);
        animal1.move(MoveDirection.FORWARD, moveValidator);
        Assertions.assertTrue(animal1.isAt(new Vector2d(3, 3)));

        animal1.move(MoveDirection.LEFT, moveValidator);
        animal1.move(MoveDirection.BACK, moveValidator);
        Assertions.assertTrue(animal1.isAt(new Vector2d(3, 2)));

        animal1.move(MoveDirection.BACK, moveValidator);
        Assertions.assertTrue(animal1.isAt(new Vector2d(3, 1)));

        animal1.move(MoveDirection.FORWARD, moveValidator);
        Assertions.assertTrue(animal1.isAt(new Vector2d(3, 2)));
    }

    @Test
    public void testBoundaries() {
        MoveValidator<Vector2d> moveValidator = new RectangularMap(5, 5);

        Animal animal1 = new Animal(new Vector2d(1, 1));
        Assertions.assertTrue(animal1.isAt(new Vector2d(1, 1)));

        Vector2d vector1 = new Vector2d(1, 0);
        for (int i = 0; i < 3; i++) {
            animal1.move(MoveDirection.BACK, moveValidator);
            Assertions.assertTrue(animal1.isAt(vector1));        }

        animal1.move(MoveDirection.LEFT, moveValidator);
        animal1.move(MoveDirection.FORWARD, moveValidator);
        Assertions.assertTrue(animal1.isAt(new Vector2d(0, 0)));

        Vector2d vector2 = new Vector2d(0, 4);
        Animal animal2 = new Animal(vector2);
        animal2.move(MoveDirection.FORWARD, moveValidator);
        Assertions.assertTrue(animal2.isAt(vector2));

        animal2.move(MoveDirection.RIGHT, moveValidator);
        animal2.move(MoveDirection.FORWARD, moveValidator);
        Assertions.assertTrue(animal2.isAt(new Vector2d(1, 4)));

        animal2.move(MoveDirection.BACK, moveValidator);
        Assertions.assertTrue(animal2.isAt(vector2));
    }
}
