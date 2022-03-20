package kwic.pipes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestShift {
    boolean IS_SAME_LINE(ArrayList<String> expected, ArrayList<String> actual) {
        int size = expected.size();
        if (size != actual.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (expected.get(i) != actual.get(i)) {
                return false;
            }
        }
        return true;
    }

    boolean IS_SAME_LINES(ArrayList<ArrayList<String>> expected, ArrayList<ArrayList<String>> actual) {
        int size = expected.size();
        if (size != actual.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!IS_SAME_LINE(expected.get(i), actual.get(i))) {
                return false;
            }
        }
        return true;
    }

    ArrayList<ArrayList<String>> COLLECT_LINES(ArrayList<String>... additions) {

        ArrayList<ArrayList<String>> inputs = new ArrayList<ArrayList<String>>();

        for (ArrayList<String> addition : additions) {
            inputs.add(addition);
        }
        return inputs;
    }

    @Test
    void Test_Input_Empty() {
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        ArrayList<ArrayList<String>> expected = input;
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_OneLineWithOneWord() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
        }};
        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1);
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_OneLineWithTwoWords() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
            add("b");
        }};
        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;
        ArrayList<String> expectedLine2 = new ArrayList<String>() {{
            add("b");
            add("a");
        }};
        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_OneLineWithThreeWords() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};
        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;
        ArrayList<String> expectedLine2 = new ArrayList<String>() {{
            add("b");
            add("c");
            add("a");
        }};
        ArrayList<String> expectedLine3 = new ArrayList<String>() {{
            add("c");
            add("a");
            add("b");
        }};
        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2, expectedLine3);
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoLinesWithOneWord() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("b");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;

        ArrayList<String> expectedLine2 = inputLine2;


        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoLinesWithTwoWords() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
            add("b");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("c");
            add("d");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;

        ArrayList<String> expectedLine2 = new ArrayList<String>() {{
            add("b");
            add("a");
        }};
        ArrayList<String> expectedLine3 = inputLine2;

        ArrayList<String> expectedLine4 = new ArrayList<String>() {{
            add("d");
            add("c");
        }};

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2, expectedLine3, expectedLine4);
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoLinesWithThreeWords() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("d");
            add("e");
            add("f");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;

        ArrayList<String> expectedLine2 = new ArrayList<String>() {{
            add("b");
            add("c");
            add("a");
        }};

        ArrayList<String> expectedLine3 = new ArrayList<String>() {{
            add("c");
            add("a");
            add("b");
        }};

        ArrayList<String> expectedLine4 = inputLine2;

        ArrayList<String> expectedLine5 = new ArrayList<String>() {{
            add("e");
            add("f");
            add("d");
        }};
        ArrayList<String> expectedLine6 = new ArrayList<String>() {{
            add("f");
            add("d");
            add("e");
        }};
        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2, expectedLine3, expectedLine4, expectedLine5, expectedLine6);
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }
    @Test
    void Test_Input_TwoLinesWithIrregularWordCounts() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("e");
            add("f");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;

        ArrayList<String> expectedLine2 = new ArrayList<String>() {{
            add("b");
            add("c");
            add("a");
        }};

        ArrayList<String> expectedLine3 = new ArrayList<String>() {{
            add("c");
            add("a");
            add("b");
        }};

        ArrayList<String> expectedLine4 = inputLine2;

        ArrayList<String> expectedLine5 = new ArrayList<String>() {{
            add("f");
            add("e");
        }};

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2, expectedLine3, expectedLine4, expectedLine5);
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

}
