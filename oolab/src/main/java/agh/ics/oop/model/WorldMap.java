package agh.ics.oop.model;

import agh.ics.oop.PositionAlreadyOccupiedException;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;
import java.util.Optional;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap<T extends WorldElement<P>,P> extends MoveValidator<P>{

    /**
     * Place a animal on the map.
     *
     * @param object,position The object to place on the map.
     * @return True if the object was placed. The object cannot be placed if the move is not valid.
     */
    void place(T object) throws PositionAlreadyOccupiedException;

    /**
     * Moves an object (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(T object, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(P position);

    /**
     * Return an object at a given position.
     *
     * @param position The position of the animal.
     * @return object or null if the position is not occupied.
     */
    Optional<T> objectAt(P position);

    int getId();

    Boundary getCurrentBoundary();

    List<T> getOrderedAnimals();
}
