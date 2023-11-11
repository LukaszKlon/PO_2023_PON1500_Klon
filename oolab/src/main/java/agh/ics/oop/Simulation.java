package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<Animal> animalsArray = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions){
        for (Vector2d position:positions) {
            animalsArray.add(new Animal(position));
        }
        this.moves = moves;
    }

    public List<Animal> run(){
        int animalsCount = animalsArray.size();
        int currentAnimal = 0;
        for (MoveDirection move:moves) {
            Animal newAnimal = animalsArray.get(currentAnimal);
            newAnimal.move(move);
            currentAnimal++;
            System.out.println("Zwierzę " + currentAnimal +" " +newAnimal.toString() );
            if (currentAnimal >= animalsCount){
                currentAnimal = 0;
            }
        }
        return new ArrayList<Animal>(animalsArray);
    }



}
