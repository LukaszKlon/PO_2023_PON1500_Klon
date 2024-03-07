package agh.ics.oop.presenter;

import agh.ics.oop.model.Vector2d;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javafx.scene.image.Image;


public class WorldElementBox {

    public WorldElementBox() {};
    public VBox createVbox(String fileName, Vector2d position){
        Label label = new Label(position.toString());
        ImageView imageView = createImage(fileName);
        VBox vBox = new VBox(imageView,label);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    private ImageView createImage(String fileName){
        Image image = new Image(fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        return imageView;
    }
}
