package agh.ics.oop;

import agh.ics.oop.model.*;

import java.sql.SQLOutput;
import java.util.List;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        Animal zwierzak = new Animal();
        List<MoveDirection> directions = OptionParser.parser(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        GrassField grassField = new GrassField(10);
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
        grassField.registerObserver(consoleMapDisplay);
        Simulation simulation = new Simulation(directions, positions,grassField);
        simulation.run();
        System.out.println("Stop");
    }

    public static void run(List<MoveDirection> args){

        for (MoveDirection arg:args) {
            switch (arg){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
                default -> System.out.println("błąd");
            }
        }
    }
}
