package agh.ics.oop.model;


import agh.ics.oop.model.util.MapVisualizer;


public class RectangularMap extends AbstractWorldMap {

    private static final Vector2d lowerLeftBoundary = new Vector2d(0, 0);
    private final Vector2d upperRightBoundary;;

    public RectangularMap(int width, int height) {
        upperRightBoundary  = new Vector2d(width - 1, height - 1);
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) &&
                position.precedes(upperRightBoundary) && position.follows(lowerLeftBoundary);
    }

    @Override
    public String toString() {
        MapVisualizer currentMap = new MapVisualizer((WorldMap) this);
        return currentMap.draw(lowerLeftBoundary, upperRightBoundary);
    }
}
