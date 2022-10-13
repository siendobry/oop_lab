package agh.ics.oop;
import agh.ics.oop.Direction;

import java.util.stream.Stream;

public class World {

    public static void run(Direction.Directions[] directions) {
        String finalDirection;
        for (Direction.Directions direction : directions) {
            finalDirection = switch (direction) {
                case f -> "Zwierzak idzie do przodu";
                case b -> "Zwierzak idzie do tylu";
                case r -> "Zwierzak skreca w prawo";
                case l -> "Zwierzak skreca w lewo";
            };
            System.out.println(finalDirection);
        }
    }

    public static void runStream(Direction.Directions[] directions) {
        Stream.of(directions)
                .forEach(Direction.Directions::information);
    }

    public static Direction.Directions[] strToEnum(String[] directions) {
        Direction.Directions[] enumDirections;
        enumDirections = new Direction.Directions[directions.length];
        for (int i = 0; i < directions.length; i++) {
            enumDirections[i] = switch (directions[i]) {
                case "f" -> Direction.Directions.f;
                case "b" -> Direction.Directions.b;
                case "r" -> Direction.Directions.r;
                case "l" -> Direction.Directions.l;
                default -> null;
            };
        }
        return enumDirections;
    }

    public static Direction.Directions[] strToEnumStream(String[] directions) {
        return Stream.of(directions)
                .map(Direction.Directions::valueOf)
                .toArray(Direction.Directions[]::new);
    }

    public static void main(String[] args) {
        System.out.println("Start");
        Direction.Directions[] directions = strToEnumStream(args);
        runStream(directions);
        System.out.println("Stop");
    }

}
