package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {

    private final int width;
    private final int height;

    public RectangularMap(int width, int height) {
        super();
        this.width = width - 1;
        this.height = height - 1;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0))
                && position.precedes(new Vector2d(width, height))
                && super.canMoveTo(position);
    }

    public Vector2d calcLowerLeft() {
        return new Vector2d(0, 0);
    }

    public Vector2d calcUpperRight() {
        return new Vector2d(this.width, this.height);
    }

}
