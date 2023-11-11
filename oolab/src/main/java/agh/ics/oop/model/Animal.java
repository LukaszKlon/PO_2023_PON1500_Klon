package agh.ics.oop.model;

public class Animal {

    final static Vector2d RIGHT_TOP_MAP_CORNER = new Vector2d(4, 4);
    final static Vector2d LEFT_BOTTOM_MAP_CORNER = new Vector2d(0, 0);
    final static Vector2d VECTOR_GO_UP = new Vector2d(0, 1);
    final static Vector2d VECTOR_GO_DOWN = new Vector2d(0, -1);
    final static Vector2d VECTOR_GO_LEFT = new Vector2d(-1, 0);
    final static Vector2d VECTOR_GO_RIGHT = new Vector2d(1, 0);
    private MapDirection orientation;

    private Vector2d cordinats;

    public Vector2d getCordinats() {
        return cordinats;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Animal() {
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d start) {
        this.orientation = MapDirection.NORTH;
        this.cordinats = start;
    }

    public String toString() {
        return "Orientacja: " + this.orientation.toString() + " pozycja: " + this.cordinats.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.cordinats.equals(position);
    }

    private boolean insideArea(Vector2d futureMove){
        return futureMove.precedes(RIGHT_TOP_MAP_CORNER) && futureMove.follows(LEFT_BOTTOM_MAP_CORNER);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
            case BACKWARD -> {
                    switch (this.orientation) {
                        case NORTH -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_DOWN);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                        case SOUTH -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_UP);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                        case EAST -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_LEFT);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                        case WEST -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_RIGHT);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                    }

                }
                case FORWARD -> {
                    switch (this.orientation) {
                        case NORTH -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_UP);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                        case SOUTH -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_DOWN);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                        case EAST -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_RIGHT);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                        case WEST -> {
                            Vector2d futureMove = this.cordinats.add(VECTOR_GO_LEFT);
                            if (insideArea(futureMove)) {
                                this.cordinats = futureMove;
                            }
                        }
                    }
                }
            }
        }
    }