package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement {

    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observerList = new ArrayList<>();

    public Animal() {
        this(new RectangularMap(5, 5));
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.position = initialPosition;
        this.map = map;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return this.orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    // positionChanged is now also called on orientation change -
    // - it does not match the method's name, but otherwise, assuming that
    // the provided commands were only to change the orientation
    // of the animals, no animation would be triggered
    public void move(MoveDirection direction) {
        Vector2d oldPosition = this.getPosition();
        Vector2d possibleMovement = this.position.add(this.orientation.toUnitVector());
        if (this.map.canMoveTo(possibleMovement)) {
            switch (direction) {
                case FORWARD -> this.position = possibleMovement;
                case BACKWARD -> this.position = possibleMovement.opposite();
                case RIGHT -> this.orientation = this.orientation.next();
                case LEFT -> this.orientation = this.orientation.previous();
            }
            this.positionChanged(oldPosition, this.getPosition());
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observerList.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.observerList.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer: this.observerList) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public String getImageUrl() {
        String orientation = null;
        switch (this.orientation) {
            case NORTH -> orientation = "src/main/resources/up.png";
            case SOUTH -> orientation = "src/main/resources/down.png";
            case EAST -> orientation = "src/main/resources/right.png";
            case WEST -> orientation = "src/main/resources/left.png";
        }
        return orientation;
    }

}
