package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SimulationTest {

    public void doTests(String[] args, List<Vector2d> positions, Vector2d[] result, WorldMap<Animal, Vector2d> worldMap) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        Simulation simulation = new Simulation(positions, directions, worldMap);
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
        Vector2d[] result1 = {new Vector2d(2,0), new Vector2d(3, 4)};
        WorldMap<Animal, Vector2d> worldMap1 = new RectangularMap(5, 5);

        doTests(args1, positions1, result1, worldMap1);


        String[] args2 = "b b r l l f r b l f f b l".split(" ");
        List<Vector2d> positions2 = List.of(new Vector2d(2,2), new Vector2d(3,4), new Vector2d(0, 0));
        Vector2d[] result2 = {new Vector2d(2,2), new Vector2d(2, 3), new Vector2d(1, 0)};
        WorldMap<Animal, Vector2d> worldMap2 = new RectangularMap(4, 7);

        doTests(args2, positions2, result2, worldMap2);


        String[] args3 = "f f f l b f".split(" ");
        List<Vector2d> positions3 = List.of(new Vector2d(1,2), new Vector2d(1,4), new Vector2d(0, 0),
                new Vector2d(1, 0), new Vector2d (0, 2), new Vector2d(0, 3), new Vector2d(0, 4),
                new Vector2d(1, 1));
        Vector2d[] result3 = {new Vector2d(1, 3), new Vector2d(1, 5), new Vector2d(0, 1),
                new Vector2d(1, 0), new Vector2d(0, 2), new Vector2d(0, 3), new Vector2d(0, 4),
                new Vector2d(1, 1)};
        WorldMap<Animal, Vector2d> worldMap3 = new RectangularMap(2, 7);

        doTests(args3, positions3, result3, worldMap3);


        String[] args4 = "".split(" ");
        List<Vector2d> positions4 = List.of(new Vector2d(1,3), new Vector2d(2,4));
        Vector2d[] result4 = {new Vector2d(1,3), new Vector2d(2, 4)};
        WorldMap<Animal, Vector2d> worldMap4 = new RectangularMap(5, 5);

        doTests(args4, positions4, result4, worldMap4);


        String[] args5 = "f l b b b f r".split(" ");
        List<Vector2d> positions5 = List.of(new Vector2d(0,0));
        Vector2d[] result5 = {new Vector2d(0,0)};
        WorldMap<Animal, Vector2d> worldMap5 = new RectangularMap(1, 1);

        doTests(args5, positions5, result5, worldMap5);

// commented for now because idk if I should keep this test and if so how am I supposed to prevent exit(1)?
//        String[] args6 = "f f r b l f f".split(" ");
//        List<Vector2d> positions6 = List.of(new Vector2d(0,0), new Vector2d(5, 0), new Vector2d(6, 9),
//                new Vector2d(-6, 7), new Vector2d(1, 1), new Vector2d(1, 1), new Vector2d(0, 2));
//        Vector2d[] result6 = {new Vector2d(0,1), new Vector2d(1, 2), new Vector2d(0, 2)};
//        WorldMap<Animal, Vector2d> worldMap6 = new RectangularMap(2, 3);
//
//        doTests(args6, positions6, result6, worldMap6);
    }
}
