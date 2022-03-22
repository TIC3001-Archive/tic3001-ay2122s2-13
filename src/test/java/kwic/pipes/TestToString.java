package kwic.pipes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static kwic.TestKWICUtils.COLLECT_LINES;
import static org.junit.jupiter.api.Assertions.*;

public class TestToString {

    @Test
    void Test_Input_Empty() {
        ArrayList<ArrayList<String>> expected = COLLECT_LINES();
        String actual = Pipes.TO_STRING(expected);
        assertEquals("", actual);
    }


    @Test
    void Test_Input_SingleWordLine() {
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(new ArrayList<String>(List.of("a")));
        String actual = Pipes.TO_STRING(expected);

        assertEquals("a", actual);
    }

    @Test
    void Test_Input_SingleWordLines() {
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(new ArrayList<String>(List.of("a")), new ArrayList<String>(List.of("b")));
        String actual = Pipes.TO_STRING(expected);

        assertEquals("a\nb", actual);
    }

    @Test
    void Test_Input_MultipleLines() {
        ArrayList<String> expectedLine1 = new ArrayList<String>(List.of("a", "d"));
        ArrayList<String> expectedLine2 = new ArrayList<String>(List.of("b"));
        ArrayList<String> expectedLine3 = new ArrayList<String>(List.of("c"));
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2, expectedLine3);
        String actual = Pipes.TO_STRING(expected);

        assertEquals("a d\nb\nc", actual);
    }
}
