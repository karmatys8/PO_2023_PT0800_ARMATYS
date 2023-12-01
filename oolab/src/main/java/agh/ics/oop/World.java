package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.util.PositionAlreadyOccupiedException;


public class World {
    public static void main(String[] args) {
        GrassField grassField = new GrassField(10);
        ConsoleMapDisplay listener = new ConsoleMapDisplay();
        grassField.addObserver(listener);


        for (int i = 0; i < 10; i++) {
            try {
                grassField.place(new Animal(new Vector2d(i, 0)));
            } catch (PositionAlreadyOccupiedException e) {
                i--;
            }
        }
    }
}
