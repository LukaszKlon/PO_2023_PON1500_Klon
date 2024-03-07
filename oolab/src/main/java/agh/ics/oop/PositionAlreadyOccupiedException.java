package agh.ics.oop;

import agh.ics.oop.model.Vector2d;

public class PositionAlreadyOccupiedException extends Exception{
    public PositionAlreadyOccupiedException(Vector2d vector){
        super("Position "+vector+" is already Occupied");
    }

}
