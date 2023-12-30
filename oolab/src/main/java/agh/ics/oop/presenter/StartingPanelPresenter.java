package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StartingPanelPresenter {

    @FXML
    private Button start;
    @FXML
    private TextField textField;

    private final List<List<MoveDirection>> movesList = new ArrayList<>();
    private final List<SimulationPresenter> presenters = new ArrayList<>();
    private static final List<Vector2d> animals = List.of(new Vector2d(-8,1), new Vector2d(0, 0));


    @FXML
    private void startSimulations() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        presenters.add(loader.getController());


        Stage stage = new Stage();

        configureStage(stage, viewRoot);
        stage.show();


        List<MoveDirection> currMoves = OptionsParser.parse(textField.getText().split(" "));

        movesList.add(currMoves);


        List<Simulation> simulations = new ArrayList<>();

        for(int i = 0; i < presenters.size(); i++) {
            WorldMap<Animal, Vector2d> worldMap = new GrassField(15);

            presenters.get(i).setWorldMap(worldMap);
            simulations.add(new Simulation(animals, movesList.get(i), worldMap));
        }

        SimulationEngine simulationEngine = new SimulationEngine(simulations);
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
