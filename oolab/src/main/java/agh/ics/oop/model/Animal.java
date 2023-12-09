package agh.ics.oop.model;

import agh.ics.oop.PositionAlreadyOccupiedException;

public class Animal implements WorldElement<Vector2d>{

    final static Vector2d RIGHT_TOP_MAP_CORNER = new Vector2d(4, 4);
    final static Vector2d LEFT_BOTTOM_MAP_CORNER = new Vector2d(0, 0);
    final static Vector2d VECTOR_GO_UP = new Vector2d(0, 1);
    final static Vector2d VECTOR_GO_DOWN = new Vector2d(0, -1);
    final static Vector2d VECTOR_GO_LEFT = new Vector2d(-1, 0);
    final static Vector2d VECTOR_GO_RIGHT = new Vector2d(1, 0);

    private static final boolean CAN_MOVE= true;
    private MapDirection orientation;

    private Vector2d position;

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Animal() {
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d start) {
        this.orientation = MapDirection.NORTH;
        this.position = start;
    }

    public String toString() {
        return switch (orientation){
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    @Override
    public boolean movable() {
        return CAN_MOVE;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    private boolean insideArea(Vector2d futureMove){
        return futureMove.precedes(RIGHT_TOP_MAP_CORNER) && futureMove.follows(LEFT_BOTTOM_MAP_CORNER);
    }

    public void move(MoveDirection direction,MoveValidator validMove) {
            switch (direction) {
                case LEFT -> this.orientation = this.orientation.previous();
                case RIGHT -> this.orientation = this.orientation.next();
                case BACKWARD -> {
                    switch (this.orientation) {
                        case NORTH -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_DOWN);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                        case SOUTH -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_UP);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                        case EAST -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_LEFT);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                        case WEST -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_RIGHT);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                    }

                }
                case FORWARD -> {
                    switch (this.orientation) {
                        case NORTH -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_UP);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                        case SOUTH -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_DOWN);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                        case EAST -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_RIGHT);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                        case WEST -> {
                            Vector2d futureMove = this.position.add(VECTOR_GO_LEFT);
                            if (validMove.canMoveTo(futureMove)) {
                                this.position = futureMove;
                            }
                        }
                    }
                }
            }
        }
    }