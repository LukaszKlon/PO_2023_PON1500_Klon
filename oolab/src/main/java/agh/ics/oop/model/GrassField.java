package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.lang.Math;
import java.util.*;

public class GrassField implements WorldMap<WorldElement<Vector2d>,Vector2d>{

    private final int countGrass;
    private final Vector2d LEFT_BOTTOM_MAP_CORNER = new Vector2d(0,0);
    private final Vector2d RIGHT_TOP_MAP_CORNER = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
    private final Map<Vector2d,Grass> grasses = new HashMap<>();

    private final Map<Vector2d,Animal> animals = new HashMap<>();


    public GrassField(int countGrass) {
        this.countGrass = countGrass;
        int parameter = (int) Math.sqrt(countGrass*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(parameter, parameter, countGrass);
        for (Vector2d temporaryVector : randomPositionGenerator) {
            place(new Grass(temporaryVector), temporaryVector);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(RIGHT_TOP_MAP_CORNER) && position.follows(LEFT_BOTTOM_MAP_CORNER) && !isOccupiedAnimal(position);
    }

    @Override
    public boolean place(WorldElement<Vector2d> object, Vector2d position) {
        if (object instanceof Animal animal){
            if (canMoveTo(position)) {
                animals.put(position,animal);
                return true;
            }
            return false;
        }
        if (object instanceof Grass grass){
            grasses.put(position,grass);
            return true;
        }
        return false;
    }

    @Override
    public void move(WorldElement<Vector2d> object, Vector2d position, MoveDirection direction) {
        if (object instanceof Animal animal){
            if ( animal == objectAt(position)){
                animals.remove(position);
                animal.move(direction,this);
                animals.put(animal.getPosition(),animal);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return isOccupiedAnimal(position) || isOccupiedGrass(position);
    }

    private boolean isOccupiedAnimal(Vector2d position){
        return animals.containsKey(position);
    }
    private boolean isOccupiedGrass(Vector2d position){
        return grasses.containsKey(position);
    }

    @Override
    public WorldElement<Vector2d> objectAt(Vector2d position) {
        if (isOccupiedAnimal(position)){
            return animals.get(position);
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
//        return currentMap.draw(new Vector2d(0,0),new Vector2d(10,10));
    }

    public Iterator<Vector2d>getGrassesKeys(){
        return grasses.keySet().iterator();
    }

}
