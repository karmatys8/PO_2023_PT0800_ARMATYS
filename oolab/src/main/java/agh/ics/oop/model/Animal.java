package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    private static final Vector2d lowerLeftBoundary = new Vector2d(0, 0);
    private static final Vector2d upperRightBoundary = new Vector2d(4, 4);


    public Animal(Vector2d position) {
        this.position = position;
    }

    public Animal() {
        this(new Vector2d(2, 2));
    }

    public String toString() {
        return position.toString() + ", " + orientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    private void validateMove(Vector2d newPosition) {
        if (newPosition.precedes(upperRightBoundary) && newPosition.follows(lowerLeftBoundary)) {
            position = newPosition;
        }
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> this.validateMove(position.add(orientation.toUnitVector()));
            case BACK -> this.validateMove(position.subtract(orientation.toUnitVector()));
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return orientation == animal.orientation && Objects.equals(position, animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }
}
