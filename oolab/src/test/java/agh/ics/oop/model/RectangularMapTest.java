package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void canMoveToEmptyMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        assertEquals(true,rectangularMap.canMoveTo(new Vector2d(3,3)));
    }

    @Test
    void canMoveToOutsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        assertEquals(false,rectangularMap.canMoveTo(new Vector2d(6,6)));
    }

    @Test
    void canMoveToOccupiedPosition() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        rectangularMap.place(new Animal(new Vector2d(3,3)),new Vector2d(3,3));
        assertEquals(false,rectangularMap.canMoveTo(new Vector2d(3,3)));
    }

    @Test
    void placeOtherTypeThanAnimal() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        assertEquals(false,rectangularMap.place(new Grass(new Vector2d(2,2)),new Vector2d(2,2)));
    }

    @Test
    void placeAnimalInsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        assertEquals(true,rectangularMap.place(new Animal(new Vector2d(2,2)),new Vector2d(2,2)));
    }

    @Test
    void placeAnimalOutsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        assertEquals(false,rectangularMap.place(new Animal(new Vector2d(3,8)),new Vector2d(3,8)));
    }

    @Test
    void moveInsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        rectangularMap.place(new Animal(new Vector2d(2,2)),new Vector2d(2,2));
        rectangularMap.move(rectangularMap.objectAt(new Vector2d(2,2)),new Vector2d(2,2),MoveDirection.FORWARD);
        assertEquals(true,rectangularMap.isOccupied(new Vector2d(2,3)));
    }

    @Test
    void moveOutsideMap() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        rectangularMap.place(new Animal(new Vector2d(4,4)),new Vector2d(4,4));
        rectangularMap.move(rectangularMap.objectAt(new Vector2d(4,4)),new Vector2d(4,4),MoveDirection.FORWARD);
        assertEquals(true,rectangularMap.isOccupied(new Vector2d(4,4)));
    }

    @Test
    void moveToOccupiedPosition() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4),animalFirst.getPosition());
    }

    @Test
    void isOccupiedAnimal() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        assertEquals(true,rectangularMap.isOccupied(animalFirst.getPosition()));
    }

    @Test
    void isOccupiedEmptyPosition() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        assertEquals(false,rectangularMap.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAtAfterPlace() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        assertEquals(animalFirst,rectangularMap.objectAt(animalFirst.getPosition()));
    }

    @Test
    void objectAtAfterMoveToOccupiedPosition() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.FORWARD);
        assertEquals(animalFirst,rectangularMap.objectAt(animalFirst.getPosition()));
    }

    @Test
    void objectAtAfterMoves() {
        RectangularMap rectangularMap = new RectangularMap(5,5);
        Animal animalFirst = new Animal(new Vector2d(4,4));
        Animal animalSecond = new Animal(new Vector2d(3,4));
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.FORWARD);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.FORWARD);
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
        rectangularMap.place(animalFirst,animalFirst.getPosition());
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
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
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
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
        rectangularMap.move(rectangularMap.objectAt(animalFirst.getPosition()),animalFirst.getPosition(),MoveDirection.LEFT);
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
        rectangularMap.place(animalFirst,animalFirst.getPosition());
        rectangularMap.place(animalSecond,animalSecond.getPosition());
        assertEquals(2,rectangularMap.getElements().size());
    }
}