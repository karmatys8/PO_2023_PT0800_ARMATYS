package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    static final int CELL_WIDTH = 25;
    static final int CELL_HEIGHT = 25;
    private WorldMap<Animal, Vector2d> worldMap;
    Stage stage = new Stage();
    
    @FXML
    private Button start;
    @FXML
    private Label moveLabel;
    @FXML
    private TextField textField;
    @FXML
    private GridPane mapGrid;

    public void setWorldMap(WorldMap<Animal, Vector2d> map) {
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


            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    addLabel(flattenedMap.charAt(i + j * width) + "", i + 1, j + 1);
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

    @FXML
    private void onSimulationStartClicked() throws IOException {
        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        //BorderPane viewRoot = loader.load();
        //SimulationPresenter presenter = loader.getController();


        //configureStage(stage, viewRoot);
        //stage.show();

        List<MoveDirection> moves = OptionsParser.parse(textField.getText().split(" "));

        List<Vector2d> animals = List.of(new Vector2d(-8,1), new Vector2d(0, 0));
        WorldMap<Animal, Vector2d> worldMap = new GrassField(15);

        worldMap.addObserver(this);
        setWorldMap(worldMap);

        Simulation simulation = new Simulation(animals, moves, worldMap);

        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runAsync();
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
