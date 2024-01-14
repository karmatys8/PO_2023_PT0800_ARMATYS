package agh.ics.oop.presenter;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SimulationPresenter implements MapChangeListener {

    static final int CELL_WIDTH = 50;
    static final int CELL_HEIGHT = 50;
    private WorldMap<Animal, Vector2d> worldMap;
    
    @FXML
    private Label moveLabel;
    @FXML
    private GridPane mapGrid;

    public void setWorldMap(WorldMap<Animal, Vector2d> map) {
        if (worldMap != null) {
            worldMap.removeObserver(this);
        }

        map.addObserver(this);
        map.addObserver((worldMap, message) -> Platform.runLater(() -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now) + " " + message);
        }));
        map.addObserver(new FileMapDisplay(map.getId()));

        worldMap = map;
    }

    private void addLabel(String text, int col, int row) {
        Label label = new Label(text);
        GridPane.setHalignment(label, HPos.CENTER);
        GridPane.setValignment(label, VPos.CENTER);
        mapGrid.add(label, col, row);
    }

    public void drawMap() {
        if (worldMap != null) {
            clearGrid();

            String flattenedMap = worldMap.toString();
            Boundary boundary = worldMap.getCurrentBounds();
            int width = boundary.upperRight().x() - boundary.lowerLeft().x() +1;
            int height = boundary.upperRight().y() - boundary.lowerLeft().y() +1;

            addLabel("y/x", 0, 0);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

            for (int i = 0; i < width; i++) {
                addLabel((boundary.lowerLeft().x() + i) + "", i + 1, 0);
                mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            }

            for (int j = 0; j < height; j++) {
                addLabel((boundary.upperRight().y() - j) + "", 0, j + 1);
                mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            }

            int lowerLeftX = boundary.lowerLeft().x();
            int upperRightY = boundary.upperRight().y();
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Optional<WorldElement> optionalElement = worldMap.objectAt(new Vector2d(lowerLeftX + i, upperRightY - j));
//                    String labelText = optionalElement.map(Object::toString).orElse(" ");
//                    addLabel(labelText, i + 1, j + 1);
                    if (optionalElement.isPresent()) {
                        mapGrid.add(new WorldElementBox(optionalElement.get()), i + 1, j + 1);
                    } else {
                        addLabel(" ", i + 1, j + 1);
                    }
                }
            }
        }
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    @Override
    public void mapChanged(WorldMap<Animal, Vector2d> worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            
            moveLabel.setText(message);
        });
    }
}
