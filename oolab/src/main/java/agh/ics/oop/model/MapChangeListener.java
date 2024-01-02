package agh.ics.oop.model;

@FunctionalInterface
public interface MapChangeListener {

    void mapChanged(WorldMap<WorldElement<Vector2d>,Vector2d> worldMap, String message);
}
