package agh.ics.oop.model;

import javax.swing.text.Position;

public interface WorldElement<P> {


    /**
     * Get position.
     *
     * @param Position.
     * @return return position
     */

    P getPosition();

    String toString();

    boolean movable();

    void move(MoveDirection direction,MoveValidator validMove);

}
