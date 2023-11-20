package agh.ics.oop.model;

import agh.ics.oop.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void testPlaceException() throws PositionAlreadyOccupiedException{
        GrassField  grass =  new GrassField(10);
        grass.place(new Animal(new Vector2d(2,2)));
        assertThrows(PositionAlreadyOccupiedException.class, ()-> grass.place(new Animal(new Vector2d(2,2))));
    }


    @Test
    void placeAnimalInsideMap() {
        GrassField grassField = new GrassField(10);
        try{
            grassField.place(new Grass(new Vector2d(2,2)));
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(true,grassField.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void moveInsideMap() {
        GrassField grassField = new GrassField(10);
        try{
            grassField.place(new Animal(new Vector2d(2,2)));
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        grassField.move(grassField.objectAt(new Vector2d(2,2)),MoveDirection.FORWARD);
        assertEquals(true,grassField.isOccupied(new Vector2d(2,3)));
    }

    @Test
    void moveToOccupiedPosition() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            grassField.place(animalFirst);
            grassField.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        grassField.move(grassField.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        grassField.move(grassField.objectAt(animalFirst.getPosition()),MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4),animalFirst.getPosition());
    }

    @Test
    void isOccupiedAnimal() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            grassField.place(animalFirst);
            grassField.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(true,grassField.isOccupied(animalFirst.getPosition()));
    }

    @Test
    void isOccupiedGrass() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            grassField.place(animalFirst);
            grassField.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        Iterator<Vector2d> grassPosition = grassField.getGrassesKeys();
        assertEquals(true,grassField.isOccupied(grassPosition.next()));
    }
    @Test
    void isOccupiednotOcupied() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            grassField.place(animalFirst);
            grassField.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(false,grassField.isOccupied(new Vector2d(1000,1000)));
    }

    @Test
    void objectAtAnimal() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            grassField.place(animalFirst);
            grassField.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(animalFirst,grassField.objectAt(animalFirst.getPosition()));
    }

    @Test
    void objectAtGrass() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            grassField.place(animalFirst);
            grassField.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        Iterator<Vector2d> grassPosition = grassField.getGrassesKeys();
        assertEquals(true,grassField.objectAt(grassPosition.next()) instanceof Grass);
    }

    @Test
    void getElementsAllObjects() {
        GrassField grassField = new GrassField(10);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            grassField.place(animalFirst);
            grassField.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(12,grassField.getElements().size());
    }

}