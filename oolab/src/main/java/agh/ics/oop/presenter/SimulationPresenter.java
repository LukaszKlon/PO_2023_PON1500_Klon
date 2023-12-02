package agh.ics.oop.presenter;

import agh.ics.oop.OptionParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    public Label moveDescription;
    @FXML
    private Label infoLabel;
    @FXML
    private TextField textField;
    WorldMap<WorldElement<Vector2d>,Vector2d> worldMap;

    public void setWorldMap(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap) {
        this.worldMap = worldMap;
    }

    public void drawMap(String message){
        infoLabel.setText(worldMap.toString());
        moveDescription.setText(message);
    }

    public void onSimulationStartClicked(){
        String[] args = textField.getText().split(" ");
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        List<MoveDirection> directions = OptionParser.parser(args);
        Simulation simulation = new Simulation(directions,positions,worldMap);
//        grassField.registerObserver(consoleMapDisplay);
//        simulation.run();
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
        try{
            simulationEngine.runAsync();
        }
        catch (InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public void mapChanged(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap, String message) {
        Platform.runLater(() -> {
            drawMap(message);
        });
    }

}
