package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
public class RectangularMap implements WorldMap<Animal,Vector2d>{

    Map<Vector2d,Animal> animals = new HashMap<>();
    static final Vector2d LEFT_BOTTOM_MAP_CORNER = new Vector2d(0,0);
    private final Vector2d rightTopMapCorner;

    public RectangularMap(int width,int length){
        rightTopMapCorner = new Vector2d(width-1,length-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(rightTopMapCorner) && position.follows(LEFT_BOTTOM_MAP_CORNER) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getCordinats();
        if (canMoveTo(position)) {
            animals.put(position,animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d position = animal.getCordinats();
        animals.remove(position);
        animal.move(direction,this);
        animals.put(animal.getCordinats(),animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        if (isOccupied(position)){
            return animals.get(position);
        }
        return null;
    }


    @Override
    public String toString() {
        MapVisualizer currentMap = new MapVisualizer(this);
        return currentMap.draw(LEFT_BOTTOM_MAP_CORNER,rightTopMapCorner);
    }
}
