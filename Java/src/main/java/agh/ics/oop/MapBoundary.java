package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    private static class CompareX implements Comparator<Vector2d> {
        public int compare(Vector2d v1, Vector2d v2) {
            if (v1.getX() - v2.getX() == 0) {
                return v1.getY() - v2.getY();
            }
            return v1.getX() - v2.getX();
        }
    }

    private static class CompareY implements Comparator<Vector2d> {
        public int compare(Vector2d v1, Vector2d v2) {
            if (v1.getY() - v2.getY() == 0) {
                return v1.getX() - v2.getX();
            }
            return v1.getY() - v2.getY();
        }
    }

    private final TreeSet<Vector2d> setX = new TreeSet<>(new CompareX());
    private final TreeSet<Vector2d> setY = new TreeSet<>(new CompareY());

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.remove(oldPosition);
        this.add(newPosition);
    }

    public void add(Vector2d vec) {
        this.setX.add(vec);
        this.setY.add(vec);
    }

    public void remove(Vector2d vec) {
        this.setX.remove(vec);
        this.setY.remove(vec);
    }

    public int getMinX() {
        return this.setX.first().getX();
    }

    public int getMaxX() {
        return this.setX.last().getX();
    }

    public int getMinY() {
        return this.setY.first().getY();
    }

    public int getMaxY() {
        return this.setY.last().getY();
    }

}
