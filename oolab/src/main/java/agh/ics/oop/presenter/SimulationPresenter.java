package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap<Animal, Vector2d> worldMap;
    Stage stage = new Stage();

    @FXML
    private Label infoLabel;
    @FXML
    private Button start;
    @FXML
    private Label moveLabel;
    @FXML
    private TextField textField;

    public void setWorldMap(WorldMap<Animal, Vector2d> map) {
        worldMap = map;
    }

    public void drawMap() {
        if (worldMap != null) {
            infoLabel.setText(worldMap.toString());
        }
    }

    @Override
    public void mapChanged(WorldMap<Animal, Vector2d> worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            // ewentualny inny kod zmieniający kontrolki
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
