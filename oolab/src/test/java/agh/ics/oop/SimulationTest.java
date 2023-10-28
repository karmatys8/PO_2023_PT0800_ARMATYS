package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SimulationTest {

    public void doTests(String[] args, List<Vector2d> positions, Vector2d[] result) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        for (int i = 0; i < result.length; i++) {
            Assertions.assertTrue(animals.get(i).isAt(result[i]));
        }
    }

    @Test
    public void testMove() {
        String[] args1 = "f b r l f f r r f f f f f f f f".split(" ");
        List<Vector2d> positions1 = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Vector2d[] result1 = {new Vector2d(3,0), new Vector2d(2, 4)};

        doTests(args1, positions1, result1);


        String[] args2 = "b b r l l f r b l f f b l".split(" ");
        List<Vector2d> positions2 = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0, 0));
        Vector2d[] result2 = {new Vector2d(2,2), new Vector2d(3, 3), new Vector2d(1, 0)};

        doTests(args2, positions2, result2);


        String[] args3 = "f f f l b".split(" ");
        List<Vector2d> positions3 = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0, 0),
                new Vector2d(1, 0), new Vector2d(3, 0), new Vector2d(3, 0), new Vector2d(4, 4));
        Vector2d[] result3 = {new Vector2d(2, 3), new Vector2d(3, 4), new Vector2d(0, 1),
                new Vector2d(1, 0), new Vector2d(3, 0), new Vector2d(3, 0), new Vector2d(4, 4)};

        doTests(args3, positions3, result3);


        String[] args4 = "".split(" ");
        List<Vector2d> positions4 = List.of(new Vector2d(1,3), new Vector2d(2,4));
        Vector2d[] result4 = {new Vector2d(1,3), new Vector2d(2, 4)};

        doTests(args4, positions4, result4);
    }
}
