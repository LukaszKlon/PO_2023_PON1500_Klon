package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animalsArray = new ArrayList<>();
    private final List<MoveDirection> moves;
    private WorldMap mapOfWorld;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions, WorldMap mapOfWorld){
        this.mapOfWorld = mapOfWorld;
        for (Vector2d position:positions) {
            Animal temporaryAnimal = new Animal(position);
            if(mapOfWorld.place(temporaryAnimal)){
                animalsArray.add(temporaryAnimal);
            }
        }
        this.moves = moves;
    }

    public List<Animal> run(){
        int animalsCount = animalsArray.size();
        int currentAnimal = 0;
        for (MoveDirection move:moves) {
            System.out.println(move);
            Animal newAnimal = animalsArray.get(currentAnimal);
            mapOfWorld.move(newAnimal,move);
            currentAnimal++;
            System.out.println(mapOfWorld.toString());
//            System.out.println("Zwierzę " + currentAnimal +" " +newAnimal.toString() );
            if (currentAnimal >= animalsCount){
                currentAnimal = 0;
            }
        }
        return new ArrayList<Animal>(animalsArray);
    }



}
