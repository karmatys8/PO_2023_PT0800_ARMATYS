package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.List;


public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> moves = OptionsParser.parse(args);

            List<Vector2d> animals1 = List.of(new Vector2d(2,2), new Vector2d(3,4));
            WorldMap<Animal, Vector2d> worldMap1 = new RectangularMap(5, 5);
            Simulation simulation1 = new Simulation(animals1, moves, worldMap1);

            List<Vector2d> animals2 = List.of(new Vector2d(-8,1), new Vector2d(3,14), new Vector2d(0, 0));
            WorldMap<Animal, Vector2d> worldMap2 = new GrassField(15);
            Simulation simulation2 = new Simulation(animals2, moves, worldMap2);

            SimulationEngine engine = new SimulationEngine(List.of(simulation2, simulation1));
            engine.runSync();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
