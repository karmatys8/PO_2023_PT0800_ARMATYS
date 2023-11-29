package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;


public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> newList = new ArrayList<>(); /* changed ArrayList to LinkedList because we add n elements to it
                                                        ,where n is List length, and access n values linearly */

        for(String arg: args) {
            try {
                MoveDirection value = switch(arg) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACK;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(arg + " is not legal move specification");
                };

                newList.add(value);
            } catch (IllegalArgumentException e) {
                System.err.println("Illegal move specification: " + e.getMessage());
            }
        }

        return newList;
    }
}
