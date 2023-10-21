package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void testToStringN() {
        MapDirection x = MapDirection.NORTH;
        assertEquals("Północ",x.toString());
    }
    @Test
    void testToStringS() {
        MapDirection x = MapDirection.SOUTH;
        assertEquals("Południe",x.toString());
    }
    @Test
    void testToStringE() {
        MapDirection x = MapDirection.EAST;
        assertEquals("Wschód",x.toString());
    }
    @Test
    void testToStringW() {
        MapDirection x = MapDirection.WEST;
        assertEquals("Zachód",x.toString());
    }
    @Test
    void nextN() {
        MapDirection x = MapDirection.NORTH;
        assertEquals(MapDirection.EAST,x.next());
    }
    @Test
    void nextS() {
        MapDirection x = MapDirection.SOUTH;
        assertEquals(MapDirection.WEST,x.next());
    }
    @Test
    void nextE() {
        MapDirection x = MapDirection.EAST;
        assertEquals(MapDirection.SOUTH,x.next());
    }
    @Test
    void nextW() {
        MapDirection x = MapDirection.WEST;
        assertEquals(MapDirection.NORTH,x.next());
    }

    @Test
    void previousN() {
        MapDirection x = MapDirection.NORTH;
        assertEquals(MapDirection.WEST,x.previous());
    }

    @Test
    void previousS() {
        MapDirection x = MapDirection.SOUTH;
        assertEquals(MapDirection.EAST,x.previous());
    }
    @Test
    void previousE() {
        MapDirection x = MapDirection.EAST;
        assertEquals(MapDirection.NORTH,x.previous());
    }
    @Test
    void previousW() {
        MapDirection x = MapDirection.WEST;
        assertEquals(MapDirection.SOUTH,x.previous());
    }

    @Test
    void toUnitVectorN() {
        MapDirection x = MapDirection.NORTH;
        assertEquals(new Vector2d(0,1),x.toUnitVector());
    }

    @Test
    void toUnitVectorS() {
        MapDirection x = MapDirection.SOUTH;
        assertEquals(new Vector2d(0,-1),x.toUnitVector());
    }

    @Test
    void toUnitVectorE() {
        MapDirection x = MapDirection.EAST;
        assertEquals(new Vector2d(1,0),x.toUnitVector());
    }

    @Test
    void toUnitVectorW() {
        MapDirection x = MapDirection.WEST;
        assertEquals(new Vector2d(-1,0),x.toUnitVector());
    }
}