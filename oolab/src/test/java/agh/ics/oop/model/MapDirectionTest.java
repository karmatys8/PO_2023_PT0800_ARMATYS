package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MapDirectionTest {
    @Test
    public void testNext() {
        MapDirection test1 = MapDirection.NORTH.next();
        Assertions.assertEquals(test1, MapDirection.EAST);

        MapDirection test2 = MapDirection.EAST.next();
        Assertions.assertEquals(test2, MapDirection.SOUTH);

        MapDirection test3 = MapDirection.SOUTH.next();
        Assertions.assertEquals(test3, MapDirection.WEST);

        MapDirection test4 = MapDirection.WEST.next();
        Assertions.assertEquals(test4, MapDirection.NORTH);
    }

    @Test
    public void testPrevious() {
        MapDirection test1 = MapDirection.NORTH.previous();
        Assertions.assertEquals(test1, MapDirection.WEST);

        MapDirection test2 = MapDirection.EAST.previous();
        Assertions.assertEquals(test2, MapDirection.NORTH);

        MapDirection test3 = MapDirection.SOUTH.previous();
        Assertions.assertEquals(test3, MapDirection.EAST);

        MapDirection test4 = MapDirection.WEST.previous();
        Assertions.assertEquals(test4, MapDirection.SOUTH);
    }
}
