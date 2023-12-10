package agh.ics.oop.presenter;

import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.Vector2d;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class StartPresenter {
    @FXML
    private Spinner<Integer> firstPositionX;
    @FXML
    private Spinner<Integer> firstPositionY;
    @FXML
    private Spinner<Integer> secondPositionX;
    @FXML
    private Spinner<Integer> secondPositionY;
    private int simulationCount = 1;

    public void simulationStart(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot;
        Scene scene;
        try {
            viewRoot = loader.load();
            scene = new Scene(viewRoot);
        }
        catch (IOException e){
            System.out.println("Couldn't load file");
            return;
        }
        SimulationPresenter presenter = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Simulation "+ simulationCount);
        stage.minWidthProperty().bind(viewRoot.minWidthProperty());
        stage.minHeightProperty().bind(viewRoot.minHeightProperty());
        GrassField grassField = new GrassField(10,simulationCount);
        simulationCount ++;
        presenter.setWorldMap(grassField);
        presenter.setPositions(List.of(new Vector2d(firstPositionX.getValue(),firstPositionY.getValue()), new Vector2d(secondPositionX.getValue(),secondPositionY.getValue())));
        grassField.registerObserver(presenter);
        stage.show();
    }
}
