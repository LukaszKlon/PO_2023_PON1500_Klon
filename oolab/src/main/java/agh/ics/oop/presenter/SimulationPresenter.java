package agh.ics.oop.presenter;

import agh.ics.oop.OptionParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;


import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    public Label moveDescription;
    @FXML
    private TextField textField;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Spinner<Integer> firstPositionX;
    @FXML
    private Spinner<Integer> firstPositionY;
    @FXML
    private Spinner<Integer> secondPositionX;
    @FXML
    private Spinner<Integer> secondPositionY;
    private WorldMap<WorldElement<Vector2d>,Vector2d> worldMap;
    static final int CELL_WIDTH = 30;
    static final int CELL_HEIGHT = 30;
    public void setWorldMap(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap) {
        this.worldMap = worldMap;
        mapGrid.getChildren().add(gridCreator());
    }

    private GridPane gridCreator(){
        Boundary boundary = worldMap.getCurrentBoundary();
        int numberColumns = boundary.rightTop().getX() - boundary.leftBottom().getX()+1;
        int numberRows = boundary.rightTop().getY() - boundary.leftBottom().getY()+1;
        GridPane gridPane = new GridPane();
        Label label;
        int cordX,cordY;
        gridPane.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        gridPane.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        for (int i = 0; i < numberRows; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            label = new Label((boundary.leftBottom().getY()+i)+"");
            gridPane.add(label,0,numberRows-i);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = 0; i < numberColumns; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            label = new Label((boundary.leftBottom().getX()+i+""));
            gridPane.add(label,1+i,0);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        label =new Label("y\\x");
        gridPane.add(label,0,0);
        GridPane.setHalignment(label, HPos.CENTER);
        for (int i = 0; i < numberRows; i++) {
            for (int j = 0; j < numberColumns; j++) {
                cordY = boundary.leftBottom().getY()+i;
                cordX = boundary.leftBottom().getX()+j;
                WorldElement<Vector2d> element = worldMap.objectAt(new Vector2d(cordX,cordY));
                if (element != null){
                    label = new Label(element.toString());
                    gridPane.add(label,j+1,numberRows-i);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
                else{
                    label = new Label(" ");
                    gridPane.add(label,j+1,numberRows-i);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public void drawMap(){
        clearGrid();
        mapGrid.getChildren().add(gridCreator());
    }

    public void onSimulationStartClicked(){
        String[] args = textField.getText().split(" ");
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        List<MoveDirection> directions;
        try {
            directions = OptionParser.parser(args);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getStackTrace());
            return;
        }
        Simulation simulation = new Simulation(directions,positions,worldMap);
        this.mapChanged(worldMap,"Add Animals");
        SimulationEngine simulationEngine = new SimulationEngine(simulation);
        try{
            simulationEngine.runAsync();
        }
        catch (InterruptedException e){
            System.out.println(e.getStackTrace());
        }

    }

    public void simulationStart(){

    }

    @Override
    public void mapChanged(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });
    }

}
