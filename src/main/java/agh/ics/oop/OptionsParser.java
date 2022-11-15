package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {

    public static MoveDirection[] parse(String[] instructions) {
        MoveDirection[] directions = new MoveDirection[instructions.length];
        int i = 0;
        for (String instruction: instructions) {
            switch (instruction) {
                case "f", "forward" -> directions[i] = MoveDirection.FORWARD;
                case "b", "backward" -> directions[i] = MoveDirection.BACKWARD;
                case "l", "left" -> directions[i] = MoveDirection.LEFT;
                case "r", "right" -> directions[i] = MoveDirection.RIGHT;
                default -> i -= 1;
            }
            i += 1;
        }
        directions = Arrays.copyOf(directions, i);
        return directions;
    }

}
