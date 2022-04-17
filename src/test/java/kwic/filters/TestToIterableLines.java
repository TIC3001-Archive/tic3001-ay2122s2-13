package kwic.filters;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static kwic.TestKWICUtils.COLLECT_LINES;
import static kwic.TestKWICUtils.isSameLines;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestToIterableLines {

    @Test
    void Test_Input_Empty() {
        String input = "";
        ArrayList<ArrayList<String>> expected = COLLECT_LINES();
        ArrayList<ArrayList<String>> actual = Adapter.iterateTitles(input);

        assertEquals(0, actual.size());
        assertTrue(isSameLines(expected, actual));
    }


    @Test
    void Test_Input_SingleWordLine() {
        String input = "a";
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(new ArrayList<>(List.of("a")));
        ArrayList<ArrayList<String>> actual = Adapter.iterateTitles(input);

        assertEquals(1, actual.size());
        assertTrue(isSameLines(expected, actual));
    }

    @Test
    void Test_Input_Paragraph_Add_EOL() {
        String fileContent = "I am a dog. I am a ";
        assertEquals("I am a dog." + System.lineSeparator() + "I am a ", Adapter.delimitLinesInParagraphWithEOL(fileContent));
    }

    @Test
    void Test_Input_SingleWordLines() {
        String input = "a" + System.lineSeparator() + "b";
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(new ArrayList<>(List.of("a")), new ArrayList<>(List.of("b")));
        ArrayList<ArrayList<String>> actual = Adapter.iterateTitles(input);

        assertEquals(2, actual.size());
        assertEquals("b", actual.get(1).get(0));
        assertEquals("a", actual.get(0).get(0));
        assertTrue(isSameLines(expected, actual));
    }

    @Test
    void Test_Input_MultipleLines() {
        String input = "a d" + System.lineSeparator() + "b" + System.lineSeparator() + "c";
        ArrayList<String> expectedLine1 = new ArrayList<>(List.of("a", "d"));
        ArrayList<String> expectedLine2 = new ArrayList<>(List.of("b"));
        ArrayList<String> expectedLine3 = new ArrayList<>(List.of("c"));
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2, expectedLine3);
        ArrayList<ArrayList<String>> actual = Adapter.iterateTitles(input);

        assertEquals(3, actual.size());
        assertTrue(isSameLines(expected, actual));
    }
}
