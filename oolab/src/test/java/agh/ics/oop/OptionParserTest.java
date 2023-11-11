package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionParserTest {

    @Test
    void parser() {
        String[] table = {"b","b","f","op","K","l","b","f","r","lukasz","klon","for","f"};
        MoveDirection[] result = {MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.BACKWARD,
        MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.FORWARD};
        assertEquals(Arrays.asList(result),OptionParser.parser(table));
    }

    @Test
    void parser2(){
        String[] table = {"b","oo","kl"};
        MoveDirection[] result = {MoveDirection.BACKWARD};
        assertEquals(Arrays.asList(result),OptionParser.parser(table));
    }

    @Test
    void parser3(){
        String[] table = {"back","oo","kl"};
        MoveDirection[] result = {};
        assertEquals(Arrays.asList(result),OptionParser.parser(table));
    }
}