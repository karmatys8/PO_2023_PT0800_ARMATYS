package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>(); /* not changing implementation because most likely number of
                                                               we will most likely access indexes more often than adding new values */
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        for (Vector2d position : positions) {
            animals.add(new Animal(position));
        }

        this.moves = moves;
    }

    public void run() {
        for (int i = 0; i < moves.size(); i++) {
            animals.get(i % animals.size()).move(moves.get(i));
            System.out.println("Zwierzę " + i % animals.size() + " : " + animals.get(i % animals.size()));
        }
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }
}
