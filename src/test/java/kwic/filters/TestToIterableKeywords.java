package kwic.filters;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static kwic.TestKWICUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestToIterableKeywords {

    @Test
    void Test_Input_Empty() {
        String input = "";
        ArrayList<String> expected = new ArrayList<>();
        ArrayList<String> actual = Adapter.iterateKeywords(input);

        assertEquals(0, actual.size());
        assertTrue(isSameWords(expected, actual));
    }


    @Test
    void Test_Input_SingleWord() {
        String input = "a";
        ArrayList<String> expected = new ArrayList<>(List.of("a"));
        ArrayList<String> actual = Adapter.iterateKeywords(input);

        assertEquals(1, actual.size());
        assertTrue(isSameWords(expected, actual));
    }

    @Test
    void Test_Input_TwoWords() {
        String input = "a\nb";
        ArrayList<String> expected = new ArrayList<>(List.of("a", "b"));
        ArrayList<String> actual = Adapter.iterateKeywords(input);
        assertEquals(actual.size(), 2);
        assertEquals(2, expected.size());
        assertEquals("a", expected.get(0));
        assertEquals("b", expected.get(1));
        assertTrue(isSameWords(expected, actual));
    }

    @Test
    void Test_Input_ThreeWords() {
        String input = "a\nb\nc";
        ArrayList<String> expected = new ArrayList<>(List.of("a", "b", "c"));
        ArrayList<String> actual = Adapter.iterateKeywords(input);
        assertEquals(3, actual.size());
        assertEquals(3, expected.size());
        assertEquals("a", expected.get(0));
        assertEquals("b", expected.get(1));
        assertEquals("c", expected.get(2));
        assertTrue(isSameWords(expected, actual));
    }


}
