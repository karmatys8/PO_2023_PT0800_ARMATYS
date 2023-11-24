package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.Vector2d;


public class World {
    public static void main(String[] args) {
        GrassField grassField = new GrassField(10);

        System.out.println(grassField);


        for (int i = 0; i < 10; i++) {
            grassField.place(new Animal(new Vector2d(i, 0)));
        }

        System.out.println(grassField);
        grassField.getElements();
    }
}
