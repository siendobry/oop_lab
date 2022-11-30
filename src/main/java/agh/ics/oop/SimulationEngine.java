package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final List<Animal> animals;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.animals = placeAnimals(positions);
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    private List<Animal> placeAnimals(Vector2d[] positions) {
        List<Animal> animals = new ArrayList<>();
        for (Vector2d position: positions) {
            Animal currentAnimal = new Animal(this.map, position);
            this.map.place(currentAnimal);
            animals.add(currentAnimal);
        }
        return animals;
    }

    public void run() {
        for (int i = 0; i<this.directions.length; i++) {
            System.out.println(i);
            this.animals.get(i % this.animals.size()).move(this.directions[i]);
            System.out.println(this.map.toString());
        }
    }

}
