package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionParser {
    public static List<MoveDirection> parser(String[] args){
        List<MoveDirection> moveTable = new ArrayList<>();

        for (String arg:args) {
            switch (arg){
                case "f" -> moveTable.add(MoveDirection.FORWARD);
                case "b" -> moveTable.add(MoveDirection.BACKWARD);
                case "l" -> moveTable.add(MoveDirection.LEFT);
                case "r" -> moveTable.add(MoveDirection.RIGHT);
                default -> {
                    throw new IllegalArgumentException(arg + " is not legal move specification");
                }
            }
        }
        return moveTable;
    }
}

