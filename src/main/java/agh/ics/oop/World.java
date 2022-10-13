package agh.ics.oop;

import java.util.stream.Stream;

public class World {

    public static void run(Direction[] directions) {
        String finalDirection;
        for (Direction direction : directions) {
            finalDirection = switch (direction) {
                case f -> "Zwierzak idzie do przodu";
                case b -> "Zwierzak idzie do tylu";
                case r -> "Zwierzak skreca w prawo";
                case l -> "Zwierzak skreca w lewo";
            };
            System.out.println(finalDirection);
        }
    }

    public static void runStream(Direction[] directions) {
        Stream.of(directions)
                .forEach(Direction::information);
    }

    public static Direction[] strToEnum(String[] directions) {
        Direction[] enumDirections;
        enumDirections = new Direction[directions.length];
        for (int i = 0; i < directions.length; i++) {
            enumDirections[i] = switch (directions[i]) {
                case "f" -> Direction.f;
                case "b" -> Direction.b;
                case "r" -> Direction.r;
                case "l" -> Direction.l;
                default -> null;
            };
        }
        return enumDirections;
    }

    public static Direction[] strToEnumStream(String[] directions) {
        return Stream.of(directions)
                .map(Direction::valueOf)
                .toArray(Direction[]::new);
    }

    public static void main(String[] args) {
        System.out.println("Start");
        Direction[] directions = strToEnumStream(args);
        runStream(directions);
        System.out.println("Stop");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(MapDirection.NORTH.toUnitVector());
        System.out.println(MapDirection.EAST.toUnitVector());
    }

}
