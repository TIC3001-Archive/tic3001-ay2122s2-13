package kwic.filters;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static kwic.TestKWICUtils.*;
import static kwic.filters.Adapter.toHashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRequire {

    private static final ArrayList<String> REQUIRES_1 = new ArrayList<>(Arrays.asList("is", "a"));
    private static final ArrayList<String> REQUIRES_2 = new ArrayList<>();

    private static final Selector.SelectionFilter FILTER_1 = Selector.newRequireFilter(Selector.newRequiredLinePredicate(toHashSet(TestRequire.REQUIRES_1)));
    private static final Selector.SelectionFilter FILTER_2 = Selector.newRequireFilter(Selector.newRequiredLinePredicate(toHashSet(TestRequire.REQUIRES_2)));

    @Test
    void Test_Input_Empty() {
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        ArrayList<ArrayList<String>> expected = input;
        assertTrue(isSameLines(expected, actual));
    }


    @Test
    void Test_Input_ACCEPT_1_SHOULDACCEPT_1() {
        // arrange inputs
        ArrayList<String> inputLine1 = CREATE_LINE("a");
        ArrayList<String> inputLine2 = CREATE_LINE("a", "b");
        ArrayList<String> inputLine3 = CREATE_LINE("A");
        ArrayList<String> inputLine4 = CREATE_LINE("A", "c");

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4);

        // arrange expected
        ArrayList<ArrayList<String>> expected = input;

        // act
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        // assert
        assertTrue(isSameLines(expected, actual));
    }

    @Test
    void Test_Input_ACCEPT_1_SHOULDACCEPT_2() {
        // arrange inputs
        ArrayList<String> inputLine1 = CREATE_LINE("is");
        ArrayList<String> inputLine2 = CREATE_LINE("Is", "some");
        ArrayList<String> inputLine3 = CREATE_LINE("is", "some");
        ArrayList<String> inputLine4 = CREATE_LINE("IS");
        ArrayList<String> inputLine5 = CREATE_LINE("IS", "c");
        ArrayList<String> inputLine6 = CREATE_LINE("Is", "c");

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4, inputLine5, inputLine6);

        // arrange expected
        ArrayList<ArrayList<String>> expected = input;


        // act
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        // assert
        assertTrue(isSameLines(expected, actual));
    }

    @Test
    void Test_Input_ACCEPT_1_SHOULDREJECT_1() {

        // arrange inputs
        ArrayList<String> inputLine1 = CREATE_LINE("isa");
        ArrayList<String> inputLine2 = CREATE_LINE("some", "Is");
        ArrayList<String> inputLine3 = CREATE_LINE("some", "is");
        ArrayList<String> inputLine4 = CREATE_LINE("ISS");
        ArrayList<String> inputLine5 = CREATE_LINE("c", "IS");
        ArrayList<String> inputLine6 = CREATE_LINE("cis", "Is");

        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4, inputLine5, inputLine6);

        // arrange expected
        ArrayList<ArrayList<String>> expected = COLLECT_LINES();


        // act
        ArrayList<ArrayList<String>> actual = FILTER_1.filter(input);
        // assert
        assertTrue(isSameLines(expected, actual));


    }

    @Test
    void Test_Input_ACCEPT_2_SHOULDACCEPT_1() {

        // arrange inputs
        ArrayList<String> inputLine1 = CREATE_LINE("isa");
        ArrayList<String> inputLine2 = CREATE_LINE("some", "Is");
        ArrayList<String> inputLine3 = CREATE_LINE("some", "is");
        ArrayList<String> inputLine4 = CREATE_LINE("ISS");
        ArrayList<String> inputLine5 = CREATE_LINE("c", "IS");
        ArrayList<String> inputLine6 = CREATE_LINE("cis", "Is");
        ArrayList<ArrayList<String>> input = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4, inputLine5, inputLine6);

        // arrange expected
        ArrayList<ArrayList<String>> expected = COLLECT_LINES(inputLine1, inputLine2, inputLine3, inputLine4, inputLine5, inputLine6);

        // act
        ArrayList<ArrayList<String>> actual = FILTER_2.filter(input);
        // assert
        assertEquals(6, actual.size());
        assertTrue(isSameLines(expected, actual));
    }
}
