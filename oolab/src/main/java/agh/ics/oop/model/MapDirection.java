package agh.ics.oop.model;

public enum MapDirection {
    NORTH("^"),
    EAST(">"),
    SOUTH("v"),
    WEST("<");

    private final String value;
    private static final MapDirection[] vals = values();

    MapDirection(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public MapDirection next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public MapDirection previous() {
        return vals[(this.ordinal() - 1 + vals.length) % vals.length];
    }

    public Vector2d toUnitVector() {
        return switch(this) {
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
        };
    }
}
