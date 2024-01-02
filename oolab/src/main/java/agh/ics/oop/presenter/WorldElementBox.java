package agh.ics.oop.presenter;

import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javafx.scene.image.Image;


public class WorldElementBox {

    public ImageView createImage(String fileName){
        Image image = new Image(fileName);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        return imageView;
    }
}
