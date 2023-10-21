package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        Vector2d a = new Vector2d(1,5);
//        System.out.println(a.toString());
        assertEquals("(1,5)",a.toString());
    }

    @Test
    void precedes() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,6);
        assertTrue(a.precedes(b));
    }

    @Test
    void precedes2() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(0,6);
        assertFalse(a.precedes(b));
    }
    @Test
    void precedes3() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(0,3);
        assertFalse(a.precedes(b));
    }
    @Test
    void precedes4() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,3);
        assertFalse(a.precedes(b));
    }
    @Test
    void follows() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,6);
        assertFalse(a.follows(b));
    }
    @Test
    void follows2() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(0,6);
        assertFalse(a.follows(b));
    }
    @Test
    void follows3() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,0);
        assertFalse(a.follows(b));
    }
    @Test
    void follows4() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(0,0);
        assertTrue(a.follows(b));
    }

    @Test
    void add() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,3);
        Vector2d c = new Vector2d(3,8);
        assertEquals(c,a.add(b));
    }

    @Test
    void subtract() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,3);
        Vector2d c = new Vector2d(-1,2);
        assertEquals(c,a.subtract(b));
    }

    @Test
    void upperRight() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,3);
        Vector2d c = new Vector2d(2,5);
        assertEquals(c,a.upperRight(b));
    }

    @Test
    void lowerLeft() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,3);
        Vector2d c = new Vector2d(1,3);
        assertEquals(c,a.lowerLeft(b));
    }

    @Test
    void opposite() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(-1,-5);
        assertEquals(b,a.opposite());
    }

    @Test
    void testEquals() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(1,5);
        assertTrue(a.equals(b));
    }

    @Test
    void testEquals2() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(2,5);
        assertFalse(a.equals(b));
    }

    @Test
    void testEquals3() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = null;
        assertFalse(a.equals(b));
    }

    @Test
    void testEquals4() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = null;
        assertTrue(a.equals(a));
    }

    @Test
    void testHashCode() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(1,5);
        boolean flag = a.hashCode() == b.hashCode();
        assertTrue(flag);
    }

    @Test
    void testHashCode2() {
        Vector2d a = new Vector2d(1,5);
        Vector2d b = new Vector2d(1,7);
        boolean flag = a.hashCode() == b.hashCode();
        assertFalse(flag);
    }
}