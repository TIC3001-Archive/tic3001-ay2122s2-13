package kwic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestKWICUtils {
    public static boolean isSameWords(ArrayList<String> expected, ArrayList<String> actual) {
        int size = expected.size();
        if (size != actual.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }
        return true;
    }


    public static boolean isSameLines(ArrayList<ArrayList<String>> expected, ArrayList<ArrayList<String>> actual) {
        int size = expected.size();
        if (size != actual.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!isSameWords(expected.get(i), actual.get(i))) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static ArrayList<ArrayList<String>> COLLECT_LINES(ArrayList<String>... additions) {

        ArrayList<ArrayList<String>> inputs = new ArrayList<>();

        Collections.addAll(inputs, additions);
        return inputs;
    }

    public static ArrayList<String> CREATE_LINE(String... words){
        return new ArrayList<>(List.of(words));
    }
}
