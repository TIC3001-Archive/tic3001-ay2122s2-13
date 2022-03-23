package kwic.filters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class Transformer {
    public static ArrayList<ArrayList<String>> SHIFT(ArrayList<ArrayList<String>> lines) {
        ArrayList<ArrayList<String>> shiftedLinesAll = new ArrayList<>();
        for (ArrayList<String> words : lines) {
            ArrayList<ArrayList<String>> thisShiftedLines = new ArrayList<>();
            int length = words.size();
            for (int i = 0; i < length; i++) {
                ArrayList<String> _words = new ArrayList<>();
                for (int j = 0; j < length; j++) {
                    _words.add(words.get((i + j) % length));
                }
                thisShiftedLines.add(_words);
            }
            shiftedLinesAll.addAll(thisShiftedLines);
        }
        return shiftedLinesAll;
    }

    public static ArrayList<ArrayList<String>> LEXI(ArrayList<ArrayList<String>> lines) {
        ArrayList<ArrayList<String>> alphabetized = new ArrayList<>(lines);
        alphabetized.sort(Comparator.comparing(a -> String.join(" ", a).toLowerCase(Locale.ROOT)));
        return alphabetized;
    }
}
