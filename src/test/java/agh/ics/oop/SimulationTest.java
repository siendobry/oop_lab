package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationTest {

    @Test
    void movementTest1() {
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        List<Animal> expectedAnimals = new ArrayList<>();
        Animal animal1 = new Animal(new RectangularMap(10, 5), new Vector2d(2, 0));
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.RIGHT);
        expectedAnimals.add(animal1);
        Animal animal2 = new Animal(new RectangularMap(10, 5), new Vector2d(3, 4));
        expectedAnimals.add(animal2);
        List<Animal> animals = ((SimulationEngine)engine).getAnimals();
        for (int i=0; i<animals.size(); i++) {
            assertEquals(expectedAnimals.get(i).getPosition(), animals.get(i).getPosition());
            assertEquals(expectedAnimals.get(i).getOrientation(), animals.get(i).getOrientation());
        }
    }

    @Test
    void movementTest2() {
        String[] args = new String[]{"r", "f", "l", "l", "l", "b", "f", "f", "b", "l", "r", "b", "r", "l", "l", "l",
                                    "b", "b", "f", "f", "r", "b", "b", "l", "l", "b", "f", "r", "r", "b", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 10);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(2,2),
                                new Vector2d(3,4), new Vector2d(8,6) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        List<Animal> expectedAnimals = new ArrayList<>();
        Animal animal1 = new Animal(new RectangularMap(10, 10), new Vector2d(0, 0));
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.RIGHT);
        expectedAnimals.add(animal1);
        Animal animal2 = new Animal(new RectangularMap(10, 10), new Vector2d(2, 6));
        animal2.move(MoveDirection.RIGHT);
        animal2.move(MoveDirection.RIGHT);
        expectedAnimals.add(animal2);
        Animal animal3 = new Animal(new RectangularMap(10, 10), new Vector2d(0, 4));
        animal3.move(MoveDirection.LEFT);
        expectedAnimals.add(animal3);
        Animal animal4 = new Animal(new RectangularMap(10, 10), new Vector2d(8, 4));
        animal4.move(MoveDirection.RIGHT);
        animal4.move(MoveDirection.RIGHT);
        expectedAnimals.add(animal4);
        List<Animal> animals = ((SimulationEngine)engine).getAnimals();
        System.out.println(expectedAnimals);
        System.out.println(animals);
        for (int i=0; i<animals.size(); i++) {
            assertEquals(expectedAnimals.get(i).getPosition(), animals.get(i).getPosition());
            assertEquals(expectedAnimals.get(i).getOrientation(), animals.get(i).getOrientation());
        }
    }

    @Test
    void movementTest3() {
        String[] args = new String[]{"f", "r", "f", "f", "r", "f", "f", "r", "f", "f", "r", "f", "r", "f", "l"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = { new Vector2d(0,1) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        Animal animal = new Animal(new RectangularMap(10, 5), new Vector2d(1, 1));
        List<Animal> animals = ((SimulationEngine)engine).getAnimals();
            assertEquals(animal.getPosition(), animals.get(0).getPosition());
            assertEquals(animal.getOrientation(), animals.get(0).getOrientation());
    }

}
