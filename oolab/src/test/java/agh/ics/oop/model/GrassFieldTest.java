package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canMoveToEmptyMap() {
        GrassField grassField = new GrassField(10);
        assertEquals(true,grassField.canMoveTo(new Vector2d(3,3)));
    }

    @Test
    void canMoveToOccupiedPosition() {
        GrassField grassField = new GrassField(10);
        grassField.place(new Animal(new Vector2d(3,3)),new Vector2d(3,3));
        assertEquals(false,grassField.canMoveTo(new Vector2d(3,3)));
    }

    @Test
    void placeOtherTypeThanAnimal() {
        GrassField grassField = new GrassField(10);
        assertEquals(true,grassField.place(new Grass(new Vector2d(2,2)),new Vector2d(2,2)));
    }

    @Test
    void placeAnimalInsideMap() {
        GrassField grassField = new GrassField(10);
        assertEquals(true,grassField.place(new Animal(new Vector2d(2,2)),new Vector2d(2,2)));
    }

    @Test
    void moveInsideMap() {
        GrassField grassField = new GrassField(10);
        grassField.place(new Animal(new Vector2d(2,2)),new Vector2d(2,2));
        grassField.move(grassField.objectAt(new Vector2d(2,2)),new Vector2d(2,2),MoveDirection.FORWARD);
        assertEquals(true,grassField.isOccupied(new Vector2d(2,3)));
    }

    @Test
    void moveToOccupiedPosition() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        grassField.place(animalFirst,animalFirst.getPosition());
        grassField.place(animalSecond,animalSecond.getPosition());
        grassField.move(grassField.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
        grassField.move(grassField.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4),animalFirst.getPosition());
    }

    @Test
    void isOccupiedAnimal() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        grassField.place(animalFirst,animalFirst.getPosition());
        grassField.place(animalSecond,animalSecond.getPosition());
        assertEquals(true,grassField.isOccupied(animalFirst.getPosition()));
    }

    @Test
    void isOccupiedGrass() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        grassField.place(animalFirst,animalFirst.getPosition());
        grassField.place(animalSecond,animalSecond.getPosition());
        Iterator<Vector2d> grassPosition = grassField.getGrassesKeys();
        assertEquals(true,grassField.isOccupied(grassPosition.next()));
    }
    @Test
    void isOccupiednotOcupied() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        grassField.place(animalFirst,animalFirst.getPosition());
        grassField.place(animalSecond,animalSecond.getPosition());
        assertEquals(false,grassField.isOccupied(new Vector2d(1000,1000)));
    }

    @Test
    void objectAtAnimal() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        grassField.place(animalFirst,animalFirst.getPosition());
        grassField.place(animalSecond,animalSecond.getPosition());
        assertEquals(animalFirst,grassField.objectAt(animalFirst.getPosition()));
    }

    @Test
    void objectAtGrass() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        grassField.place(animalFirst,animalFirst.getPosition());
        grassField.place(animalSecond,animalSecond.getPosition());
        Iterator<Vector2d> grassPosition = grassField.getGrassesKeys();
        assertEquals(true,grassField.objectAt(grassPosition.next()) instanceof Grass);
    }

}