package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected final HashMap<Vector2d, IMapElement> elements;

    public AbstractWorldMap() {
        this.elements = new HashMap<>();
    }

    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.elements.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        return elements.get(position);
    }

    public abstract Vector2d calcLowerLeft();

    public abstract Vector2d calcUpperRight();

    public String toString() {
        return toString(calcLowerLeft(), calcUpperRight());
    }

    public String toString(Vector2d lowerLeft, Vector2d upperRight) {
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement tmp = this.elements.get(oldPosition);
        this.elements.remove(oldPosition);
        this.elements.put(newPosition, tmp);
    }

}
