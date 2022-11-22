package agh.ics.oop;

public class Animal implements IMapElement {

    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;

    public Animal() {
        this(new RectangularMap(4, 4));
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
            case FORWARD -> {if (this.map.canMoveTo(this.position.add(this.orientation.toUnitVector())))
                                this.position = this.position.add(this.orientation.toUnitVector());
            }
            case BACKWARD -> {if (this.map.canMoveTo(this.position.add(this.orientation.toUnitVector().opposite())))
                                this.position = this.position.add(this.orientation.toUnitVector().opposite());
            }
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }

//        this.position = position.upperRight(new Vector2d(0, 0))
//                .lowerLeft(new Vector2d(4, 4));
    }

}
