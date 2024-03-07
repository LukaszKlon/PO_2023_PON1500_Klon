package agh.ics.oop.model;

import agh.ics.oop.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void getOrderedAnimals() {
        RectangularMap map = new RectangularMap(5,5,0);
        try {
            map.place(new Animal(new Vector2d(2,1)));
            map.place(new Animal(new Vector2d(1,2)));
            map.place(new Animal(new Vector2d(2,2)));
            map.place(new Animal(new Vector2d(1,1)));
            map.place(new Animal(new Vector2d(3,3)));
            map.place(new Animal(new Vector2d(2,4)));
        }
        catch (PositionAlreadyOccupiedException e){
            e.printStackTrace();
        }
        List <WorldElement<Vector2d>> l = new ArrayList<>();
        l.add(new Animal(new Vector2d(1,1)));
        l.add(new Animal(new Vector2d(1,2)));
        l.add(new Animal(new Vector2d(2,1)));
        l.add(new Animal(new Vector2d(2,2)));
        l.add(new Animal(new Vector2d(2,4)));
        l.add(new Animal(new Vector2d(3,3)));
        assertEquals(l.stream().map(WorldElement::getPosition).collect(Collectors.toList()), map.getOrderedAnimals().stream().map(WorldElement::getPosition).collect(Collectors.toList()));
    }
}