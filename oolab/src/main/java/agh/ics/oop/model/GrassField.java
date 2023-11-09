package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.lang.Math;
import java.util.*;

public class GrassField extends AbstractWorldMap{

    private final Map<Vector2d,Grass> grasses = new HashMap<>();

    public GrassField(int countGrass) {
        super(new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE));
        int parameter = (int) Math.sqrt(countGrass*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(parameter, parameter, countGrass);
        for (Vector2d temporaryVector : randomPositionGenerator) {
            place(new Grass(temporaryVector), temporaryVector);
        }
    }

    @Override
    public boolean place(WorldElement<Vector2d> object, Vector2d position) {
        if (object instanceof Grass grass){
            grasses.put(position,grass);
            return true;
        }
        return super.place(object,position);
    }

    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || isOccupiedGrass(position) ;
    }
    private boolean isOccupiedGrass(Vector2d position){
        return grasses.containsKey(position);
    }

    @Override
    public WorldElement<Vector2d> objectAt(Vector2d position) {
        Animal animal = (Animal) super.objectAt(position);
        if (animal != null){
            return animal;
        }
        if (isOccupiedGrass(position)){
            return grasses.get(position);
        }
        return null;
    }


    private void findCorners(ExtremeCoordinates coordinates,Iterator<Vector2d> iterator){
        while (iterator.hasNext()) {
            Vector2d temporaryVector = iterator.next();
            int coordinateXTemporary = temporaryVector.getX();
            int coordinateYTemporary = temporaryVector.getY();
            if (coordinateXTemporary < coordinates.getLeft()){
                coordinates.setLeft(coordinateXTemporary);
            }
            else if ((coordinateXTemporary > coordinates.getRight())) {
                coordinates.setRight(coordinateXTemporary);
            }
            if (coordinateYTemporary < coordinates.getBottom()){
                coordinates.setBottom(coordinateYTemporary);
            }
            else if ((coordinateYTemporary > coordinates.getTop())) {
                coordinates.setTop(coordinateYTemporary);
            }
        }
    }

    @Override
    public String toString() {
        MapVisualizer currentMap = new MapVisualizer(this);
        Iterator<Vector2d> iteratorAnimals = animals.keySet().iterator();
        Iterator<Vector2d> iteratorGrasses = grasses.keySet().iterator();
        ExtremeCoordinates extremeCoordinates = new ExtremeCoordinates(0,0,0,0);
        boolean flag = true;
        if (iteratorAnimals.hasNext()){
            flag = false;
            Vector2d temporaryVector = iteratorAnimals.next();
            extremeCoordinates = new ExtremeCoordinates(temporaryVector.getX(), temporaryVector.getX(), temporaryVector.getY(), temporaryVector.getY());
            findCorners(extremeCoordinates,iteratorAnimals);
        }
        if (flag){
            Vector2d temporaryVector = iteratorGrasses.next();
            extremeCoordinates = new ExtremeCoordinates(temporaryVector.getX(), temporaryVector.getX(), temporaryVector.getY(), temporaryVector.getY());
        }
        findCorners(extremeCoordinates,iteratorGrasses);
        Vector2d leftBottomCornerTemporary = new Vector2d(extremeCoordinates.getLeft(),extremeCoordinates.getBottom());
        Vector2d rightTopCornerTemporary = new Vector2d(extremeCoordinates.getRight(), extremeCoordinates.getTop());
        return currentMap.draw(leftBottomCornerTemporary,rightTopCornerTemporary);
    }

    @Override
    public List<WorldElement<Vector2d>> getElements() {
        List<WorldElement<Vector2d>> allElements = super.getElements();
        for (Vector2d vector: grasses.keySet()) {
            allElements.add(grasses.get(vector));
        }
        return allElements;
    }

    public Iterator<Vector2d>getGrassesKeys(){
        return grasses.keySet().iterator();
    }

}
