package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap<WorldElement<Vector2d>,Vector2d>{

    protected Map<Vector2d,Animal> animals = new HashMap<>();
    private final Vector2d leftBottomMapCorner;
    private final Vector2d rightTopMapCorner;

    public AbstractWorldMap(Vector2d rightTopMapCorner) {
        this.leftBottomMapCorner = new Vector2d(0,0);
        this.rightTopMapCorner = rightTopMapCorner;
    }

    public boolean canMoveTo(Vector2d position) {
        return position.precedes(rightTopMapCorner) && position.follows(leftBottomMapCorner) && !isOccupiedAnimal(position);
    }

    public boolean place(WorldElement<Vector2d> object,Vector2d position) {
        if (object instanceof Animal animal){
            if (canMoveTo(position)) {
                animals.put(position,animal);
                return true;
            }
            return false;
        }
        return false;
    }

    public void move(WorldElement<Vector2d> object, Vector2d position, MoveDirection direction) {
        if (object instanceof Animal animal){
            if ( animal == objectAt(position)){
                animals.remove(position);
                animal.move(direction,this);
                animals.put(animal.getPosition(),animal);
            }
        }
    }

    public boolean isOccupied(Vector2d position) {
        return isOccupiedAnimal(position);
    }

    private boolean isOccupiedAnimal(Vector2d position){
        return animals.containsKey(position);
    }

    public WorldElement<Vector2d> objectAt(Vector2d position) {
        if (isOccupiedAnimal(position)) {
            return animals.get(position);
        }
        return null;
    }

    public List<WorldElement<Vector2d>> getElements(){
        List<WorldElement<Vector2d>> allElements = new ArrayList<>();
        for (Vector2d vector:animals.keySet()) {
            allElements.add(animals.get(vector));
        }
        return allElements;
    }

    public String toString() {
        MapVisualizer currentMap = new MapVisualizer(this);
        return currentMap.draw(leftBottomMapCorner,rightTopMapCorner);
    }




}
