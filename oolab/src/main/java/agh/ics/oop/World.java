package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;


public class World {
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
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
