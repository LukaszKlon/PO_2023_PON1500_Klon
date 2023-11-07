package agh.ics.oop.model;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap<WorldElement<Vector2d>,Vector2d>{

    protected Map<Vector2d,Animal> animals = new HashMap<>();
    private final Vector2d leftBottomMapCorner;
    private final Vector2d rightTopMapCorner;

    public AbstractWorldMap(Vector2d leftBottomMapCorner, Vector2d rightTopMapCorner) {
        this.leftBottomMapCorner = leftBottomMapCorner;
        this.rightTopMapCorner = rightTopMapCorner;
    }

    public boolean canMoveTo(Vector2d position) {
        return position.precedes(rightTopMapCorner) && position.follows(leftBottomMapCorner) && !isOccupied(position);
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




}
