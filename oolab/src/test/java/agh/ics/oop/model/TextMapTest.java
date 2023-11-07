//package agh.ics.oop.model;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TextMapTest {
//
//    @Test
//    void canMoveTo() {
//    }
//
//    @Test
//    void place1() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        System.out.println(map.getStringList());
//        assertEquals(3,map.getStringList().size());
//    }
//
//    @Test
//    void place2() {
//        TextMap map = new TextMap();
//        System.out.println(map.getStringList());
//        assertEquals(0,map.getStringList().size());
//    }
//
//    @Test
//    void move1() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        map.move(map.objectAt(0),0,MoveDirection.RIGHT);
//        map.move(map.objectAt(0),0,MoveDirection.FORWARD);
//        List<String> Result = new ArrayList<>();
//        Result.add("ma");
//        Result.add("Ala");
//        Result.add("sowoniedzwiedzia");
//        assertEquals(Result,map.getStringList());
//    }
//
//    @Test
//    void move2() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        map.move(map.objectAt(0),0,MoveDirection.LEFT);
//        map.move(map.objectAt(0), 0,MoveDirection.FORWARD);
//        List<String> Result = new ArrayList<>();
//        Result.add("Ala");
//        Result.add("ma");
//        Result.add("sowoniedzwiedzia");
//        assertEquals(Result,map.getStringList());
//    }
//
//    @Test
//    void move3() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        map.move(map.objectAt(1), 1,MoveDirection.LEFT);
//        map.move(map.objectAt(1),1,MoveDirection.FORWARD);
//        List<String> Result = new ArrayList<>();
//        Result.add("ma");
//        Result.add("Ala");
//        Result.add("sowoniedzwiedzia");
//        assertEquals(Result,map.getStringList());
//    }
//
//    @Test
//    void move4() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        map.move(map.objectAt(1), 1,MoveDirection.RIGHT);
//        map.move(map.objectAt(1),1,MoveDirection.FORWARD);
//        List<String> Result = new ArrayList<>();
//        Result.add("Ala");
//        Result.add("sowoniedzwiedzia");
//        Result.add("ma");
//        assertEquals(Result,map.getStringList());
//    }
//
//    @Test
//    void move5() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        map.move(map.objectAt(1), 1,MoveDirection.RIGHT);
//        map.move(map.objectAt(1),1,MoveDirection.BACKWARD);
//        List<String> Result = new ArrayList<>();
//        Result.add("ma");
//        Result.add("Ala");
//        Result.add("sowoniedzwiedzia");
//        assertEquals(Result,map.getStringList());
//    }
//
//    @Test
//    void move6() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        map.move(map.objectAt(0), 0,MoveDirection.RIGHT);
//        map.move(map.objectAt(0),0,MoveDirection.FORWARD);
//        map.move(map.objectAt(2), 2,MoveDirection.RIGHT);
//        map.move(map.objectAt(2),2,MoveDirection.BACKWARD);
//        List<String> Result = new ArrayList<>();
//        Result.add("ma");
//        Result.add("sowoniedzwiedzia");
//        Result.add("Ala");
//        assertEquals(Result,map.getStringList());
//    }
//
//    @Test
//    void move7() {
//        TextMap map = new TextMap();
//        map.place("a");
//        map.place("b");
//        map.place("c");
//        map.place("d");
//        map.place("e");
//        map.move(map.objectAt(0), 0,MoveDirection.RIGHT);
//        map.move(map.objectAt(0),0,MoveDirection.FORWARD);
//        map.move(map.objectAt(2), 2,MoveDirection.RIGHT);
//        map.move(map.objectAt(2),2,MoveDirection.BACKWARD);
//        map.move(map.objectAt(4), 4,MoveDirection.RIGHT);
//        map.move(map.objectAt(4),4,MoveDirection.BACKWARD);
//        List<String> Result = new ArrayList<>();
//        Result.add("b");
//        Result.add("c");
//        Result.add("a");
//        Result.add("e");
//        Result.add("d");
//        assertEquals(Result,map.getStringList());
//    }
//
//    @Test
//    void isOccupied1() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        assertEquals(true,map.isOccupied(2));
//    }
//
//    @Test
//    void isOccupied2() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        assertEquals(false,map.isOccupied(4));
//    }
//
//    @Test
//    void isOccupied3() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        assertEquals(false,map.isOccupied(-1));
//    }
//
//    @Test
//    void objectAt1() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        assertEquals("Ala",map.objectAt(0));
//    }
//
//    @Test
//    void objectAt2() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        assertEquals("ma",map.objectAt(1));
//    }
//
//    @Test
//    void objectAt3() {
//        TextMap map = new TextMap();
//        map.place("Ala");
//        map.place("ma");
//        map.place("sowoniedzwiedzia");
//        assertEquals("sowoniedzwiedzia",map.objectAt(2));
//    }
//
//    @Test
//    void testToString() {
//    }
//}