package kwic.filters;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static kwic.TestKWICUtils.COLLECT_LINES;
import static kwic.TestKWICUtils.IS_SAME_LINES;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIgnore {

    private static final ArrayList<String> IGNORES_1 = new ArrayList<String>(Arrays.asList("is", "a"));
    private static final ArrayList<String> IGNORES_2 = new ArrayList<String>();

    private static FilterInterface FILTER_1 = Filters.NEW_FILTER_IGNORE(TestIgnore.IGNORES_1);
    private static FilterInterface FILTER_2 = Filters.NEW_FILTER_IGNORE(TestIgnore.IGNORES_2);

    @Test
    void Test_Input_Empty() {
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        ArrayList<ArrayList<String>> expected = input;
        assertTrue(IS_SAME_LINES(expected, actual));
    }


    @Test
    void Test_Input_INGORE_1_SHOULDREJECT_1() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("a");
        }};
        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("a");
            add("b");
        }};
        ArrayList<String> inputLine3 = new ArrayList<String>() {{
            add("A");
        }};
        ArrayList<String> inputLine4 = new ArrayList<String>() {{
            add("A");
            add("c");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4);

        // arrange expected
        ArrayList<ArrayList<String>> expected = COLLECT_LINES();


        // act
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_INGORE_1_SHOULDREJECT_2() {
        // arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("is");
        }};
        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("Is");
            add("some");
        }};
        ArrayList<String> inputLine3 = new ArrayList<String>() {{
            add("is");
            add("some");
        }};
        ArrayList<String> inputLine4 = new ArrayList<String>() {{
            add("IS");
        }};
        ArrayList<String> inputLine5 = new ArrayList<String>() {{
            add("IS");
            add("c");
        }};

        ArrayList<String> inputLine6 = new ArrayList<String>() {{
            add("Is");
            add("c");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4, inputLine5, inputLine6);

        // arrange expected
        ArrayList<ArrayList<String>> expected = COLLECT_LINES();


        // act
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));
    }

    @Test
    void Test_Input_INGORE_1_SHOULDACCEPT_1() {

// arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("isa");
        }};
        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("some");
            add("Is");
        }};
        ArrayList<String> inputLine3 = new ArrayList<String>() {{
            add("some");
            add("is");
        }};
        ArrayList<String> inputLine4 = new ArrayList<String>() {{
            add("ISS");
        }};
        ArrayList<String> inputLine5 = new ArrayList<String>() {{
            add("c");
            add("IS");
        }};

        ArrayList<String> inputLine6 = new ArrayList<String>() {{
            add("cis");
            add("Is");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4, inputLine5, inputLine6);

        // arrange expected
        ArrayList<ArrayList<String>> expected = input;


        // act
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));


    }

    @Test
    void Test_Input_INGORE_2_SHOULDACCEPT_1() {

// arrange inputs
        ArrayList<String> inputLine1 = new ArrayList<String>() {{
            add("isa");
        }};
        ArrayList<String> inputLine2 = new ArrayList<String>() {{
            add("some");
            add("Is");
        }};
        ArrayList<String> inputLine3 = new ArrayList<String>() {{
            add("some");
            add("is");
        }};
        ArrayList<String> inputLine4 = new ArrayList<String>() {{
            add("ISS");
        }};
        ArrayList<String> inputLine5 = new ArrayList<String>() {{
            add("c");
            add("IS");
        }};

        ArrayList<String> inputLine6 = new ArrayList<String>() {{
            add("cis");
            add("Is");
        }};

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4, inputLine5, inputLine6);

        // arrange expected
        ArrayList<ArrayList<String>> expected = input;


        // act
        ArrayList<ArrayList<String>> actual = FILTER_2.filter(input);
        // assert
        assertTrue(IS_SAME_LINES(expected, actual));


    }
}
