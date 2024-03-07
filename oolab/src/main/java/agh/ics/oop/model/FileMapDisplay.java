package agh.ics.oop.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class FileMapDisplay implements MapChangeListener{

    private final String fileName;

    public FileMapDisplay(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap) {
        fileName = "Map_" + worldMap.getId() + ".log";
        try (FileWriter fileWriter = new FileWriter(fileName, false)) {
            fileWriter.write( "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mapChanged(WorldMap<WorldElement<Vector2d>, Vector2d> worldMap, String message) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(message+ "\n");
            fileWriter.write(worldMap.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
