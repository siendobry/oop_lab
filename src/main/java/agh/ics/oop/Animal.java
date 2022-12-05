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

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                Vector2d possibleMovement = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(possibleMovement)) {
                    this.positionChanged(this.position, possibleMovement);
                    this.position = possibleMovement;
                }
            }
            case BACKWARD -> {
                Vector2d possibleMovement = this.position.add(this.orientation.toUnitVector().opposite());
                if (this.map.canMoveTo(possibleMovement)) {
                    this.positionChanged(this.position, possibleMovement);
                    this.position = possibleMovement;
                }
            }
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
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

}
