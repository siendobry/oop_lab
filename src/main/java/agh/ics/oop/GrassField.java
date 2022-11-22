package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap {

    private final ArrayList<Vector2d> garden;

    public GrassField(int grassCount) {
        super();
        this.garden = new ArrayList<>();
        int MAP_BOUNDARY = (int) Math.sqrt(10 * grassCount);
        for (int i = 0; i < MAP_BOUNDARY; i++) {
            for (int j = 0; j < MAP_BOUNDARY; j++) {
                this.garden.add(new Vector2d(i, j));
            }
        }
        this.putGrass(grassCount);
    }

    @Override
    public boolean place(Animal animal) {
        Object object = this.objectAt(animal.getPosition());
        if (object instanceof Grass) {
            garden.remove(animal.getPosition());
            this.elements.remove(object);
            putGrass(1);
        }
        return super.place(animal);
    }

    private int getRdmIdx(int size) {
        return (int)(Math.random() * size);
    }

    // O(n^2):(
    private void putGrass(int grassCount) {
        for (int i = 0; i < grassCount; i++) {
            int idx = this.getRdmIdx(garden.size());
            this.elements.add(new Grass(garden.get(idx)));
            garden.remove(idx);
        }
    }

    public Vector2d calcLowerLeft() {
        Vector2d lowerCorner = new Vector2d(0, 0);
        for (IMapElement element: this.elements) {
            lowerCorner = lowerCorner.lowerLeft(element.getPosition());
        }
        return lowerCorner;
    }

    public Vector2d calcUpperRight() {
        Vector2d upperCorner = new Vector2d(0, 0);
        for (IMapElement element: this.elements) {
            upperCorner = upperCorner.upperRight(element.getPosition());
        }
        return upperCorner;
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