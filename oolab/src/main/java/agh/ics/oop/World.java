package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.TextMap;


public class World {
    public static void main(String[] args) {
        TextMap textMap = new TextMap();
        String str1 = "Ala";
        String str2 = "ma";
        String str3 = "sowoniedźwiedzia";

        textMap.place(str1);
        textMap.place(str2);
        textMap.place(str3);

        System.out.println(textMap);

        textMap.move(str3, MoveDirection.RIGHT);
        textMap.move(str3, MoveDirection.FORWARD);

        System.out.println(textMap);

        textMap.move(str2, MoveDirection.LEFT);
        textMap.move(str2, MoveDirection.BACK);

        System.out.println(textMap);
    }
}
