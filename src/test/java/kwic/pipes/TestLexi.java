package kwic.pipes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static kwic.TestKWICUtils.COLLECT_LINES;
import static kwic.TestKWICUtils.IS_SAME_LINES;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLexi {

    @Test
    void Test_Input_Empty() {
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
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
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
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

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1);
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
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
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1);

        // act
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoSortedLinesWithOneWord() {
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
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoUnsortedSortedLinesWithTwoWords_1() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("c");
            add("d");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("a");
            add("b");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine2;
        ArrayList<String> expectedLine2 = inputLine1;

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoUnsortedSortedLinesWithTwoWords_2() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("c");
            add("d");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("c");
            add("b");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine2;
        ArrayList<String> expectedLine2 = inputLine1;

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoLinesWithThreeWords_1() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("d");
            add("e");
            add("f");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine2;
        ArrayList<String> expectedLine2 = inputLine1;

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoLinesWithThreeWords_2() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("d");
            add("e");
            add("f");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("d");
            add("e");
            add("c");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine2;
        ArrayList<String> expectedLine2 = inputLine1;

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_TwoLinesWithThreeWords_3_ShouldBeCaseInsensitive() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("D");
            add("e");
            add("c");
        }};

        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("d");
            add("e");
            add("c");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine1;
        ArrayList<String> expectedLine2 = inputLine2;

        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }


    @Test
    void Test_Input_TwoLinesWithIrregularWordCounts() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("e");
            add("f");
        }};
        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};


        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2);
        // arrange expected
        ArrayList<String> expectedLine1 = inputLine2;

        ArrayList<String> expectedLine2 = inputLine1;


        // act
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(expectedLine1, expectedLine2);
        ArrayList<ArrayList<String>> actual = Pipes.LEXI(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

}
