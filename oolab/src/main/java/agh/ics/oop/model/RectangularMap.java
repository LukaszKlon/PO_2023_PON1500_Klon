package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width,int length){
        super(new Vector2d(width-1,length-1));
    }

}
