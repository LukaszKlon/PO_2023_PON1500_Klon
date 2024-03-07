package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;
import agh.ics.oop.PositionAlreadyOccupiedException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        FileMapDisplay fileMapDisplay = new FileMapDisplay(this);
        this.registerObserver(fileMapDisplay);
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
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            if (!position.equals(object.getPosition())) {
                mapChanged(now.format(formatter) + " Animal change position from " + position +" to "+ object.getPosition());
            }
            else if (MoveDirection.RIGHT == direction || MoveDirection.LEFT == direction){
                mapChanged(now.format(formatter) + " Animal on position " + position +" change direction");
            }
        }
    }

    public boolean isOccupied(Vector2d position) {
        return isOccupiedAnimal(position);
    }

    private boolean isOccupiedAnimal(Vector2d position){
        return animals.containsKey(position);
    }

    public Optional<WorldElement<Vector2d>> objectAt(Vector2d position) {
        if (isOccupiedAnimal(position)) {
            return Optional.ofNullable(animals.get(position));
        }
        return Optional.empty();
    }

    public List<WorldElement<Vector2d>> getElements(){
        return animals.keySet().stream()
                .map(animals::get)
                .collect(Collectors.toList());
    }

    abstract public Boundary getCurrentBoundary();

    public String toString() {
        MapVisualizer currentMap = new MapVisualizer(this);
        Boundary Corners = getCurrentBoundary();
        return currentMap.draw(Corners.leftBottom(),Corners.rightTop());
    }

    @Override
    public void mapChanged(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap, String message) {
        observersList.forEach(observer -> observer.mapChanged(this, message));
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

    public List<WorldElement<Vector2d>> getOrderedAnimals(){
        List<Vector2d> vectorList = new ArrayList<>(animals.keySet());

        Comparator<Vector2d> vectorComparator = Comparator
                .comparing(Vector2d::getX)
                .thenComparing(Vector2d::getY);

        vectorList.sort(vectorComparator);

        return vectorList.stream()
                .sorted(vectorComparator)
                .map(this::objectAt)
                .flatMap(Optional::stream)
                .toList();
    }
}
