package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorldMapTest {

    @Test
    void doesPlaceAnimalsRect() {
        IWorldMap map = new RectangularMap(5, 5);
        map.place(new Animal(map, new Vector2d(4, 4)));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(4, 4))));
        assertEquals("Cannot place an element at position (4, 4)", exception.getMessage());
    }

    @Test
    void doesPlaceAnimalsGrass() {
        IWorldMap map = new GrassField(5);
        map.place(new Animal(map, new Vector2d(4, 4)));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(4, 4))));
        assertEquals("Cannot place an element at position (4, 4)", exception.getMessage());
    }

    @Test
    void doesReturnObjectAtRect() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map, new Vector2d(2, 3));
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(2, 3)));
    }

    @Test
    void doesReturnObjectAtGrass() {
        IWorldMap map = new GrassField(5);
        Animal animal = new Animal(map, new Vector2d(2, 3));
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(2, 3)));
    }

    @Test
    void doesOccupyRect() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map, new Vector2d(3, 3));
        map.place(animal);
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void doesOccupyGrass() {
        IWorldMap map = new GrassField(5);
        Animal animal = new Animal(map, new Vector2d(3, 3));
        map.place(animal);
        assertTrue(map.isOccupied(new Vector2d(3, 3)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    void doesCanMoveToWorkRect() {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map, new Vector2d(4, 3));
        map.place(animal);
        assertTrue(map.canMoveTo(new Vector2d(4, 4)));
        assertFalse(map.canMoveTo(new Vector2d(4, 3)));
    }

    @Test
    void doesCanMoveToWorkGrass() {
        IWorldMap map = new GrassField(5);
        Animal animal = new Animal(map, new Vector2d(4, 3));
        map.place(animal);
        assertTrue(map.canMoveTo(new Vector2d(4, 4)));
        assertFalse(map.canMoveTo(new Vector2d(4, 3)));
    }

}
