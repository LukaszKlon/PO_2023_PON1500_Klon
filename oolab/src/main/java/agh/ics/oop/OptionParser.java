package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionParser  {
    public static List<MoveDirection> parser(String[] args){
        List<MoveDirection> moveTable = new ArrayList<>();
        return Arrays.stream(args).map(arg -> {
            switch (arg) {
                case "f":
                    return MoveDirection.FORWARD;
                case "b":
                    return MoveDirection.BACKWARD;
                case "l":
                    return MoveDirection.LEFT;
                case "r":
                    return MoveDirection.RIGHT;
                default:
                    throw new IllegalArgumentException(arg + " is not a legal move specification");
            }
        }).collect(Collectors.toList());
//        for (String arg:args) {
//            switch (arg){
//                case "f" -> moveTable.add(MoveDirection.FORWARD);
//                case "b" -> moveTable.add(MoveDirection.BACKWARD);
//                case "l" -> moveTable.add(MoveDirection.LEFT);
//                case "r" -> moveTable.add(MoveDirection.RIGHT);
//                default -> {
//                    throw new IllegalArgumentException(arg + " is not legal move specification");
//                }
//            }
//        }
//        return moveTable;
    }
}

