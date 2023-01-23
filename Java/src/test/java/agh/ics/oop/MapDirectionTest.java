package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    void northNext() {
        MapDirection next = MapDirection.NORTH.next();
        assertEquals(MapDirection.EAST, next);
    }

    @Test
    void southNext() {
        MapDirection next = MapDirection.SOUTH.next();
        assertEquals(MapDirection.WEST, next);
    }

    @Test
    void westNext() {
        MapDirection next = MapDirection.WEST.next();
        assertEquals(MapDirection.NORTH, next);
    }

    @Test
    void eastNext() {
        MapDirection next = MapDirection.EAST.next();
        assertEquals(MapDirection.SOUTH, next);
    }


    @Test
    void northPrevious() {
        MapDirection previous = MapDirection.NORTH.previous();
        assertEquals(MapDirection.WEST, previous);
    }

    @Test
    void southPrevious() {
        MapDirection previous = MapDirection.SOUTH.previous();
        assertEquals(MapDirection.EAST, previous);
    }

    @Test
    void westPrevious() {
        MapDirection previous = MapDirection.WEST.previous();
        assertEquals(MapDirection.SOUTH, previous);
    }

    @Test
    void eastPrevious() {
        MapDirection previous = MapDirection.EAST.previous();
        assertEquals(MapDirection.NORTH, previous);
    }

}
