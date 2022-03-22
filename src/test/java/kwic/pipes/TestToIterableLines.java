package kwic.pipes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static kwic.TestKWICUtils.COLLECT_LINES;
import static kwic.TestKWICUtils.IS_SAME_LINES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestToIterableLines {

    @Test
    void Test_Input_Empty() {
        String input = "";
        ArrayList<ArrayList<String>> expected = COLLECT_LINES();
        ArrayList<ArrayList<String>> actual = Pipes.TO_ITERABLE_TITLES(input);

        assertEquals(0, actual.size());
        assertTrue(IS_SAME_LINES(expected, actual));
    }


    @Test
    void Test_Input_SingleWordLine() {
        String input = "a";
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(new ArrayList<>(List.of("a")));
        ArrayList<ArrayList<String>> actual = Pipes.TO_ITERABLE_TITLES(input);

        assertEquals(1, actual.size());
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_SingleWordLines() {
        String input = "a\nb";
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(new ArrayList<>(List.of("a")), new ArrayList<>(List.of("b")));
        ArrayList<ArrayList<String>> actual = Pipes.TO_ITERABLE_TITLES(input);

        assertEquals(2, actual.size());
        assertEquals("b", actual.get(1).get(0));
        assertEquals("a", actual.get(0).get(0));
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_MultipleLines() {
        String input = "a d\nb\nc";
        ArrayList<String> expectedLine1 = new ArrayList<>(List.of("a", "d"));
        ArrayList<String> expectedLine2 = new ArrayList<>(List.of("b"));
        ArrayList<String> expectedLine3 = new ArrayList<>(List.of("c"));
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2, expectedLine3);
        ArrayList<ArrayList<String>> actual = Pipes.TO_ITERABLE_TITLES(input);

        assertEquals(3, actual.size());
        assertTrue(IS_SAME_LINES(expected, actual));
    }
}
