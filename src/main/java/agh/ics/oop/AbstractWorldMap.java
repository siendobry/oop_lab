package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractWorldMap implements IWorldMap {

    protected final List<IMapElement> elements;

    public AbstractWorldMap() {
        this.elements = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position) {
        return !(this.objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            this.elements.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    public Object objectAt(Vector2d position) {
        Object possibleGrass = null;
        for (IMapElement element: this.elements) {
            if (!Objects.equals(position, element.getPosition())) {
                continue;
            }
            if (element instanceof Animal) {
                return element;
            }
            else {
                possibleGrass = element;
            }
        }
        return possibleGrass;
    }

    public abstract Vector2d calcLowerLeft();

    public abstract Vector2d calcUpperRight();

    public String toString() {
        return toString(calcLowerLeft(), calcUpperRight());
    }

    public String toString(Vector2d lowerLeft, Vector2d upperRight) {
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }

}
