package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionParser {
    public static MoveDirection[] parser(String[] args){
        int l =0;

        for (String arg:args) {

            switch (arg.toLowerCase()){
                case "backward": break;
                case "forward": break;
                case "left": break;
                case "right": break;
                default:
                    l--;
                    break;
            }
            l++;
        }

        MoveDirection[] table = new MoveDirection[l];
        int i = 0;

        for (String arg:args) {

            switch (arg.toLowerCase()){
                case "backward":
                    table[i] = MoveDirection.BACKWARD;
                    i++;
                    break;
                case "forward":
                    table[i] = MoveDirection.FORWARD;
                    i++;
                    break;
                case "left":
                    table[i] = MoveDirection.LEFT;
                    i++;
                    break;
                case "right":
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

