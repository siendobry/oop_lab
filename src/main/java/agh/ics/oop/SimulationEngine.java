package agh.ics.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationEngine implements IEngine {

    private MoveDirection[] directions;
    private final IWorldMap map;
    private final List<Animal> animals;
    private int moveDelay;

    public SimulationEngine(IWorldMap map, Vector2d[] positions) {
        this(new MoveDirection[]{}, map, positions);
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.animals = placeAnimals(positions);
        this.moveDelay = 500;
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }

    public void setMoveDelay(int delay) {
        this.moveDelay = delay;
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
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println(i);
            this.animals.get(i % this.animals.size()).move(this.directions[i]);
            System.out.println(this.map.toString());
        }
    }

}
