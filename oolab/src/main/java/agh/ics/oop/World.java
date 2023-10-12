package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.sql.SQLOutput;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        MoveDirection[] table = OptionParser.parser(args);
        World.run(table);
        System.out.println("Stop");
    }

    public static void run(MoveDirection[] args){

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
