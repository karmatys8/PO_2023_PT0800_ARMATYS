package agh.ics.oop;

import agh.ics.oop.model.*;
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
            if (! worldMap.isOccupied(position)) {
                try {
                    worldMap.place(newAnimal);
                    animals.add(newAnimal);
                } catch (PositionAlreadyOccupiedException ignored) {}
            }
        }

        this.moves = moves;
        this.worldMap = worldMap;
        this.worldMap.addObserver(new ConsoleMapDisplay());
    }

    public void run() {
        for (int i = 0; i < moves.size(); i++) {
            worldMap.move(animals.get(i % animals.size()), moves.get(i));
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
}
