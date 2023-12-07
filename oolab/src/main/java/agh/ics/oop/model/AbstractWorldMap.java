package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;
import agh.ics.oop.PositionAlreadyOccupiedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap<WorldElement<Vector2d>,Vector2d>,MapChangeListener{

    protected Map<Vector2d,WorldElement<Vector2d>> animals = new HashMap<>();
    protected final Vector2d leftBottomMapCorner;
    protected final Vector2d rightTopMapCorner;
    protected final List<MapChangeListener> observersList = new ArrayList<>();
    private final int Id;

    public AbstractWorldMap(Vector2d rightTopMapCorner,int Id) {
        this.leftBottomMapCorner = new Vector2d(0,0);
        this.rightTopMapCorner = rightTopMapCorner;
        this.Id = Id;
    }

    public boolean canMoveTo(Vector2d position) {
        return position.precedes(rightTopMapCorner) && position.follows(leftBottomMapCorner) && !isOccupiedAnimal(position);
    }

    public void place(WorldElement<Vector2d> object) throws PositionAlreadyOccupiedException {
        Vector2d position = object.getPosition();
        if (object.movable()){
            if (isOccupiedAnimal(position)){
                throw new PositionAlreadyOccupiedException(position);
            }
            else if (canMoveTo(position)) {
                animals.put(position,object);
            }
        }
    }

    public void move(WorldElement<Vector2d> object, MoveDirection direction) {
        Vector2d position = object.getPosition();
        if (object.movable()){
            animals.remove(position);
            object.move(direction,this);
            animals.put(object.getPosition(),object);
            if (!position.equals(object.getPosition())) {
                mapChanged("Animal change position");
            }
            else if (MoveDirection.RIGHT == direction || MoveDirection.LEFT == direction){
                mapChanged("Animal change direction");
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

    abstract public Boundary getCurrentBoundary();

    public String toString() {
        MapVisualizer currentMap = new MapVisualizer(this);
        Boundary Corners = getCurrentBoundary();
        return currentMap.draw(Corners.leftBottom(),Corners.rightTop());
    }

    @Override
    public void mapChanged(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap, String message) {
        for (MapChangeListener observer : observersList) {
            observer.mapChanged(this, message);
        }
    }

    public void mapChanged(String message) {
        mapChanged(this,message);
    }

    public void registerObserver(MapChangeListener mapChangeListener){
        observersList.add(mapChangeListener);
    }

    public void unregisterObserver(MapChangeListener mapChangeListener){
        observersList.remove(mapChangeListener);
    }

    public int getId(){
        return Id;
    }
}
