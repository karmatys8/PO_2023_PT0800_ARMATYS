package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

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
            if (worldMap.place(newAnimal))
            {
                animals.add(newAnimal);
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
