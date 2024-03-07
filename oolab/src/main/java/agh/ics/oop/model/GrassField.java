package agh.ics.oop.model;

import agh.ics.oop.PositionAlreadyOccupiedException;

import java.lang.Math;
import java.util.*;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{

    private final Map<Vector2d,WorldElement<Vector2d>> grasses = new HashMap<>();

    public GrassField(int countGrass,int Id) {
        super(new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE),Id);
        int parameter = (int) Math.sqrt(countGrass*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(parameter, parameter, countGrass);
        for (Vector2d temporaryVector : randomPositionGenerator) {
            try {
                place(new Grass(temporaryVector));
            }
            catch (PositionAlreadyOccupiedException p){
                System.out.println(p.getMessage());
            }
        }
    }

    @Override
    public void place(WorldElement<Vector2d> object) throws PositionAlreadyOccupiedException {
        Vector2d position = object.getPosition();
        if (!object.movable()){
            grasses.put(position,object);
        }
        else{
            super.place(object);
        }
    }

    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || isOccupiedGrass(position) ;
    }
    private boolean isOccupiedGrass(Vector2d position){
        return grasses.containsKey(position);
    }

    @Override
    public Optional<WorldElement<Vector2d>> objectAt(Vector2d position) {
        Optional<WorldElement<Vector2d>> animal =  super.objectAt(position);
        if (animal.isPresent()){
            return animal;
        }
        if (isOccupiedGrass(position)){
            return Optional.ofNullable(grasses.get(position));
        }
        return Optional.empty();
    }

    @Override
    public List<WorldElement<Vector2d>> getElements() {
        List<WorldElement<Vector2d>> allElements = super.getElements();
        return Stream.concat(grasses.keySet().stream().map(grasses::get), allElements.stream()).toList();
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
