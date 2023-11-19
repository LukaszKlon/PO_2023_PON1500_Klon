package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    void runOrientation1() {
        Animal animalTest = new Animal();
        String[] args = {"r","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves, positions, new RectangularMap(5,5) );
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(MapDirection.EAST,firstAnimal.getOrientation());
    }
    @Test
    void runOrientation2() {
        Animal animalTest = new Animal();
        String[] args = {"l","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(MapDirection.WEST,firstAnimal.getOrientation());
    }
    @Test
    void runOrientation3() {
        Animal animalTest = new Animal();
        String[] args = {"l","l"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(MapDirection.SOUTH,firstAnimal.getOrientation());
    }
    @Test
    void runOrientation4() {
        Animal animalTest = new Animal();
        String[] args = {"l","l","l"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(MapDirection.EAST,firstAnimal.getOrientation());
    }
    @Test
    void runOrientation5() {
        Animal animalTest = new Animal();
        String[] args = {};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(MapDirection.NORTH,firstAnimal.getOrientation());
    }
    @Test
    void runPositions1() {
        Animal animalTest = new Animal();
        String[] args = {"f","f","r","b"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(1,4),firstAnimal.getCordinats());
    }
    @Test
    void runPositions2() {
        Animal animalTest = new Animal();
        String[] args = {"f","f","r","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(4,4),firstAnimal.getCordinats());
    }
    @Test
    void runPositions3() {
        Animal animalTest = new Animal();
        String[] args = {"b","f","r","l"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(2,2),firstAnimal.getCordinats());
    }
    @Test
    void runPositions4() {
        Animal animalTest = new Animal();
        String[] args = {"l","f","f","l","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(0,1),firstAnimal.getCordinats());
    }
    @Test
    void runPositions5() {
        Animal animalTest = new Animal();
        String[] args = {"r","r","f","l","b"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(1,1),firstAnimal.getCordinats());
    }
    @Test
    void runPositionstwopets1() {
        String[] args = {"f","f","r","b"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        positions.add(new Vector2d(3,3));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(2,3));
        expectedResult.add(new Vector2d(3,3));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionstwopets2() {
        String[] args = {"f","f","r","b","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        positions.add(new Vector2d(3,3));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(2,3));
        expectedResult.add(new Vector2d(3,4));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionstwopets3() {
        String[] args = {"f","b","r","l","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        positions.add(new Vector2d(3,3));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(3,3));
        expectedResult.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionstwopets4() {
        String[] args = {"l","r","f","f","f","f","f","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        positions.add(new Vector2d(3,3));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(0,2));
        expectedResult.add(new Vector2d(4,3));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionstwopets5() {
        String[] args = {"b","f","b","f","b","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        positions.add(new Vector2d(3,3));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(2,0));
        expectedResult.add(new Vector2d(3,4));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionstwopets6() {
        String[] args = {"f","b","f","b","r","l","f","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0,0));
        positions.add(new Vector2d(4,4));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(2,2));
        expectedResult.add(new Vector2d(3,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionsthreepets1() {
        String[] args = {"f","b","f","b","r","l","f","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0,0));
        positions.add(new Vector2d(0,0));
        positions.add(new Vector2d(4,4));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(2,2));
        expectedResult.add(new Vector2d(3,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(7,7));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionsthreepets2() {
        String[] args = {"f","b","f","b","r","l","f","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0,0));
        positions.add(new Vector2d(2,2));
        positions.add(new Vector2d(4,4));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(0,2));
        expectedResult.add(new Vector2d(3,1));
        expectedResult.add(new Vector2d(3,5));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(7,7));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionsthreepets3() {
        String[] args = {"f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0,0));
        positions.add(new Vector2d(2,2));
        positions.add(new Vector2d(4,4));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(0,6));
        expectedResult.add(new Vector2d(2,6));
        expectedResult.add(new Vector2d(4,6));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(7,7));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPositionsthreepets4() {
        String[] args = {"f","l","r","f","f","r","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0,0));
        positions.add(new Vector2d(2,3));
        positions.add(new Vector2d(0,4));
        List<Vector2d> expectedResult = new ArrayList<>();
        expectedResult.add(new Vector2d(0,3));
        expectedResult.add(new Vector2d(1,3));
        expectedResult.add(new Vector2d(0,4));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(7,7));
        List<Animal> animalList = simulation.run();
        List<Vector2d> result = new ArrayList<>();
        for (Animal animal:animalList) {
            result.add(animal.getCordinats());
        }
        assertEquals(result,expectedResult);
    }
    @Test
    void runPlaceAnimalOnMap1() {
        String[] args = {"f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(-1,-1));
        positions.add(new Vector2d(8,9));
        positions.add(new Vector2d(0,4));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(7,7));
        List<Animal> animalList = simulation.run();
        assertEquals(1,animalList.size());
    }
    @Test
    void runPlaceAnimalOnMap2() {
        String[] args = {"f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(-1,-1));
        positions.add(new Vector2d(8,9));
        positions.add(new Vector2d(3,4));
        positions.add(new Vector2d(7,7));
        positions.add(new Vector2d(2,7));
        positions.add(new Vector2d(6,6));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(7,7));
        List<Animal> animalList = simulation.run();
        assertEquals(2,animalList.size());
    }
    @Test
    void runInsideMap1() {
        Animal animalTest = new Animal();
        String[] args = {"f","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(2,4),firstAnimal.getCordinats());
    }
    @Test
    void runInsideMap2() {
        Animal animalTest = new Animal();
        String[] args = {"b","b","b","b"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(2,0),firstAnimal.getCordinats());
    }
    @Test
    void runInsideMap3() {
        Animal animalTest = new Animal();
        String[] args = {"r","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(4,2),firstAnimal.getCordinats());
    }
    @Test
    void runInsideMap4() {
        Animal animalTest = new Animal();
        String[] args = {"l","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(0,2),firstAnimal.getCordinats());
    }
    @Test
    void runInsideMap5() {
        Animal animalTest = new Animal();
        String[] args = {"l","f","f","f","r","f","f","f"};
        List<MoveDirection> moves = OptionParser.parser(args);
        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2,2));
        Simulation simulation = new Simulation(moves,positions,new RectangularMap(5,5));
        List<Animal> animalList = simulation.run();
        Animal firstAnimal = animalList.get(0);
        assertEquals(new Vector2d(0,4),firstAnimal.getCordinats());
    }


}