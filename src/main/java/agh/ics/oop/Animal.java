package agh.ics.oop;

public class Animal {

    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    @Override
    public String toString() {
        return "Position: " + this.position + ", orientation: " + this.orientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> {
                if (!(  this.position.x == 4 && this.orientation == MapDirection.EAST ||
                        this.position.x == 0 && this.orientation == MapDirection.WEST ||
                        this.position.y == 4 && this.orientation == MapDirection.NORTH ||
                        this.position.y == 0 && this.orientation == MapDirection.SOUTH)) {
                    this.position = this.position.add(this.orientation.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (!(  this.position.x==4 && this.orientation==MapDirection.WEST ||
                        this.position.x==0 && this.orientation==MapDirection.EAST ||
                        this.position.y==4 && this.orientation==MapDirection.SOUTH ||
                        this.position.y==0 && this.orientation==MapDirection.NORTH)) {
                    this.position = this.position.add(this.orientation.toUnitVector().opposite());
                }
            }
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
        }
    }

}
