package agh.ics.oop;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.Scanner;

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
//        System.out.println("Start");
//        Direction[] directions = strToEnumStream(args);
//        runStream(directions);
//        System.out.println("Stop");

//        Animal animal = new Animal();
//        System.out.println(animal);
//
//        // Classic approach
//        // in order for it to work, one has to comment lines 49 through 52
//        MoveDirection[] directionList1 = OptionsParser.parse(args);
//        for (MoveDirection direction: directionList1) {
//            animal.move(direction);
//            System.out.println(animal);
//        }
//
//        // More interactive approach
//        Scanner sc = new Scanner(System.in);
//        String[] instructions = sc.nextLine().split(" ");
//        while (!instructions[0].equals("finish")) {     // in order to break the loop type "finish" into the stdin
//            MoveDirection[] directionList2 = OptionsParser.parse(instructions);
//            for (MoveDirection direction: directionList2) {
//                animal.move(direction);
//            }
//            System.out.println(animal);
//            instructions = sc.nextLine().split(" ");
//        }

        // Forbidding two Animal objects being at the same place,
        // can be done by creating a Map class, which contains
        // boolean information (for each position in the map
        // boundaries), whether there is an Animal object at
        // a particular position.

        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        // idk if everything before lines 83 through 87 can be simply deleted from main??
        // thanks from mountain for your response:)
    }

}
