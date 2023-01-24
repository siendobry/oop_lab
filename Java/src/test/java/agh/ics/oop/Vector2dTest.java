package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    void doesEqual() {
        Vector2d vec = new Vector2d(2, 5);
        assertEquals(new Vector2d(2, 5), vec);
    }

    @Test
    void doesToStringEqual() {
        Vector2d vec = new Vector2d(3, 5);
        assertEquals("(3, 5)", vec.toString());
    }

    @Test
    void doesPrecede() {
        Vector2d vec1 = new Vector2d(3, 6);
        Vector2d vec2 = new Vector2d(2, 5);
        assertFalse(vec1.precedes(vec2));
    }

    @Test
    void doesFollow() {
        Vector2d vec1 = new Vector2d(3, 6);
        Vector2d vec2 = new Vector2d(2, 5);
        assertTrue(vec1.follows(vec2));
    }

    @Test
    void doesUpperRight() {
        Vector2d vec1 = new Vector2d(3, 5);
        Vector2d vec2 = new Vector2d(2, 6);
        assertEquals(new Vector2d(3, 6), vec1.upperRight(vec2));
    }

    @Test
    void doesLowerLeft() {
        Vector2d vec1 = new Vector2d(3, 5);
        Vector2d vec2 = new Vector2d(2, 6);
        assertEquals(new Vector2d(2, 5), vec1.lowerLeft(vec2));
    }

    @Test
    void doesAdd() {
        Vector2d vec1 = new Vector2d(3, 5);
        Vector2d vec2 = new Vector2d(2, 6);
        assertEquals(new Vector2d(5, 11), vec1.add(vec2));
    }

    @Test
    void doesSubtract() {
        Vector2d vec1 = new Vector2d(3, 5);
        Vector2d vec2 = new Vector2d(2, 6);
        assertEquals(new Vector2d(1, -1), vec1.subtract(vec2));
    }

    @Test
    void doesOpposite() {
        Vector2d vec = new Vector2d(3, 5);
        assertEquals(new Vector2d(-3, -5), vec.opposite());
    }
}
