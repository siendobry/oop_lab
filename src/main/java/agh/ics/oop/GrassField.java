package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final ArrayList<Vector2d> garden;
    private final MapBoundary mapBorder = new MapBoundary();

    public GrassField(int grassCount) {
        this.garden = new ArrayList<>();
        int mapBoundary = (int) Math.sqrt(10 * grassCount);
        for (int i = 0; i < mapBoundary; i++) {
            for (int j = 0; j < mapBoundary; j++) {
                this.garden.add(new Vector2d(i, j));
            }
        }
        this.putGrass(grassCount);
    }

    @Override
    public void place(Animal animal) {
        Object object = this.objectAt(animal.getPosition());
        if (object instanceof Grass) {
            garden.remove(animal.getPosition());
            this.elements.remove(object);
            this.mapBorder.remove(animal.getPosition());
            this.putGrass(1);
        }
        super.place(animal);
        this.mapBorder.add(animal.getPosition());
        animal.addObserver(this.mapBorder);
    }

    private int getRdmIdx(int size) {
        return (int)(Math.random() * size);
    }

    private void putGrass(int grassCount) {
        for (int i = 0; i < grassCount; i++) {
            int idx = this.getRdmIdx(garden.size());
            this.elements.put(garden.get(idx), new Grass(garden.get(idx)));
            this.mapBorder.add(garden.get(idx));
            garden.remove(idx);
        }
    }

    public Vector2d calcLowerLeft() {
        return new Vector2d(this.mapBorder.getMinX(), this.mapBorder.getMinY());
    }

    public Vector2d calcUpperRight() {
        return new Vector2d(this.mapBorder.getMaxX(), this.mapBorder.getMaxY());
    }


    // generating new Grass object whenever another instance gets 'eaten'

    // if I am correct, garbage collector should get rid of old Grass object's instance
    // as the only reference to it was in the HashMap, and it got removed
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Object beforeMovement = this.objectAt(newPosition);
        super.positionChanged(oldPosition, newPosition);
        if (beforeMovement instanceof Grass) {
            this.garden.add(oldPosition);
            this.putGrass(1);
        }
    }

}


//    private int getRdmPos() {
//        return (int)(Math.random() * MAP_BOUNDARY) + 1;
//    }

// putGrass' time complexity - O(n) (assuming HashMap operations' time complexities are O(1))
// probably messes up probability
//    private void putGrass(int grassCount) {
//        Vector2d availablePos = new Vector2d(MAP_BOUNDARY, MAP_BOUNDARY);
//        HashMap<Vector2d, Boolean> takenPos = new HashMap<>();
//        for (int i = 0; i < grassCount; i++) {
//            Vector2d randomPos = new Vector2d(getRdmPos(), getRdmPos());
//            if (takenPos.get(randomPos) == null) {
//                this.elements.add(new Grass(randomPos));
//                takenPos.put(randomPos, Boolean.TRUE);
//            }
//            else {
//                this.elements.add(new Grass(availablePos));
//                takenPos.put(availablePos, Boolean.TRUE);
//            }
//            while (takenPos.get(availablePos) != null) {
//                availablePos = availablePos.add(new Vector2d(-1, 0));
//                if (availablePos.x == 0) {
//                    availablePos = availablePos.add(new Vector2d(MAP_BOUNDARY, -1));
//                }
//            }
//        }
//    }