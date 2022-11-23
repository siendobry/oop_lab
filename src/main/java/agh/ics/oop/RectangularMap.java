package agh.ics.oop;

public class RectangularMap implements IWorldMap {

    private final Animal[][] map;

    public RectangularMap(int width, int height) {
        this.map = new Animal[width][height];
    }

    public boolean canMoveTo(Vector2d position) {
        return position.x>=0
                && position.x<this.map.length
                && position.y>=0
                && position.y<this.map[0].length
                && this.map[position.x][position.y] == null;
    }

    public boolean place(Animal animal) {
        Vector2d animalPos = animal.getPosition();
        if (!isOccupied(new Vector2d(animalPos.x, animalPos.y))) {
            this.map[animalPos.x][animalPos.y] = animal;
            return true;
        }
        return false;
    }

    public Vector2d moveAnimal(Animal animal, Vector2d direction) {
        Vector2d current = animal.getPosition();
        if (canMoveTo(current.add(direction))) {
            this.map[current.x][current.y] = null;
            this.map[current.x + direction.x][current.y + direction.y] = animal;
        }
        return animal.getPosition().add(direction);
    }

    public boolean isOccupied(Vector2d position) {
        return this.map[position.x][position.y] != null;
    }

    public Object objectAt(Vector2d position) {
        return this.map[position.x][position.y];
    }

    public String toString(Vector2d lowerLeft, Vector2d upperRight) {
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }

}
