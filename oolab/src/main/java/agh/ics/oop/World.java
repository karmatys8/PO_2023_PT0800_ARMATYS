package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.Animal;

import java.util.List;


public class World {
    public static void main(String[] args) {

    }

    public static void run(MoveDirection[] args) {
        for(int i=0; i < args.length; i++) {
            if (i != 0) {
                System.out.println(",");
            }

            String message = switch(args[i]) {
                case FORWARD -> "zwierzak idzie do przodu";
                case BACK-> "zwierzak idzie do tyłu";
                case RIGHT -> "zwierzak skręca w prawo";
                case LEFT -> "zwierzak skręca w lewo";
            };

            System.out.print(message);
        }

        System.out.println(); // last line doesn't have '\n'
    }
}
