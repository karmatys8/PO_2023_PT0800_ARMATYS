package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;


public class OptionsParser {
    public static MoveDirection[] f(String[] args) {
        MoveDirection[] result = new MoveDirection[0];

        for(String arg: args) {
            MoveDirection value = switch(arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACK;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            MoveDirection[] newArray = new MoveDirection[result.length +1];
            if (value != null) {
                System.arraycopy(result, 0, newArray, 0, result.length);
                newArray[result.length] = value;
                result = newArray;
            }
        }

        return result;
    }
}
