package agh.ics.oop.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AnimalisticString {
    String str;

    MapDirection orientation;

    AnimalisticString(String str, MapDirection orientation) {
        this.str = str;
        this.orientation = orientation;
    }

    AnimalisticString(String str) {
        this(str, MapDirection.NORTH);
    }

    @Override
    public String toString() {
        return "%s(%s)".formatted(str, orientation);
    }
}

public class TextMap implements WorldMap<String, Integer> {
    private final List<AnimalisticString> strMap = new ArrayList<>();

    @Override
    public boolean canMoveTo(Integer position) {
        return 0 <= position && position < strMap.size();
    }

    @Override
    public boolean place(String animal) {
        strMap.add(new AnimalisticString(animal));
        return true;
    }


    private void moveString(AnimalisticString animalisticString, int idx, int modifier) {
        if (animalisticString.orientation == MapDirection.EAST && canMoveTo(idx + 1 * modifier)) {
            Collections.swap(strMap, idx, idx + 1 * modifier);
        } else if (animalisticString.orientation == MapDirection.WEST && canMoveTo(idx + -1 * modifier)) {
            Collections.swap(strMap, idx, idx + -1 * modifier);
        }
    }

    @Override
    public void move(String animal, MoveDirection direction) {
        int i = 0; // checking references on purpose
        AnimalisticString currStr = strMap.get(i);
        while (i < strMap.size() && (currStr.str != animal)) currStr = strMap.get(++i);

        switch (direction) {
            case FORWARD -> moveString(currStr, i, 1);
            case RIGHT -> currStr.orientation = currStr.orientation.next();
            case BACK -> moveString(currStr, i, -1);
            case LEFT -> currStr.orientation = currStr.orientation.previous();
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return true;
    }

    @Override
    public String objectAt(Integer position) {
        return strMap.get(position).str;
    }

    @Override
    public String toString() {
        String res = "";
        for (AnimalisticString animalisticString:strMap) {
            res += " " + animalisticString;
        }

        return res;
    }
}
