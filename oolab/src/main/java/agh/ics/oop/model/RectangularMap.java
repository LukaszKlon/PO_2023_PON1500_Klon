package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width,int length,int Id){
        super(new Vector2d(width-1,length-1),Id);
    }

    @Override
    public Boundary getCurrentBoundary() {
        return new Boundary(leftBottomMapCorner,rightTopMapCorner);
    }
}
