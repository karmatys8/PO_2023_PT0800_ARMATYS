package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;


public class OptionsParser {
    public static List<MoveDirection> readInstructions(String[] args) {
        List<MoveDirection> newList = new ArrayList<>();

        for(String arg: args) {
            MoveDirection value = switch(arg) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACK;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };

            if (value != null) {
                newList.add(value);
            }
        }

        return newList;
    }
}
