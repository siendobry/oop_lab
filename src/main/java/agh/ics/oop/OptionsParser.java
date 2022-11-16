package agh.ics.oop;

public class OptionsParser {

    public static MoveDirection[] parse(String[] instructions) {
        int length = 0;
        for (String instruction: instructions) {
            switch (instruction) {
                case "f", "forward", "b", "backward", "l", "left", "r", "right" -> length += 1;
            }
        }
        MoveDirection[] directions = new MoveDirection[length];
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
        return directions;
    }

}
