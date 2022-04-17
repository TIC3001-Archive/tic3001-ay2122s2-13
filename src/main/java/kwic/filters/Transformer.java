package kwic.filters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class Transformer {
    /**
     * For each line, generate circular shifted lines
     *
     * Line:
     * word1, word2, word3
     *
     * Shifted Lines:
     * word1, word2, word3
     * word2, word3, word1
     * word3, word2, word1
     *
     * @param lines
     * @return
     */
    public static ArrayList<ArrayList<String>> shift(ArrayList<ArrayList<String>> lines) {
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

    /**
     * Lexically
     * @param lines
     * @return
     */
    public static ArrayList<ArrayList<String>> lexi(ArrayList<ArrayList<String>> lines) {
        ArrayList<ArrayList<String>> alphabetized = new ArrayList<>(lines);
        alphabetized.sort(Comparator.comparing(a -> String.join(" ", a).toLowerCase(Locale.ROOT)));
        return alphabetized;
    }
}
