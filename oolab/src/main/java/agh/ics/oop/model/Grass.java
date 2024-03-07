package agh.ics.oop.model;

public class Grass implements WorldElement<Vector2d>{

    private final Vector2d position;

    private static final boolean CAN_MOVE= false;

    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public boolean movable() {
        return CAN_MOVE;
    }

    @Override
    public void move(MoveDirection direction, MoveValidator validMove) {
    }

    @Override
    public String getResource() {
        return "grass.png";
    }

}
