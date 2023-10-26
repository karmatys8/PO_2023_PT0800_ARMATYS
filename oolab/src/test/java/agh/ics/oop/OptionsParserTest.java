package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import agh.ics.oop.model.MoveDirection;

public class OptionsParserTest {
    @Test
    public void testReadInstructions() {
        String[] input1 = "f r b l".split(" ");
        MoveDirection[] result1 = {MoveDirection.FORWARD, MoveDirection.RIGHT,
                    MoveDirection.BACK, MoveDirection.LEFT};

        Assertions.assertArrayEquals(
                OptionsParser.readInstructions(input1)
                , result1);


        String[] input2 = "l     l l r  l ".split(" ");
        MoveDirection[] result2 = {MoveDirection.LEFT, MoveDirection.LEFT,
                MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.LEFT};

        Assertions.assertArrayEquals(
                OptionsParser.readInstructions(input2)
                , result2);


        String[] input3 = "f bb g i _l 4 b 007".split(" ");
        MoveDirection[] result3 = {MoveDirection.FORWARD, MoveDirection.BACK};

        Assertions.assertArrayEquals(
                OptionsParser.readInstructions(input3)
                , result3);


        String[] input4 = "9 16 q w e rty 25 1 4".split(" ");
        MoveDirection[] result4 = {};

        Assertions.assertArrayEquals(
                OptionsParser.readInstructions(input4)
                , result4);


        String[] input5 = "".split(" ");
        MoveDirection[] result5 = {};

        Assertions.assertArrayEquals(
                OptionsParser.readInstructions(input5)
                , result5);
    }
}
