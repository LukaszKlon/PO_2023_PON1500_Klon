package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionParser {
    public static MoveDirection[] parser(String[] args){
        int l =0;

        // wyznaczamy długość tablicy
        for (String arg:args) {

            if ("f".equalsIgnoreCase(arg) || "b".equalsIgnoreCase(arg) ||"l".equalsIgnoreCase(arg) ||"r".equalsIgnoreCase(arg) ){
                l++;
            }

        }

        MoveDirection[] table = new MoveDirection[l];
        int i = 0;

        for (String arg:args) {

            switch (arg.toLowerCase()){
                case "b":
                    table[i] = MoveDirection.BACKWARD;
                    i++;
                    break;
                case "f":
                    table[i] = MoveDirection.FORWARD;
                    i++;
                    break;
                case "l":
                    table[i] = MoveDirection.LEFT;
                    i++;
                    break;
                case "r":
                    table[i] = MoveDirection.RIGHT;
                    i++;
                    break;
                default:
                    break;
            }
        }
        return table;
    }
}

