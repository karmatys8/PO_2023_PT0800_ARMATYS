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

        String[] input2 = "l     l l r  l ".split(" ");
        MoveDirection[] res2 = {MoveDirection.LEFT, MoveDirection.LEFT,
                MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.LEFT};
        List<MoveDirection> result2 = Arrays.asList(res2);

        Assertions.assertEquals(result2, OptionsParser.parse(input2));


        String[] input3 = "f bb g i _l 4 b 007".split(" ");
        MoveDirection[] res3 = {MoveDirection.FORWARD, MoveDirection.BACK};
        List<MoveDirection> result3 = Arrays.asList(res3);

        Assertions.assertEquals(result3, OptionsParser.parse(input3));


        String[] input4 = "9 16 q w e rty 25 1 4".split(" ");
        List<MoveDirection> result4 = new ArrayList<>();

        Assertions.assertEquals(result4, OptionsParser.parse(input4));


        String[] input5 = "".split(" ");
        List<MoveDirection> result5 = new ArrayList<>();

        Assertions.assertEquals(result5, OptionsParser.parse(input5));
    }
}
