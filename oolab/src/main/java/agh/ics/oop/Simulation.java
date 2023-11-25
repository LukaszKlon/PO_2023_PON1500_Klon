package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Simulation implements Runnable {

    private final List<Animal> animalsArray = new ArrayList<>();
    private final List<MoveDirection> moves;
    private final WorldMap<WorldElement<Vector2d>,Vector2d> mapOfWorld;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions,  WorldMap<WorldElement<Vector2d>,Vector2d> mapOfWorld){
        this.mapOfWorld = mapOfWorld;
        for (Vector2d position:positions) {
            Animal temporaryAnimal = new Animal(position);
            try{
                if (this.mapOfWorld.canMoveTo(position)){
                    this.animalsArray.add(temporaryAnimal);
                }
                mapOfWorld.place(temporaryAnimal);
            }
            catch (PositionAlreadyOccupiedException p){
                System.out.println(p.getMessage());
            }
        }
        this.moves = moves;
    }

    @Override
    public void run() {
        int animalsCount = animalsArray.size();
        int currentAnimal = 0;
        for (MoveDirection move:moves) {
            Animal newAnimal = animalsArray.get(currentAnimal);
            mapOfWorld.move(newAnimal,move);
            currentAnimal++;
            currentAnimal = currentAnimal % animalsCount;
        }
    }

    public List<Animal> getAnimalsArray() {
        return new ArrayList<Animal>(animalsArray);
    }

}
