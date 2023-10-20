package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;


public class OptionsParser {
    public static MoveDirection[] readInstructions(String[] args) {
        MoveDirection[] newArray = new MoveDirection[args.length];
        int idx = 0;

        for(String arg: args) {
            MoveDirection value = switch(arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACK;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            if (value != null) {
                newArray[idx++] = value;
            }
        }

        return Arrays.copyOf(newArray, idx);
    }
}
