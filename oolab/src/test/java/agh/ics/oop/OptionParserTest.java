package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {

    @Test
    void parserExceptionThrow() {
        String[] table = {"b","b","f","op","K","l","b","f","r","lukasz","klon","for","f"};
        MoveDirection[] result = {MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.BACKWARD,
        MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.FORWARD};
        boolean flag = false;
        try{
            List<MoveDirection> resultParser= OptionParser.parser(table);
        }
        catch (IllegalArgumentException ex){
            flag = true;
        }
        assertTrue(flag);
    }

    @Test
    void parserCorrectData(){
        String[] table = {"b","l","f","r"};
        MoveDirection[] result = {MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.RIGHT};
        assertEquals(Arrays.asList(result),OptionParser.parser(table));
    }

    @Test
    void parserEmptyArray(){
        String[] table = {};
        MoveDirection[] result = {};
        assertEquals(Arrays.asList(result),OptionParser.parser(table));
    }
}