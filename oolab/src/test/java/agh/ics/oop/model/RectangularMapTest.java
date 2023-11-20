package agh.ics.oop.model;

import agh.ics.oop.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void testPlaceException() throws PositionAlreadyOccupiedException{
        RectangularMap rectangularMap = new RectangularMap(5,5);
        rectangularMap.place(new Animal(new Vector2d(2,2)));
        assertThrows(PositionAlreadyOccupiedException.class, ()-> rectangularMap.place(new Animal(new Vector2d(2,2))));
    }
    @Test
    void placeAnimalInsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        try{
            rectangularMap.place(new Animal(new Vector2d(2,2)));
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(true,rectangularMap.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void moveInsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        try{
            rectangularMap.place(new Animal(new Vector2d(2,2)));
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        rectangularMap.move(rectangularMap.objectAt(new Vector2d(2,2)),MoveDirection.FORWARD);
        assertEquals(true,rectangularMap.isOccupied(new Vector2d(2,3)));
    }

    @Test
    void moveOutsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        try{
            rectangularMap.place(new Animal(new Vector2d(4,4)));
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        rectangularMap.move(rectangularMap.objectAt(new Vector2d(4,4)),MoveDirection.FORWARD);
        assertEquals(true,rectangularMap.isOccupied(new Vector2d(4,4)));
    }

    @Test
    void moveToOccupiedPosition() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4),animalFirst.getPosition());
    }

    @Test
    void isOccupiedAnimal() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(true,rectangularMap.isOccupied(animalFirst.getPosition()));
    }

    @Test
    void isOccupiedEmptyPosition() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(false,rectangularMap.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAtAfterPlace() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(animalFirst,rectangularMap.objectAt(animalFirst.getPosition()));
    }

    @Test
    void objectAtAfterMoveToOccupiedPosition() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.FORWARD);
        assertEquals(animalFirst,rectangularMap.objectAt(animalFirst.getPosition()));
    }

    @Test
    void objectAtAfterMoves() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.FORWARD);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.FORWARD);
        assertEquals(animalFirst,rectangularMap.objectAt(animalFirst.getPosition()));
    }

    @Test
    void testToStringEmptyMap() {
        RectangularMap rectangularMap = new RectangularMap(3,3);
        String result = " y\\x  0 1 2\r\n" +
                "  3: -------\r\n" +
                "  2: | | | |\r\n" +
                "  1: | | | |\r\n" +
                "  0: | | | |\r\n" +
                " -1: -------\r\n";
        assertEquals(result,rectangularMap.toString());
    }

    @Test
    void testToStringOnePetNorthOrientation() {
        RectangularMap rectangularMap = new RectangularMap(3,3);
        Animal animalFirst = new Animal(new Vector2d(1,1));
        try{
            rectangularMap.place(animalFirst);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        String result = " y\\x  0 1 2\r\n" +
                "  3: -------\r\n" +
                "  2: | | | |\r\n" +
                "  1: | |N| |\r\n" +
                "  0: | | | |\r\n" +
                " -1: -------\r\n";
        assertEquals(result,rectangularMap.toString());
    }

    @Test
    void testToStringOnePetSouthOrientation() {
        RectangularMap rectangularMap = new RectangularMap(3,3);
        Animal animalFirst = new Animal(new Vector2d(1,1));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        String result = " y\\x  0 1 2\r\n" +
                "  3: -------\r\n" +
                "  2: | | | |\r\n" +
                "  1: | |S| |\r\n" +
                "  0: | | | |\r\n" +
                " -1: -------\r\n";
        assertEquals(result,rectangularMap.toString());
    }

    @Test
    void testToStringTwoPets() {
        RectangularMap rectangularMap = new RectangularMap(3,3);
        Animal animalFirst = new Animal(new Vector2d(1,1));
        Animal animalSecond = new Animal(new Vector2d(2,2));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),MoveDirection.LEFT);
        String result = " y\\x  0 1 2\r\n" +
                "  3: -------\r\n" +
                "  2: | | |N|\r\n" +
                "  1: | |S| |\r\n" +
                "  0: | | | |\r\n" +
                " -1: -------\r\n";
        assertEquals(result,rectangularMap.toString());
    }

    @Test
    void getElementsAllObjects() {
        RectangularMap rectangularMap = new RectangularMap(6,6);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        try{
            rectangularMap.place(animalFirst);
            rectangularMap.place(animalSecond);
        }
        catch (PositionAlreadyOccupiedException p){
            System.out.println(p.getMessage());
        }
        assertEquals(2,rectangularMap.getElements().size());
    }
}