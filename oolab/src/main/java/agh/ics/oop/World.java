package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.List;


public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> moves = OptionsParser.parse(args);

//            List<Vector2d> animals1 = List.of(new Vector2d(2,2), new Vector2d(3,4));
//            WorldMap<Animal, Vector2d> worldMap1 = new RectangularMap(5, 5);
//            Simulation simulation1 = new Simulation(animals1, moves, worldMap1);
//
//            List<Vector2d> animals2 = List.of(new Vector2d(-8,1), new Vector2d(3,14), new Vector2d(0, 0));
//            WorldMap<Animal, Vector2d> worldMap2 = new GrassField(15);
//            Simulation simulation2 = new Simulation(animals2, moves, worldMap2);
//
//            SimulationEngine engine = new SimulationEngine(List.of(simulation2, simulation1));
//            engine.runAsync();

            ConsoleMapDisplay observer = new ConsoleMapDisplay();

            List<Simulation> simulations = new ArrayList<>();
            for(int i = 0; i < 1000; i++) {
                List<Vector2d> animals = List.of(new Vector2d(-8,1), new Vector2d(3,14), new Vector2d(0, 0));
                WorldMap<Animal, Vector2d> worldMap = new GrassField(15);
                worldMap.addObserver(observer);
                simulations.add(new Simulation(animals, moves, worldMap));
            }

            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsyncInThreadPool(); // pula wątków jest lepszym pomysłem ponieważ korzystamy z 4 rdzeni (jeżeli są dostępne)
                                           // więc program będzie się wykonywać około 4 razy szybciej
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("System zakończył działanie");
    }
}
