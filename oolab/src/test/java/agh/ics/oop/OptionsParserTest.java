package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionsParserTest {
    @Test
    public void testReadInstructions() {
        String[] input1 = "f r b l".split(" ");
        MoveDirection[] res1 = {MoveDirection.FORWARD, MoveDirection.RIGHT,
                    MoveDirection.BACK, MoveDirection.LEFT};
        List<MoveDirection> result1 = Arrays.asList(res1);

        Assertions.assertEquals(result1, OptionsParser.parse(input1));

        String[] input2 = "l l l r l".split(" ");
        MoveDirection[] res2 = {MoveDirection.LEFT, MoveDirection.LEFT,
                MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.LEFT};
        List<MoveDirection> result2 = Arrays.asList(res2);

        Assertions.assertEquals(result2, OptionsParser.parse(input2));


        String[] input3 = "f bb g i _l 4 b 007".split(" ");

        Assertions.assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(input3));


        String[] input4 = "l    l  l r  l".split(" ");

        Assertions.assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(input4));


        String[] input5 = "".split(" ");

        Assertions.assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(input5));
    }
}
