package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final IWorldMap map;
    private final List<Animal> animals;

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.animals = placeAnimals(positions);
    }

    private List<Animal> placeAnimals(Vector2d[] positions) {
        List<Animal> animals = new ArrayList<>();
        for (Vector2d position: positions) {
            if (!this.map.isOccupied(position)) {
                Animal animal = new Animal(this.map, position);
                animals.add(animal);
                this.map.place(animal);
            }
        }
        return animals;
    }

    public void run() {
        for (int i = 0; i<this.directions.length; i++) {
//            System.out.println(i);
            this.animals.get(i % this.animals.size()).move(this.directions[i]);
//            System.out.println(this.map.toString(new Vector2d(0, 0), new Vector2d(9, 9)));
        }
    }

}
