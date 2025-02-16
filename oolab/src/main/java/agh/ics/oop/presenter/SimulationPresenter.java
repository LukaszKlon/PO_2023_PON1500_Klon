package agh.ics.oop.presenter;

import agh.ics.oop.OptionParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.List;
import java.util.Optional;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    public Label moveDescription;
    @FXML
    private TextField textField;
    @FXML
    private GridPane mapGrid;
    private WorldMap<WorldElement<Vector2d>,Vector2d> worldMap;
    private List<Vector2d> positions;
    static final int CELL_WIDTH = 40;
    static final int CELL_HEIGHT = 40;
    public void setWorldMap(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap) {
        this.worldMap = worldMap;
        mapGrid.getChildren().add(gridCreator());
    }

    public void setPositions(List<Vector2d> positions){
        this.positions = positions;
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
        String path;
        VBox vBox = new VBox();
        WorldElementBox worldElementBox = new WorldElementBox();
        label =new Label("y\\x");
        gridPane.add(label,0,0);
        GridPane.setHalignment(label, HPos.CENTER);
        for (int i = 0; i < numberRows; i++) {
            for (int j = 0; j < numberColumns; j++) {
                cordY = boundary.leftBottom().getY()+i;
                cordX = boundary.leftBottom().getX()+j;
                Optional<WorldElement<Vector2d>> element = worldMap.objectAt(new Vector2d(cordX,cordY));
                label = element.map(vector2dWorldElement -> new Label(vector2dWorldElement.toString())).orElseGet(() -> new Label(" "));
                if (element.isPresent()){
                    System.out.println(element.map(WorldElement::getResource).get());
                    vBox = worldElementBox.createVbox(element.map(WorldElement::getResource).get(),element.map(WorldElement::getPosition).get());
                }
                else{
                    vBox = new VBox();
                }
//                gridPane.add(label,j+1,numberRows-i);
                gridPane.add(vBox,j+1,numberRows-i);
                GridPane.setHalignment(vBox, HPos.CENTER);
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

    @Override
    public void mapChanged(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });
    }

}
