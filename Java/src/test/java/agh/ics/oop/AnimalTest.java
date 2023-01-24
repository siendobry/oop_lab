package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    // in doesTurn tests, boolean assertion is probably sufficient,
    // but there's also an equals assertion in each one, just in case

    @Test
    void doesTurn1() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(3, 2)));
        assertEquals(MapDirection.EAST, animal.getOrientation());
    }

    @Test
    void doesTurn2() {
        Animal animal = new Animal();
        for (int i = 0; i < 7; i++) {
            animal.move(MoveDirection.RIGHT);
        }
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 1)));
        assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }


    @Test
    void doesPosition1() {
        Animal animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 3)));
    }

    @Test
    void doesPosition2() {
        Animal animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(3, 3)));
    }


    @Test
    void doesStayWithinBorder1() {
        Animal animal = new Animal();
        for (int i = 0; i < 4; i++) {
            animal.move(MoveDirection.FORWARD);
        }
        assertTrue(animal.isAt(new Vector2d(2, 4)));
    }

    @Test
    void doesStayWithinBorder2() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        for (int i = 0; i < 4; i++) {
            animal.move(MoveDirection.BACKWARD);
        }
        assertTrue(animal.isAt(new Vector2d(0, 2)));
    }


    @Test
    void doesInterpretStringInput1() {
        Animal animal = new Animal();
        String[] instructions = {"f", "f", "r", "b"};
        MoveDirection[] directionList = OptionsParser.parse(instructions);
        for (MoveDirection direction: directionList) {
            animal.move(direction);
        }
        assertTrue(animal.isAt(new Vector2d(1, 4)));
    }

    @Test
    void doesInterpretStringInput2() {
        Animal animal = new Animal();
        String[] instructions = {};
        MoveDirection[] directionList = OptionsParser.parse(instructions);
        for (MoveDirection direction : directionList) {
            animal.move(direction);
        }
        assertTrue(animal.isAt(new Vector2d(2, 2)));
    }

}
