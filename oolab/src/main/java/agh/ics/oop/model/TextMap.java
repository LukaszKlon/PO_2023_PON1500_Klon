package agh.ics.oop.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextMap implements WorldMap<String,Integer>{

    private final List<MapDirection> orientationList;
    private final List<String> stringList;

    public TextMap(){
        orientationList = new ArrayList<>();
        stringList = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return isOccupied(position);
    }

    @Override
    public boolean place(String object, Integer position) {
        if (position == stringList.size()){
            stringList.add(object);
            orientationList.add(MapDirection.NORTH);
            return true;
        }
        return false;
    }

    public boolean place(String object) {
        return place(object,stringList.size());
    }
    @Override
    public void move(String object, Integer position, MoveDirection direction) {
        if (stringList.get(position).equals(object)){
            switch(direction){
                case LEFT -> orientationList.set(position,orientationList.get(position).previous());
                case RIGHT -> orientationList.set(position,orientationList.get(position).next());
                case BACKWARD -> {
                    switch (orientationList.get(position)){
                        case EAST -> {
                            if (canMoveTo(position-1)){
                                swap(position,position-1);
                            }
                        }
                        case WEST ->{
                            if (canMoveTo(position+1)){
                                swap(position,position+1);
                            }
                        }
                        case SOUTH,NORTH -> {}
                    }
                }
                case FORWARD -> {
                    switch (orientationList.get(position)){
                        case EAST -> {
                            if (canMoveTo(position+1)){
                                swap(position,position+1);
                            }
                        }
                        case WEST ->{
                            if (canMoveTo(position-1)){
                                swap(position,position-1);
                            }
                        }
                        case SOUTH,NORTH -> {}
                    }
                }
            }
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return 0 <= position && position < stringList.size();
    }

    @Override
    public String objectAt(Integer position) {
        return stringList.get(position);
    }

    @Override
    public String toString(){
        String result = "[";
        for (String string:stringList.subList(0,stringList.size()-1)) {
            result = result + "\"" + string + "\",";
        }
        result = result + "\"" + stringList.get(stringList.size()-1) + "\"]";
        return result;
    }

    public List<String> getStringList() {
        return stringList;
    }

    private void swap(int position1,int position2){
        Collections.swap(stringList,position1,position2);
        Collections.swap(orientationList,position1,position2);
    }
}
