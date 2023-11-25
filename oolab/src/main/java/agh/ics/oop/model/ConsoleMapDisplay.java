package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{

    private int allActualizationNumber;

    public ConsoleMapDisplay() {
        this.allActualizationNumber = 0;
    }

    @Override
    public synchronized void mapChanged(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap);
        allActualizationNumber++;
        System.out.println("Map ID: " +worldMap.getId()+" updated "+allActualizationNumber+" times");
    }
}
