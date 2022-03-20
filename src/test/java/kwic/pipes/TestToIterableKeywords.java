package kwic.pipes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static kwic.TestKWICUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestToIterableKeywords {

    @Test
    void Test_Input_Empty() {
      String input = "";
        ArrayList<String> expected = new ArrayList<>();
      ArrayList<String> actual =  Pipes.TO_ITERABLE_KEYWORDS(input);

      assertTrue(actual.size() == 0);
      assertTrue(IS_SAME_WORDS(expected,actual));
    }


    @Test
    void Test_Input_SingleWord() {
        String input = "a";
        ArrayList<String> expected = new ArrayList<String>(List.of("a"));
        ArrayList<String> actual =  Pipes.TO_ITERABLE_KEYWORDS(input);

        assertTrue(actual.size() == 1);
        assertTrue(IS_SAME_WORDS(expected,actual));
    }

    @Test
    void Test_Input_TwoWords() {
        String input = "a\nb";
        ArrayList<String> expected = new ArrayList<String>(List.of("a","b"));
        ArrayList<String> actual =  Pipes.TO_ITERABLE_KEYWORDS(input);
        assertEquals(actual.size() ,2);
        assertTrue(expected.size() == 2);
        assertTrue(expected.get(0) == "a");
        assertTrue(expected.get(1) == "b");
        assertTrue(IS_SAME_WORDS(expected,actual));
    }

    @Test
    void Test_Input_ThreeWords() {
        String input = "a\nb\nc";
        ArrayList<String> expected = new ArrayList<String>(List.of("a","b","c"));
        ArrayList<String> actual =  Pipes.TO_ITERABLE_KEYWORDS(input);
        assertEquals(3 ,actual.size());
        assertTrue(expected.size() == 3);
        assertTrue(expected.get(0) == "a");
        assertTrue(expected.get(1) == "b");
        assertTrue(expected.get(2) == "c");
        assertTrue(IS_SAME_WORDS(expected,actual));
    }


}
