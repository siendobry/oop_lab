package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {

    protected final HashMap<Vector2d, IMapElement> elements;

    public AbstractWorldMap() {
        this.elements = new HashMap<>();
    }

    public Map<Vector2d, IMapElement> getElements() {
        return Collections.unmodifiableMap(this.elements);
    }

    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    public void place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.elements.put(animal.getPosition(), animal);
            animal.addObserver(this);
        }
        else {
            throw new IllegalArgumentException("Cannot place an element at position " + animal.getPosition());
        }
    }

    public Object objectAt(Vector2d position) {
        return elements.get(position);
    }

    public String toString() {
        return toString(calcLowerLeft(), calcUpperRight());
    }

    public String toString(Vector2d lowerLeft, Vector2d upperRight) {
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = this.elements.remove(oldPosition);
        this.elements.put(newPosition, element);
    }

}
