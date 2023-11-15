package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.lang.Math;
import java.util.*;

public class GrassField extends AbstractWorldMap{

    private final Map<Vector2d,WorldElement<Vector2d>> grasses = new HashMap<>();

    public GrassField(int countGrass) {
        super(new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE));
        int parameter = (int) Math.sqrt(countGrass*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(parameter, parameter, countGrass);
        for (Vector2d temporaryVector : randomPositionGenerator) {
            place(new Grass(temporaryVector));
        }
    }

    @Override
    public boolean place(WorldElement<Vector2d> object) {
        Vector2d position = object.getPosition();
        if (!object.movable()){
            grasses.put(position,object);
            return true;
        }
        return super.place(object);
    }

    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || isOccupiedGrass(position) ;
    }
    private boolean isOccupiedGrass(Vector2d position){
        return grasses.containsKey(position);
    }

    @Override
    public WorldElement<Vector2d> objectAt(Vector2d position) {
        WorldElement<Vector2d> animal =  super.objectAt(position);
        if (animal != null){
            return animal;
        }
        if (isOccupiedGrass(position)){
            return grasses.get(position);
        }
        return null;
    }

    @Override
    public List<WorldElement<Vector2d>> getElements() {
        List<WorldElement<Vector2d>> allElements = super.getElements();
        for (Vector2d vector: grasses.keySet()) {
            allElements.add(grasses.get(vector));
        }
        return allElements;
    }

    @Override
    public Boundary getCurrentBoundary() {
        List<WorldElement<Vector2d>> allElements = getElements();
        Vector2d rightTop = allElements.get(0).getPosition();
        Vector2d leftBottom = allElements.get(0).getPosition();
        Vector2d currentElementPosition;
        for (WorldElement<Vector2d> element:allElements) {
            currentElementPosition = element.getPosition();
            rightTop = rightTop.upperRight(currentElementPosition);
            leftBottom = leftBottom.lowerLeft(currentElementPosition);
        }
        return new Boundary(leftBottom,rightTop);
    }

    public Iterator<Vector2d>getGrassesKeys(){
        return grasses.keySet().iterator();
    }

}
