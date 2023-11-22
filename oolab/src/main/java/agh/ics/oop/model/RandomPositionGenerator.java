package agh.ics.oop.model;


import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final List<Vector2d> vectors;
    private int grassLeft;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        vectors = new ArrayList<>(maxWidth * maxHeight);
        this.grassLeft = grassCount;

        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < maxHeight; j++) {
                vectors.add(new Vector2d(i, j));
            }
        }

        Collections.shuffle(vectors);
    }


    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<Vector2d>() {
            @Override
            public boolean hasNext() {
                return (grassLeft != 0) && (! vectors.isEmpty());
            }

            @Override
            public Vector2d next() {
                if (! hasNext()) {
                    throw new NoSuchElementException();
                }
                grassLeft--;
                return vectors.remove(0);
            }
        };
    }
}
