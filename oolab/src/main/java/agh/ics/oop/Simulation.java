package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>(); /* not changing implementation because most likely number of
                                                               we will most likely access indexes more often than adding new values */
    private final List<MoveDirection> moves;
    private final WorldMap<Animal, Vector2d> worldMap;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves, WorldMap<Animal, Vector2d> worldMap) {
        for (Vector2d position : positions) {
            Animal newAnimal = new Animal(position);
            try {
                worldMap.place(newAnimal);
                animals.add(newAnimal);
            } catch (PositionAlreadyOccupiedException e) {
                if (! worldMap.isOccupied(position)) { // that means that exception occurred on user input
                    System.err.println("Illegal move specification: " + e.getMessage());
                    System.exit(1);
                }
            }
        }

        this.moves = moves;
        this.worldMap = worldMap;
    }

    public void run() {
        for (int i = 0; i < moves.size(); i++) {
            worldMap.move(animals.get(i % animals.size()), moves.get(i));
            System.out.println(worldMap);
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
}
