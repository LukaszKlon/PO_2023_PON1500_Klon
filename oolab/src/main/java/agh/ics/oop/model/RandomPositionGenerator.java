package agh.ics.oop.model;

import java.lang.reflect.Array;
import java.util.*;


public class RandomPositionGenerator implements Iterator<Vector2d>,Iterable<Vector2d>{

    private Vector2d[] vectorTable;
    private int currentIndex = 0;


    public RandomPositionGenerator(int height,int width,int pointNumbers) {
        generateVectors(height,width,pointNumbers);
    }

    private void generateVectors(int height,int width,int pointNumbers){
        vectorTable = new Vector2d[pointNumbers];
        Random random = new Random();
        int area = height*width;
        ArrayList<Integer> uniqueNumbers = new ArrayList<>();
        int temporaryIndex;
        int temporaryNumber;
        for (int i = 0; i < area; i++) {
            uniqueNumbers.add(i);
        }
        for(int i=0; i<pointNumbers;i++){
            temporaryIndex = random.nextInt(uniqueNumbers.size());
            temporaryNumber = uniqueNumbers.get(temporaryIndex);
            vectorTable[i] = new Vector2d(temporaryNumber/height,temporaryNumber%width);
            uniqueNumbers.remove(temporaryIndex);
        }
    }

    @Override
    public Iterator<Vector2d> iterator() {
        currentIndex = 0;
        return this;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < vectorTable.length;
    }

    @Override
    public Vector2d next() {
        if (hasNext()) {
            return vectorTable[currentIndex++];
        }
        return null;
    }


}
