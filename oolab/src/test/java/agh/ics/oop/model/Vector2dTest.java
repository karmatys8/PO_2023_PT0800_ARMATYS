package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {
    @Test
    public void testEquals() {
        Vector2d vector1 = new Vector2d(1, 2);
        Assertions.assertTrue(vector1.equals(vector1));

        Vector2d vector2 = new Vector2d(1, 2);
        Assertions.assertTrue(vector2.equals(vector1));

        Vector2d vector3 = new Vector2d(0, 2);
        Assertions.assertFalse(vector3.equals(vector2));

        Vector2d vector4 = new Vector2d(2, 0);
        Assertions.assertFalse(vector4.equals(vector3));
    }

    @Test
    public void testTString() {
        Vector2d vector1 = new Vector2d(-0, 0);
        Assertions.assertEquals(vector1.toString(), "(0,0)");

        Vector2d vector2 = new Vector2d(-1, -10);
        Assertions.assertEquals(vector2.toString(), "(-1,-10)");

        Vector2d vector3 = new Vector2d(-128, 127);
        Assertions.assertEquals(vector3.toString(), "(-128,127)");

        Vector2d vector4 = new Vector2d(13, 37);
        Assertions.assertEquals(vector4.toString(), "(13,37)");
    }

    @Test
    public void testPrecedes() {
        Vector2d vector1 = new Vector2d(1, 3);
        Assertions.assertTrue(vector1.precedes(vector1));

        Vector2d vector2 = new Vector2d(0, 0);
        Assertions.assertTrue(vector2.precedes(vector1));

        Vector2d vector3 = new Vector2d(-1, 5);
        Assertions.assertFalse(vector3.precedes(vector2));

        Vector2d vector4 = new Vector2d(-4, 4);
        Assertions.assertTrue(vector4.precedes(vector3));
    }

    @Test
    public void testFollows() {
        Vector2d vector1 = new Vector2d(-1, -5);
        Assertions.assertTrue(vector1.follows(vector1));

        Vector2d vector2 = new Vector2d(-1, -1);
        Assertions.assertTrue(vector2.follows(vector1));

        Vector2d vector3 = new Vector2d(6, 80);
        Assertions.assertTrue(vector3.follows(vector2));

        Vector2d vector4 = new Vector2d(70, 79);
        Assertions.assertFalse(vector4.follows(vector3));
    }

    @Test
    public void testUpperRight() {
        Vector2d vector1 = new Vector2d(-9, 2);
        Assertions.assertEquals(vector1.upperRight(vector1), vector1);

        Vector2d vector2 = new Vector2d(-7, 1);
        Assertions.assertEquals(vector2.upperRight(vector1), new Vector2d(-7, 2));

        Vector2d vector3 = new Vector2d(-7, -3);
        Assertions.assertEquals(vector3.upperRight(vector2), vector2);
    }

    @Test
    public void testLowerLeft() {
        Vector2d vector1 = new Vector2d(3, 1);
        Assertions.assertEquals(vector1.lowerLeft(vector1), vector1);

        Vector2d vector2 = new Vector2d(-1, 7);
        Assertions.assertEquals(vector2.lowerLeft(vector1), new Vector2d(-1, 1));

        Vector2d vector3 = new Vector2d(-1, 10);
        Assertions.assertEquals(vector3.lowerLeft(vector2), vector2);
     }
     @Test
    public void testAdd() {
        Vector2d vector1 = new Vector2d(7, 6);
        Assertions.assertEquals(vector1.add(vector1), new Vector2d(14, 12));

        Vector2d vector2 = new Vector2d(-7, -1);
        Assertions.assertEquals(vector2.add(vector1), new Vector2d(0, 5));

        Vector2d vector3 = new Vector2d(0, 0);
        Assertions.assertEquals(vector3.add(vector2), vector2);
     }

    @Test
    public void testSubtract() {
        Vector2d vector1 = new Vector2d(5, 9);
        Assertions.assertEquals(vector1.subtract(vector1), new Vector2d(0, 0));

        Vector2d vector2 = new Vector2d(-4, 1);
        Assertions.assertEquals(vector2.subtract(vector1), new Vector2d(-9, -8));

        Vector2d vector3 = new Vector2d(0, 0);
        Assertions.assertEquals(vector3.subtract(vector2), new Vector2d(4, -1));

        Vector2d vector4 = new Vector2d(3, -11);
        Assertions.assertEquals(vector4.subtract(vector3), vector4);
    }

    @Test
    public void testOpposite() {
        Vector2d vector1 = new Vector2d(3, -7);
        Assertions.assertEquals(vector1.opposite(), new Vector2d(-3, 7));

        Vector2d vector2 = new Vector2d(0, 0);
        Assertions.assertEquals(vector2.opposite(), vector2);

        Vector2d vector3 = new Vector2d(-1, 1);
        Assertions.assertEquals(vector3.opposite(), new Vector2d(1, -1));
    }
}
